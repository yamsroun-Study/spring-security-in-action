package yamsroun.ssia10.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @PostMapping("/add")
    public String add(@RequestParam String name) {
        log.info(">>> Adding product={}", name);
        return "home.html";
    }
}
