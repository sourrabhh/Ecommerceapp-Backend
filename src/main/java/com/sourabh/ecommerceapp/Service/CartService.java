package com.sourabh.ecommerceapp.Service;

import com.sourabh.ecommerceapp.Exceptions.ProductException;
import com.sourabh.ecommerceapp.Model.Cart;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Request.AddToCartRequest;

public interface CartService 
{
    public Cart createCart(User user);

    public String addCartItem(Long userId, AddToCartRequest req) throws ProductException;

    public Cart findUserCart(Long userId);
}
