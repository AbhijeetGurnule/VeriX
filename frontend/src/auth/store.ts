import type LoginData from "@/models/LoginData";
import type LoginResponseData from "@/models/LoginResponseData";
import type User from "@/models/User";
import { loginUser, logoutUser } from "@/services/AuthService";
import { create } from "zustand";
import { persist } from "zustand/middleware";

const LOCAL_KEY = "app_state";
// type AuthStatus = "idle" | "authenticating" | "authenticated" | "anonymous";

// Global authstate for authentication using Zustand
type AuthState = {
  accessToken: string | null;
  user: User | null;
  authStatus: boolean;
  authLoading: boolean;
  login: (loginData: LoginData) => Promise<LoginResponseData>;
  logout: (silent?: boolean) => void;
  checkLogin: () => boolean | undefined;

  changeLocalLoginData: (
    accessToken: string,
    user: User,
    authStatus: boolean,
  ) => void;
};

// main logic for global auth state management
const useAuth = create<AuthState>()(
  persist(
    (set, get) => ({
      accessToken: null,
      user: null,
      authStatus: false,
      authLoading: false,

      changeLocalLoginData: (accessToken, user, authStatus) => {
        set({
          accessToken,
          user,
          authStatus,
        });
      },

      login: async (loginData) => {
        console.log("started login...");
        set({ authLoading: true });
        try {
          const loginResponseData = await loginUser(loginData);
          console.log("login response data: ", loginResponseData);
          set({
            accessToken: loginResponseData.accessToken,
            user: loginResponseData.user,
            authStatus: true,
          });
          return loginResponseData;
        } catch (error) {
          console.log("login error: ", error);
          throw error;
        } finally {
          set({ authLoading: false });
        }
      },
      logout: async (silent = false) => {
        try {
          // if(!silent) {
          //     await logoutUser();
          // }
          set({
            authLoading: true,
          });
          await logoutUser();
        } catch (error) {
        } finally {
          set({ authLoading: false });
        }
        set({
          accessToken: null,
          user: null,
          authStatus: false,
          authLoading: false,
        });

        // await logoutUser();
        // set({
        //     accessToken: null,
        //     user: null,
        //     authStatus: false,
        //     authLoading: false,
        // });
      },
      checkLogin: () => {
        if (get().accessToken && get().authStatus) return true;
        else false;
      },
    }),
    { name: LOCAL_KEY },
  ),
);

export default useAuth;