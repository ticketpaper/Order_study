package com.example.order.OrderItem.repository;

import com.example.order.OrderItem.domain.OrderItem;
import com.example.order.Ordering.domain.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrdering(Ordering ordering);
}
