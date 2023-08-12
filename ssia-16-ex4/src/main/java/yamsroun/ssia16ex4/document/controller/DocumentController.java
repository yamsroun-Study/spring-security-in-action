package yamsroun.ssia16ex4.document.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yamsroun.ssia16ex4.document.domain.Document;
import yamsroun.ssia16ex4.document.service.DocumentService;

@RestController
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/documents/{code}")
    public Document getDocumentDetails(@PathVariable String code) {
        return documentService.getDocument(code);
    }
}
