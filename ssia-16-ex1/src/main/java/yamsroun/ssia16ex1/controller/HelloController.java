package yamsroun.ssia16ex1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yamsroun.ssia16ex1.service.NameService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final NameService nameService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, " + nameService.getName();
    }

    @GetMapping("/secret/names/{name}")
    public List<String> names(@PathVariable String name) {
        return nameService.getSecretNames(name);
    }
}
