package com.sourabh.ecommerceapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourabh.ecommerceapp.Exceptions.OrderException;
import com.sourabh.ecommerceapp.Model.Order;
import com.sourabh.ecommerceapp.Response.ApiResponse;
import com.sourabh.ecommerceapp.Service.OrderService;


@RestController
@RequestMapping("/api/admin/order")
public class AdminOrderController 
{
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrderHandler()
    {
        List<Order> allOrders = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(allOrders, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmedOrderHandler(@PathVariable("orderId") Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException
    {
        Order confirmOrder = orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(confirmOrder, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/shipped")
    public ResponseEntity<Order> shippedOrderHandler(@PathVariable("orderId") Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException
    {
        Order shippedOrder = orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(shippedOrder, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/delivered")
    public ResponseEntity<Order> deliveredOrderHandler(@PathVariable("orderId") Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException
    {
        Order deliveredOrder = orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(deliveredOrder, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancelled")
    public ResponseEntity<Order> cancelledOrderHandler(@PathVariable("orderId") Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException
    {
        Order cancelledOrder = orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(cancelledOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable("orderId") Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException
    {
        orderService.deleteOrder(orderId);
        ApiResponse res = new ApiResponse();
        res.setMessage("order deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
}
