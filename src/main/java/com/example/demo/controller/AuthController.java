package com.example.demo.controller;

import com.example.demo.bean.Const;
import com.example.demo.bean.entity.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/auth", name = "驗證")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping(value = "/login", name = "登入頁面")
    public String login() {
        return "auth/login";
    }

    @GetMapping(value = "/register", name = "註冊頁面")
    public String register() {
        return "auth/register";
    }

    @PostMapping(value = "/login", name = "登入api")
    public String login(@RequestParam String account, @RequestParam String password, Model model, HttpSession session) {
        User login = userService.login(account, password);
        if (login == null) {
            model.addAttribute("error", true);
            return "auth/login";
        }
        session.setAttribute("userId", login.getId());
        session.setAttribute("account", login.getAccount());
        session.setAttribute("role", login.getRoleId());
        if (login.getRoleId().equals(Const.ADMIN_ID)) {
            // 管理員->導向後台
            return "redirect:/bo";
        }
        // 會員->導向首頁
        return "redirect:/";
    }

    @PostMapping(value = "/register", name = "註冊api")
    public String register(@RequestParam String account,
                           @RequestParam String password,
                           @RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String phone, Model model) {
        String response = userService.createUser(account, password, name, email, phone);
        if (response.equals("success")) {
            return "redirect:/auth/login";
        }
        model.addAttribute("error", true);
        model.addAttribute("message", response);
        return "auth/register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("account");
        session.invalidate();
        return "redirect:/";
    }
}
