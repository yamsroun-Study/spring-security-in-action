package yamsroun.ssia10.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import yamsroun.ssia10.repository.CustomTokenJpaRepository;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final CustomTokenJpaRepository customTokenJpaRepository;

    @GetMapping("/hello")
    public String getHello() {
        customTokenJpaRepository.findAll();
        return "Get Hello!";
    }

    @PostMapping("/hello")
    public String postHello() {
        log.info("postHello() called");
        customTokenJpaRepository.findAll();
        return "Post Hello!";
    }

    @PostMapping("/ciao")
    public String postCiao() {
        log.info("postCiao() called");
        return "Post Ciao!";
    }

    @PostMapping("/ciao2")
    public String postCiao2() {
        log.info("postCiao2() called");
        return "Post Ciao2!";
    }
}
