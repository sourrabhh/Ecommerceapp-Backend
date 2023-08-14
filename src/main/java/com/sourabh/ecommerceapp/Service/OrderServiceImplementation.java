package com.sourabh.ecommerceapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourabh.ecommerceapp.Exceptions.OrderException;
import com.sourabh.ecommerceapp.Model.Address;
import com.sourabh.ecommerceapp.Model.CartItem;
import com.sourabh.ecommerceapp.Model.Order;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Repositories.CartRepository;


@Service
public class OrderServiceImplementation implements OrderService
{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;


    @Override
    public Order createOrder(User user, Address shippingAddress) 
    {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException
    {
        return null;
    }

    @Override
    public List<Order> userOrderHistory(Long userId)
    {
        return null;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException 
    {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException 
    {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException 
    {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deliveredOrder'");
    }

    @Override
    public Order cancelOrder(Long OrderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelOrder'");
    }

    @Override
    public List<Order> getAllOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }
    
}
