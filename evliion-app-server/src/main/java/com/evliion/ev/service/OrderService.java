package com.evliion.ev.service;

import com.evliion.ev.model.Order;
import com.evliion.ev.model.OrderItem;
import com.evliion.ev.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";

    @Autowired
    OrderRepository orderDAO;
    @Autowired OrderItemService orderItemService;

    /*public Page4Navigator<Order> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA =orderDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }*/

    public void removeOrderFromOrderItem(List<Order> orders) {
        for (Order order : orders) {
            removeOrderFromOrderItem(order);
        }
    }

    private void removeOrderFromOrderItem(Order order) {
        List<OrderItem> orderItems= order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(null);
        }
    }

    public Order get(int oid) {
        return orderDAO.getOne(oid);
    }

    public void update(Order bean) {
        orderDAO.save(bean);
    }



    //@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public Order add(Order order, List<Integer> oiid) {
        float total = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        for (int id : oiid) {
            OrderItem oi= orderItemService.get(id);
            total +=oi.getProduct().getRate()*oi.getNumber();
            orderItems.add(oi);
        }
        order.setOrderItems(orderItems);
        order.setStatus(waitPay);
        order.setTotal(total);
        orderDAO.save(order);
        return order;
    }

    public void add(Order order) {
        orderDAO.save(order);
    }

}
