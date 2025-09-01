package org.example.ytlearning.repository;

import org.example.ytlearning.emunration.OrderStatus;
import org.example.ytlearning.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findFirstByOrderId(String orderId);

    Optional<Order> findFirstByUserIdAndOrderStatusNot(Long userId, OrderStatus orderStatus);
}
