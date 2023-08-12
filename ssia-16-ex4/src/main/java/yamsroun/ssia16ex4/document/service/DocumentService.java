package yamsroun.ssia16ex4.document.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import yamsroun.ssia16ex4.document.domain.Document;
import yamsroun.ssia16ex4.document.repository.DocumentRepository;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    @PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
    public Document getDocument(String code) {
        return documentRepository.findDocument(code);
    }

    @PreAuthorize("hasPermission(#code, 'document', 'ROLE_admin')")
    public Document getDocument2(String code) {
        return documentRepository.findDocument(code);
    }
}
