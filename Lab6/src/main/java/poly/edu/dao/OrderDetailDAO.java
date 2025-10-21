package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.entity.OrderDetail;

/**
 * Repository interface for the OrderDetail entity.
 */
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
}
