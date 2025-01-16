package com.example.demo.controller;

import com.example.demo.bean.entity.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final ProductService productService;

    @GetMapping
    public String index(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
        return "index";
    }
}
