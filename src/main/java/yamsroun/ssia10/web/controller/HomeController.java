package yamsroun.ssia10.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

    @GetMapping("/")
    public String main() {
        return "main.html";
    }

    //@CrossOrigin(origins = "http://localhost:8888")
    @PostMapping("/test")
    @ResponseBody
    public String test() {
        log.info(">>> Test method called");
        return "HELLO";
    }
}
