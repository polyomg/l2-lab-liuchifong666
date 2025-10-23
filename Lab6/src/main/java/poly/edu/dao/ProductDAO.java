package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.entity.Product;

/**
 * Repository interface for the Product entity.
 */
public interface ProductDAO extends JpaRepository<Product, Integer> {
}
