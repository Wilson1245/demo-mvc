package com.example.demo.controller.bo;

import com.example.demo.bean.Const;
import com.example.demo.bean.dto.request.ProductRequest;
import com.example.demo.bean.dto.request.update.UpdateProductRequest;
import com.example.demo.bean.dto.response.ProductResponse;
import com.example.demo.bean.entity.Product;
import com.example.demo.controller.BaseController;
import com.example.demo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bo/product")
@RequiredArgsConstructor
public class BoProductController extends BaseController {

    private final ProductService productService;

    @GetMapping
    public String index(HttpSession httpSession, Model model) {
        if (!checkIdentity(httpSession, Const.ADMIN_ID)) {
            return "redirect:/auth/login";
        }
        List<ProductResponse> response = productService.findAll().stream().map(ProductResponse::of).toList();
        model.addAttribute("productList", response);
        return "bo/product/index";
    }

    @GetMapping("/add")
    public String add(HttpSession httpSession, Model model) {
        if (!checkIdentity(httpSession, Const.ADMIN_ID)) {
            return "redirect:/auth/login";
        }
        model.addAttribute("product", new ProductRequest());
        return "bo/product/add";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model, HttpSession httpSession) {
        if (!checkIdentity(httpSession, Const.ADMIN_ID)) {
            return "redirect:/auth/login";
        }
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("updateProductRequest", new UpdateProductRequest());
            return "bo/product/edit";
        }
        return "redirect:/bo/product";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id, HttpSession httpSession) {
        if (!checkIdentity(httpSession, Const.ADMIN_ID)) {
            return "redirect:/auth/login";
        }
        productService.delete(id);
        return "redirect:/bo/product";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute ProductRequest request, HttpSession httpSession) {
        if (!checkIdentity(httpSession, Const.ADMIN_ID)) {
            return "redirect:/auth/login";
        }
        productService.create(request);
        return "redirect:/bo/product";
    }

    @PostMapping("/edit")
    public String update(@RequestParam("id") Long id, @ModelAttribute UpdateProductRequest request, HttpSession httpSession) {
        if (!checkIdentity(httpSession, Const.ADMIN_ID)) {
            return "redirect:/auth/login";
        }
        productService.update(id, request);
        return "redirect:/bo/product";
    }
}
