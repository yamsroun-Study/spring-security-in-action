package yamsroun.ssiach6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yamsroun.ssiach6.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
