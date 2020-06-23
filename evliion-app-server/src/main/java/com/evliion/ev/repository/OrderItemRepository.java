package com.evliion.ev.repository;

import com.evliion.ev.model.Order;
import com.evliion.ev.model.OrderItem;
import com.evliion.ev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
    List<OrderItem> findByUserAndOrderIsNull(User user);
}
