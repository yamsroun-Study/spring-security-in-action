package yamsroun.ssia16ex1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yamsroun.ssia16ex1.domain.Employee;
import yamsroun.ssia16ex1.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/details/{name}")
    public Employee getDetails(@PathVariable String name) {
        return bookService.getBookDetails(name);
    }
}
