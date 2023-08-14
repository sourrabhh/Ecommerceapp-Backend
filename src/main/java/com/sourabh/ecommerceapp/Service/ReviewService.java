package com.sourabh.ecommerceapp.Service;

import java.util.List;

import com.sourabh.ecommerceapp.Exceptions.ProductException;
import com.sourabh.ecommerceapp.Model.Review;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Request.ReviewRequest;

public interface ReviewService 
{
    public Review createReview(ReviewRequest req, User user) throws ProductException;

    public List<Review> getAllReview(Long productId);
}
