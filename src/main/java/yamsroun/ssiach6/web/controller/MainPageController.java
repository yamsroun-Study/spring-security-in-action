package yamsroun.ssiach6.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import yamsroun.ssiach6.service.ProductService;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final ProductService productService;

    @GetMapping("/main")
    public String main(Authentication auth, Model model) {
        model.addAttribute("username", auth.getName());
        model.addAttribute("products", productService.findAll());
        return "main";
    }
}
