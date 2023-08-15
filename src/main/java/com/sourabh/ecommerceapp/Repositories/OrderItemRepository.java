package com.sourabh.ecommerceapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.ecommerceapp.Model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>
{
    
}
