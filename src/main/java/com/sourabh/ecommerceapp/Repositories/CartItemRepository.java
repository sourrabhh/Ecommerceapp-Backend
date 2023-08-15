package com.sourabh.ecommerceapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sourabh.ecommerceapp.Model.Cart;
import com.sourabh.ecommerceapp.Model.CartItem;
import com.sourabh.ecommerceapp.Model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long>
{
    // @Query("Select ci From CartItem Where ci.Cart = :cart AND ci.product = :product AND ci.size = :size AND ci.userId = :userId")

    @Query(" SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.product = :product AND ci.size = :size AND ci.userId = :userId ")
    CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product, @Param("size") String size, @Param("userId") Long userId);
    
}
