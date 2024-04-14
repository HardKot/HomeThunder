package com.homethunder.homethunder.infrastructure.libs;

import jakarta.servlet.http.Cookie;

public class CookieLibs {
    public static Cookie setCookieAuth(String token) {
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        cookie.setDomain("localhost");

        return cookie;
    }
}
