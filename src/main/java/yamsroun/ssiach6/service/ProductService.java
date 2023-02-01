package yamsroun.ssiach6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yamsroun.ssiach6.domain.Product;
import yamsroun.ssiach6.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
