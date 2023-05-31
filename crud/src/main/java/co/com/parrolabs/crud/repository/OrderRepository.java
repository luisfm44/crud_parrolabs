package co.com.parrolabs.crud.repository;

import co.com.parrolabs.crud.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.orderNumber LIKE %:keyword% OR o.customer.name LIKE %:keyword%")
    List<Order> search(@Param("keyword") String keyword);

    boolean existsByOrderNumber(String orderNumber);

    Order findByOrderNumber(String orderNumber);
}
