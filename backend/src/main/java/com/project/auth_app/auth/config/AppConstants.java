package com.project.auth_app.auth.config;

public class AppConstants {
    public static final String[] AUTH_PUBLIC_URLS = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**"
    };

    public static final String[] AUTH_ADMIN_URLS = {
            "/api/v1/users/**"
    };

    public static final String[] AUTH_GUEST_URLS = {
            // here add only those urls accessible only to guest users
    };

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String GUEST_ROLE = "GUEST";

    // here you can add other project-related constants
}
