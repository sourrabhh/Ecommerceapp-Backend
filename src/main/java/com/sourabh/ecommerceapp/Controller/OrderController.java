package com.sourabh.ecommerceapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourabh.ecommerceapp.Exceptions.OrderException;
import com.sourabh.ecommerceapp.Exceptions.UserException;
import com.sourabh.ecommerceapp.Model.Address;
import com.sourabh.ecommerceapp.Model.Order;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Service.OrderService;
import com.sourabh.ecommerceapp.Service.UserService;

@RestController
@RequestMapping("/api/order")
public class OrderController 
{
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Order>createOrder(@RequestBody Address shippingAddress,
                @RequestHeader("authorization") String jwt) throws UserException
    {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.createOrder(user, shippingAddress);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException
    {
        User user = userService.findUserProfileByJwt(jwt);
        List<Order> orderList = orderService.userOrderHistory(user.getId());

        return new ResponseEntity<>(orderList, HttpStatus.CREATED);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("orderId") Long orderId, @RequestHeader("Authorization") String jwt) throws UserException, OrderException
    {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.findOrderById(user.getId());

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

}
