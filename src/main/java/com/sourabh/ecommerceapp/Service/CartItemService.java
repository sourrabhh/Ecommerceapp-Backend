package com.sourabh.ecommerceapp.Service;

import org.springframework.security.core.userdetails.User;

import com.sourabh.ecommerceapp.Exceptions.CartItemException;
import com.sourabh.ecommerceapp.Exceptions.UserException;
import com.sourabh.ecommerceapp.Model.Cart;
import com.sourabh.ecommerceapp.Model.CartItem;
import com.sourabh.ecommerceapp.Model.Product;

public interface CartItemService 
{
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart,Product product,Long userId, String size );

    public void removeCartItem(Long userId, Long cartItemId ) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
