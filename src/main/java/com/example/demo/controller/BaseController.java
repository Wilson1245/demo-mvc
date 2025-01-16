package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

public class BaseController {

    public boolean checkIdentity(HttpSession httpSession, Long roleId) {
        return httpSession.getAttribute("account") != null && httpSession.getAttribute("role") != null &&
                httpSession.getAttribute("role").equals(roleId);
    }
}
