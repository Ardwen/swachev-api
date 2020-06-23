package com.evliion.ev.controller;

import com.evliion.ev.model.Order;
import com.evliion.ev.model.OrderItem;
import com.evliion.ev.model.Product;
import com.evliion.ev.model.User;
import com.evliion.ev.payload.OrderRequest;
import com.evliion.ev.payload.TransactionRequest;
import com.evliion.ev.repository.OrderRepository;
import com.evliion.ev.repository.UserRepository;
import com.evliion.ev.security.CurrentUser;
import com.evliion.ev.security.UserPrincipal;
import com.evliion.ev.service.OrderItemService;
import com.evliion.ev.service.OrderService;
import com.evliion.ev.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CartController {
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ProductService productService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderService orderService;

    @GetMapping("/addCart")
    public Object addCart( @RequestParam(value = "pid") Long pid, @RequestParam(value = "num") int num, @CurrentUser UserPrincipal currentUser) {
        return buyoneAndAddCart(pid,num,currentUser);
    }

    @GetMapping("/displayCart")
    public Object cart(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.getOne(currentUser.getId());
        List<OrderItem> ois = orderItemService.listByUser(user);
        return ois;
    }

    private int buyoneAndAddCart(long pid, int num, UserPrincipal currentUser) {

        User user = userRepository.getOne(currentUser.getId());
        Product product = productService.getProductById(pid,currentUser);
        int oiid = 0;

        boolean found = false;
        List<OrderItem> ois = orderItemService.listByUser(user);
        for (OrderItem oi : ois) {
            if(oi.getProduct().getId()==product.getId()){
                oi.setNumber(oi.getNumber()+num);
                orderItemService.update(oi);
                found = true;
                oiid = oi.getId();
                break;
            }
        }

        if(!found){
            OrderItem oi = new OrderItem();
            oi.setUser(user);
            oi.setProduct(product);
            oi.setNumber(num);
            orderItemService.add(oi);
            oiid = oi.getId();
        }
        return oiid;
    }

    @GetMapping("/changeCartItem")
            public Object changeOrderItem(@RequestParam(value = "pid") Long pid, @RequestParam(value = "num") int num, @CurrentUser UserPrincipal currentUser) {
        User user = userRepository.getOne(currentUser.getId());

        List<OrderItem> ois = orderItemService.listByUser(user);
        for (OrderItem oi : ois) {
            if(oi.getProduct().getId()==pid){
                oi.setNumber(num);
                orderItemService.update(oi);
                break;
            }
        }
        return "Success";
    }

    @GetMapping("/deleteCartItem")
    public Object deleteOrderItem(@RequestParam(value = "ooid") int oiid,@CurrentUser UserPrincipal currentUser){
        User user = userRepository.getOne(currentUser.getId());

        orderItemService.delete(oiid);
        return "Success";
    }

    @GetMapping("/createOrder")
    public Object buy(@RequestBody OrderRequest orderRequest, @CurrentUser UserPrincipal currentUser){
        User user = userRepository.getOne(currentUser.getId());
        Order order = new Order();
        order.setUser(user);
        order.setUserMessage(orderRequest.getUsermessage());
        order.setMobile(orderRequest.getMobile());
        return orderService.add(order,orderRequest.getOoid());
    }


}
