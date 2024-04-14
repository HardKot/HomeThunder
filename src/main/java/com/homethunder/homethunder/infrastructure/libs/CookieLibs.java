package com.homethunder.homethunder.infrastructure.libs;

import jakarta.servlet.http.Cookie;

public class CookieLibs {
    public static Cookie setCookieAuth(String token, boolean rememberMe) {
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setPath("/");
        cookie.setMaxAge(rememberMe ? 86400 : -1);
        cookie.setDomain("localhost");

        return cookie;
    }
}
