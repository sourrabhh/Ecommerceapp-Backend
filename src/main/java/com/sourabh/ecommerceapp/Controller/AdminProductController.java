package com.sourabh.ecommerceapp.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourabh.ecommerceapp.Exceptions.ProductException;
import com.sourabh.ecommerceapp.Model.Product;
import com.sourabh.ecommerceapp.Request.CreateProductRequest;
import com.sourabh.ecommerceapp.Response.ApiResponse;
import com.sourabh.ecommerceapp.Service.ProductService;


@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController 
{
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req)
    {
        Product product = productService.createProduct(req);

        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException
    {
        productService.deleteProduct(productId);
        ApiResponse response = new ApiResponse();
        response.setMessage("Product Deleted Successfully");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct()
    {
        List<Product> products = productService.findAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product req, @PathVariable Long productId) throws ProductException
    {
        Product products = productService.updateProduct(productId, req);

        return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody CreateProductRequest[] req)
    {
        for(CreateProductRequest product :req)
        {
            productService.createProduct(product);
        }
        ApiResponse response = new ApiResponse();
        response.setMessage("Product Created Successfully");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    
}
