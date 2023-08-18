package com.sourabh.ecommerceapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourabh.ecommerceapp.Exceptions.ProductException;
import com.sourabh.ecommerceapp.Exceptions.UserException;
import com.sourabh.ecommerceapp.Model.Cart;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Request.AddToCartRequest;
import com.sourabh.ecommerceapp.Response.ApiResponse;
import com.sourabh.ecommerceapp.Service.CartService;
import com.sourabh.ecommerceapp.Service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart Management")
public class CartController 
{
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;


    @GetMapping("/target")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException
    {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());


        return new ResponseEntity<Cart>(cart, HttpStatus.FOUND);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddToCartRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException
    {
        User user = userService.findUserProfileByJwt(jwt);

        cartService.addCartItem(user.getId(), req);

        ApiResponse response = new ApiResponse();
        response.setMessage("Item Added to Cart ");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
}
