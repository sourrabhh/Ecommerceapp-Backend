package com.sourabh.ecommerceapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sourabh.ecommerceapp.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>
{
    @Query("Select c from Cart Where c.user.id = :userId")
    public Cart findByUserId(@Param("userId") Long userId);
}
