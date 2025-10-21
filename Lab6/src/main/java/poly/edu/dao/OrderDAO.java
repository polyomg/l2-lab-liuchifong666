package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.entity.Order;

/**
 * Repository interface for the Order entity.
 */
public interface OrderDAO extends JpaRepository<Order, Long> {
}
