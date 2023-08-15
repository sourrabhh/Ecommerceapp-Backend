package com.sourabh.ecommerceapp.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sourabh.ecommerceapp.Exceptions.ProductException;
import com.sourabh.ecommerceapp.Model.Category;
import com.sourabh.ecommerceapp.Model.Product;
import com.sourabh.ecommerceapp.Repositories.CategoryRepository;
import com.sourabh.ecommerceapp.Repositories.ProductRepository;
// import com.sourabh.ecommerceapp.Repositories.UserRepository;
import com.sourabh.ecommerceapp.Request.CreateProductRequest;

@Service
public class ProductServiceImplementation implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // @Autowired
    // private UserRepository userRepository;

    @Override
    public Product createProduct(CreateProductRequest request) 
    {
        Category toplevel = categoryRepository.findByName(request.getToplevelCategory());
        if(toplevel == null)
        {
            Category toplevelCategory = new Category();
            toplevelCategory.setName(request.getToplevelCategory()); 
            toplevelCategory.setLevel(1);

            toplevel = categoryRepository.save(toplevelCategory);
        }

        Category secondlevel = categoryRepository.findByNameAndParentCategory(request.getSecondlevelCategory(), toplevel.getName());
        if(secondlevel == null)
        {
            Category secondlevelCategory = new Category();
            secondlevelCategory.setName(request.getSecondlevelCategory());
            secondlevelCategory.setParentCategory(toplevel);
            secondlevelCategory.setLevel(2);

            secondlevel = categoryRepository.save(secondlevelCategory);
        }

        Category thirdlevel = categoryRepository.findByNameAndParentCategory(request.getThirdlevelCategory(), secondlevel.getName());
        if(thirdlevel == null)
        {
            Category thirdlevelCategory = new Category();
            thirdlevelCategory.setName(request.getThirdlevelCategory());
            thirdlevelCategory.setParentCategory(secondlevel);
            thirdlevelCategory.setLevel(3);

            thirdlevel = categoryRepository.save(thirdlevelCategory);
        }

        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setBrand(request.getBrand());
        product.setColor(request.getColor());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setDiscountedPrice(request.getDiscountedPrice());
        product.setDiscountedPercent(request.getDiscountPercentage());
        product.setImgUrl(request.getImageUrl());
        product.setQuantity(request.getQuantity());
        product.setCategory(thirdlevel);
        product.setSize(request.getSize());
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        
        return savedProduct;   
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException 
    {
        Product product = findProductById(productId);
        product.getSize().clear();
        productRepository.delete(product);

        return "Product Deleted Successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException 
    {
        Product product = findProductById(productId);  

        if(req.getQuantity() != 0)   // Only Quantity update
        {    product.setQuantity(req.getQuantity()); 
        }

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) throws ProductException 
    {
        Optional<Product> optionalProd = productRepository.findById(id);

        if(optionalProd.isPresent()){   
            optionalProd.get();
        }
        throw new ProductException("Product not found with ID "+id);
        
    }

    @Override
    public List<Product> findProductByCategory(String category) throws ProductException {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
            Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) 
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productRepository.filterProduct(category, minPrice, maxPrice, minDiscount, sort);

        if(!colors.isEmpty())
        {
            products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor() ) )).collect(Collectors.toList());
        }

        if(stock != null){
            if(stock.equals("in_stock")){
                products.stream().filter(p -> p.getQuantity()>0).collect(Collectors.toList());
            }
            else if(stock.equals("out_of_stocks")) {
                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
            }
        }

        int startIndex = (int)pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);
        Page<Product> filterProducts = new PageImpl<>(pageContent,pageable, products.size());
        return filterProducts;
    }
    
}
