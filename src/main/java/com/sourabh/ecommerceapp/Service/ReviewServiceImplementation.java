package com.sourabh.ecommerceapp.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourabh.ecommerceapp.Exceptions.ProductException;
import com.sourabh.ecommerceapp.Model.Product;
import com.sourabh.ecommerceapp.Model.Review;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Repositories.ReviewRepository;
import com.sourabh.ecommerceapp.Request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService
{
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductService productService;
    
    // @Autowired
    // private ProductRepository productRepository;

    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException 
    {
        Product product = productService.findProductById(req.getProductId());

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setCreatedAt(LocalDateTime.now());
        review.setReview(req.getReview());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) 
    {
        return reviewRepository.getAllProductReview(productId);
    }
    
}
