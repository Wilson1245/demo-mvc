package com.example.demo.controller.web;

import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String info(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        if (userService.findByAccount(account) == null) {
            return "redirect:/auth/login";
        }
        return "/user/info";
    }
}
