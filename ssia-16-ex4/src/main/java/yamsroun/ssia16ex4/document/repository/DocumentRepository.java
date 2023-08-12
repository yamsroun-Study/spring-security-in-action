package yamsroun.ssia16ex4.document.repository;

import org.springframework.stereotype.Repository;
import yamsroun.ssia16ex4.document.domain.Document;

import java.util.Map;

@Repository
public class DocumentRepository {

    private final Map<String, Document> documents = Map.of(
        "abc123", new Document("natalie"),
        "qwe123", new Document("natalie"),
        "asd123", new Document("emma")
    );

    public Document findDocument(String code) {
        return documents.get(code);
    }
}
