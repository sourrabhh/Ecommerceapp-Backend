package com.sourabh.ecommerceapp.Service;

import java.util.List;

import com.sourabh.ecommerceapp.Exceptions.ProductException;
import com.sourabh.ecommerceapp.Model.Rating;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Request.RatingRequest;


public interface RatingService 
{
    public Rating createRating(RatingRequest req, User user) throws ProductException;
    
    public List<Rating> getProductRating(Long productId );
}
