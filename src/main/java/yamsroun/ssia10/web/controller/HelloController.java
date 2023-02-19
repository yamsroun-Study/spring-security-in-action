package yamsroun.ssia10.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        log.info("hello() called");
        return "Get Hello!";
    }

    @PostMapping("/hello")
    public String postHello() {
        log.info("postHello() called");
        return "Post Hello!";
    }

    @PostMapping("/ciao")
    public String postCiao() {
        log.info("postCiao() called");
        return "Post Ciao!";
    }
}
