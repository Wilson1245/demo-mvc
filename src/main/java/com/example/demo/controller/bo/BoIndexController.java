package com.example.demo.controller.bo;

import com.example.demo.bean.Const;
import com.example.demo.controller.BaseController;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bo")
@RequiredArgsConstructor
public class BoIndexController extends BaseController {

    @GetMapping
    public String index(HttpSession httpSession) {
        if (!checkIdentity(httpSession, Const.ADMIN_ID)) {
            return "redirect:/auth/login";
        }
        return "bo/index";
    }
}
