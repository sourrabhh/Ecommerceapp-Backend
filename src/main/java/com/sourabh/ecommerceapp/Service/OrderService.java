package com.sourabh.ecommerceapp.Service;

import java.util.List;

import com.sourabh.ecommerceapp.Exceptions.OrderException;
import com.sourabh.ecommerceapp.Model.Address;
import com.sourabh.ecommerceapp.Model.Order;
import com.sourabh.ecommerceapp.Model.User;

public interface OrderService 
{
    public Order createOrder(User user, Address shippingAddress);

    public Order findOrderById(Long orderId) throws OrderException;

    public List<Order> userOrderHistory(Long userId);

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancelOrder(Long OrderId) throws OrderException;
    
    public List<Order> getAllOrders();
    
    public void deleteOrder(Long orderId) throws OrderException;

}
