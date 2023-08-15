package com.sourabh.ecommerceapp.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourabh.ecommerceapp.Exceptions.OrderException;
import com.sourabh.ecommerceapp.Model.Address;
import com.sourabh.ecommerceapp.Model.Cart;
import com.sourabh.ecommerceapp.Model.CartItem;
import com.sourabh.ecommerceapp.Model.Order;
import com.sourabh.ecommerceapp.Model.OrderItem;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Repositories.AddressRepository;
import com.sourabh.ecommerceapp.Repositories.CartRepository;
import com.sourabh.ecommerceapp.Repositories.OrderItemRepository;
import com.sourabh.ecommerceapp.Repositories.OrderRepository;
import com.sourabh.ecommerceapp.Repositories.UserRepository;


@Service
public class OrderServiceImplementation implements OrderService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public Order createOrder(User user, Address shippingAddress) 
    {
        shippingAddress.setUser(user);
        Address address = addressRepository.save(shippingAddress);
        user.getAddress().add(address);
        userRepository.save(user);

        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItemList = new ArrayList<>();

        for(CartItem item : cart.getCartItem())
        {
            OrderItem orderItem = new OrderItem();

            orderItem.setPrice(item.getPrice());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setProduct(item.getProduct());
            orderItem.setUserId(item.getUserId());

            OrderItem createdOrderItem = orderItemRepository.save(orderItem);
            orderItemList.add(createdOrderItem);

        }

        Order createdOrder = new Order();

        createdOrder.setUser(user);
        createdOrder.setOrderItem(orderItemList);
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setDiscount(cart.getDiscount());
        createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrder.setTotalItem(cart.getTotalItem());

        createdOrder.setShippingAddress(address);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.getPaymentDetails().setPaymentStatus("PENDING");
        createdOrder.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(createdOrder);

        for(OrderItem item : orderItemList)
        {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }

        return savedOrder;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException
    {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if(orderOptional.isPresent()){
            return orderOptional.get();
        }
        
        throw new OrderException("Order Not Exist with ID "+orderId);
    }

    @Override
    public List<Order> userOrderHistory(Long userId)
    {
        List<Order> orderHistory = orderRepository.getUserOrders(userId);
        return orderHistory;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException 
    {
        Order order = findOrderById(orderId);
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setPaymentStatus("COMPLETED");

        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException 
    {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");

        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException 
    {
        Order order = findOrderById(orderId);
        order.setOrderStatus("SHIPPED");

        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException 
    {
        Order order = findOrderById(orderId);
        order.setOrderStatus("DELIVERED");

        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException 
    {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CANCELLED");

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() 
    {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException 
    {
        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isPresent())
        {
            orderRepository.deleteById(orderId);
        }
        throw new OrderException("Order ID is Wrong to Delete " +orderId);
    }
    
}
