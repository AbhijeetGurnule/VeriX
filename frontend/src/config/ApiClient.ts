import useAuth from "@/auth/store";
import { refreshToken } from "@/services/AuthService";
import axios from "axios";
import toast from "react-hot-toast";

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:8083/api/v1",
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // Include cookies in requests
  timeout: 10000, // Set a timeout for requests (in milliseconds)
});

// This interceptor will add the Authorization header with the access token to every request made using apiClient. It retrieves the access token from the global auth state managed by Zustand (useAuth).
// Every request : before
apiClient.interceptors.request.use((config) => {
  // You can add any additional headers or modify the request config here
  const accessToken = useAuth.getState().accessToken;

  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }

  return config;
});

let isRefreshing = false;
let pending: any[] = [];

function queueRequest(callback: any) {
  pending.push(callback);
}

function resolveQueue(newToken: string) {
  pending.forEach((callback) => callback(newToken));
  pending = [];
}

// Response interceptor to handle 401 Unauthorized errors and refresh the access token if needed
apiClient.interceptors.response.use(
  (response) => response,
  async (error) => {
    const is401 = error.response.status === 401;
    const original = error.config;
    console.log(original);
    console.log("original retry: ", original._retry);
    if (!is401 || original._retry) {
      //message:

      if (error.response && error.response.data)
        toast.error(error.response.data?.message || "An error occurred");
      console.error("API Error:", error.response.data);
      console.error("Full error:", error);
      return Promise.reject(error);
    }

    original._retry = true;
    // If the error is a 401 Unauthorized, we can attempt to refresh the access token
    if (isRefreshing) {
      console.log("added to queue");
      return new Promise((resolve, reject) => {
        queueRequest((newToken: string) => {
          if (!newToken) return reject();
          original.headers.Authorization = `Bearer ${newToken}`;
          resolve(apiClient(original));
        });
      });
    }

    // start refreshing the token
    isRefreshing = true;

    try {
      console.log("start refreshing...");
      const loginResponse = await refreshToken();
      const newToken = loginResponse.accessToken;
      if (!newToken) throw new Error("no access token received");
      useAuth
        .getState()
        .changeLocalLoginData(
          loginResponse.accessToken,
          loginResponse.user,
          true,
        );

      resolveQueue(newToken);

      original.headers.Authorization = `Bearer ${newToken}`;
      return apiClient(original);
    } catch (error) {
      resolveQueue("null");
      useAuth.getState().logout();
      return Promise.reject(error);
    } finally {
      isRefreshing = false;
    }
  },
);

export default apiClient;