package poly.edu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import poly.edu.dao.ProductDAO;
import poly.edu.entity.Product;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDAO productDAO;

    public List<Product> findAll() {
        return productDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Product findById(Integer id) {
        return productDAO.findById(id).orElse(null);
    }

    public Product save(Product p) {
        return productDAO.save(p);
    }

    public void delete(Integer id) {
        productDAO.deleteById(id);
    }

    // Bài 1: tìm theo khoảng giá
    public List<Product> searchByPrice(Double min, Double max) {
        return productDAO.searchByPrice(min, max);
    }

    // Bài 2: tìm theo tên + phân trang
    public Page<Product> findPage(String q, int page, int size) {
        page = Math.max(page, 0);
        size = Math.max(size, 1);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        if (q == null || q.trim().isBlank()) {
            return productDAO.findAll(pageable);
        }
        return productDAO.findByNameContainingIgnoreCaseOrderByIdDesc(q.trim(), pageable);
    }
}
