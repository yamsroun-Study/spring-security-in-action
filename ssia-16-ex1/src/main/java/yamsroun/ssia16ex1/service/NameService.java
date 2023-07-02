package yamsroun.ssia16ex1.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private final Map<String, List<String>> secretNames = Map.of(
        "natalie", List.of("Energico", "Perfecto"),
        "emma", List.of("Fantastico")
    );

    @PreAuthorize("hasAuthority('write')")
    public String getName() {
        return "Fantastico";
    }

    @PreAuthorize("#name == authentication.principal.username")
    public List<String> getSecretNames(String name) {
        return secretNames.get(name);
    }
}
