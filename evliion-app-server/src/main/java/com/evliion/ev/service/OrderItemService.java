package com.evliion.ev.service;

import com.evliion.ev.model.OrderItem;
import com.evliion.ev.model.User;
import com.evliion.ev.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemDAO;

    public void update(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }

    public void add(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }
    public OrderItem get(int id) {
        return orderItemDAO.getOne(id);
    }


    public void delete(int id) {
        orderItemDAO.deleteById(id);
    }

    public List<OrderItem> listByUser(User user) {
        return orderItemDAO.findByUserAndOrderIsNull(user);
    }


}
