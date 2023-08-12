package yamsroun.ssia16ex4.document.service;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    @Secured("ROLE_ADMIN")
    public String getName() {
        return "Fantastico";
    }

    @RolesAllowed("ROLE_ADMIN")
    public String getName2() {
        return "Fantastico";
    }
}
