package com.sourabh.ecommerceapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.ecommerceapp.Model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>
{
    
}
