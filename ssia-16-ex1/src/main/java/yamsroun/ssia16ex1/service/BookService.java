package yamsroun.ssia16ex1.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import yamsroun.ssia16ex1.domain.Employee;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final Map<String, Employee> records = Map.of(
        "emma", new Employee("Emma Thompson", List.of("Karamazov Brothers"), List.of("accountant", "reader")),
        "natalie", new Employee("Natalie Parker", List.of("Beautiful Paris"), List.of("researcher"))
    );

    @PostAuthorize(value = "returnObject.roles.contains('reader')")
    public Employee getBookDetails(String name) {
        return records.get(name);
    }
}
