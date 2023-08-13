package com.sourabh.ecommerceapp.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sourabh.ecommerceapp.Exceptions.ProductException;
import com.sourabh.ecommerceapp.Model.Product;
import com.sourabh.ecommerceapp.Request.CreateProductRequest;


public interface ProductService 
{
    public Product createProduct(CreateProductRequest createProduct);

    public String deleteProduct(Long productId ) throws ProductException;

    public Product updateProduct(Long productId, Product req) throws ProductException; 

    public Product findProductById(Long id) throws ProductException;

    public List<Product> findProductByCategory(String category) throws ProductException;

    public Page<Product> getAllProduct(String category, List<String>color, List<String>sizes, 
                Integer minPrice, Integer maxPrice, Integer minDiscount, 
                String sort, String stock, Integer pageNumber, Integer pageSize );

}
