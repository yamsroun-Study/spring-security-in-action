package yamsroun.ssia16ex4.document.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import yamsroun.ssia16ex4.document.domain.Document;
import yamsroun.ssia16ex4.document.repository.DocumentRepository;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class DocumentPermissionEvaluator implements PermissionEvaluator {

    private final DocumentRepository documentRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Document document = (Document) targetDomainObject;
        return isAdminOrIsOwner(authentication, permission, document);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        String code = targetId.toString();
        Document document = documentRepository.findDocument(code);
        return isAdminOrIsOwner(authentication, permission, document);
    }

    private static boolean isAdminOrIsOwner(Authentication authentication, Object permission, Document document) {
        boolean isAdmin = isAdmin(authentication, permission);
        boolean isOwner = isOwner(authentication, document);

        return isAdmin || isOwner;
    }

    private static boolean isAdmin(Authentication authentication, Object permission) {
        String permissionName = (String) permission;
        return authentication.getAuthorities()
            .stream()
            .anyMatch(auth -> auth.getAuthority().equals(permissionName));
    }

    private static boolean isOwner(Authentication authentication, Document document) {
        return document.getOwner().equals(authentication.getName());
    }
}
