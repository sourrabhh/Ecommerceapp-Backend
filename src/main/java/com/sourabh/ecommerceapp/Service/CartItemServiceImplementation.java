package com.sourabh.ecommerceapp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourabh.ecommerceapp.Exceptions.CartItemException;
import com.sourabh.ecommerceapp.Exceptions.UserException;
import com.sourabh.ecommerceapp.Model.Cart;
import com.sourabh.ecommerceapp.Model.CartItem;
import com.sourabh.ecommerceapp.Model.Product;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Repositories.CartItemRepository;
// import com.sourabh.ecommerceapp.Repositories.CartRepository;

@Service
public class CartItemServiceImplementation implements CartItemService
{
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    // @Autowired
    // private CartRepository cartRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) 
    {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

        CartItem createdCartItem = cartItemRepository.save(cartItem);
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException 
    {
        CartItem item = findCartItemById(id);
        User user = userService.findUserById(item.getUserId());

        if(user.getId() == userId)
        {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getQuantity() * item.getProduct().getPrice());

            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * cartItem.getQuantity());
        }

        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, Long userId, String size) 
    {
        CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);

        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException 
    {
        CartItem cartItem = findCartItemById(cartItemId);

        User user = userService.findUserById(cartItem.getUserId());
        User reqUser = userService.findUserById(userId);

        if(user.getId() == reqUser.getId())
        {
            cartItemRepository.deleteById(cartItemId);
        }
        else{
            throw new UserException("Cant remove other users item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException 
    {
        Optional<CartItem> opCartItem = cartItemRepository.findById(cartItemId);

        if(opCartItem.isPresent()){
            return opCartItem.get();
        }
            throw new CartItemException("Cart Item Not Found "+cartItemId);
    }
    
}
