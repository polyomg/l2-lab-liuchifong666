package poly.edu.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import poly.edu.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {

    Page<Product> findByNameContainingIgnoreCaseOrderByIdDesc(String name, Pageable pageable);

    @Query("""
        SELECT p FROM Product p
        WHERE (:min IS NULL OR p.price >= :min)
          AND (:max IS NULL OR p.price <= :max)
        ORDER BY p.price
    """)
    List<Product> searchByPrice(@Param("min") Double min, @Param("max") Double max);

    @Query("""
        SELECT p.category.name,
               COUNT(p),
               SUM(p.price),
               MIN(p.price),
               MAX(p.price),
               AVG(p.price)
        FROM Product p
        GROUP BY p.category.name
        ORDER BY p.category.name
    """)
    List<Object[]> getInventoryByCategory();
}
