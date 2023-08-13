package com.sourabh.ecommerceapp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sourabh.ecommerceapp.Model.Product;


public interface ProductRepository extends JpaRepository<Product, Long>
{
    @Query("SELECT p FROM Product p " + 
           "WHERE (p.category.name = :category OR :category = '') " +
           "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) " +
           "AND (:minDiscount IS NULL OR p.discountedPercent >= :minDiscount) " +
           "ORDER BY " +
           "CASE WHEN :sort = 'priceLow' THEN p.discountedPrice END ASC, " +
           "CASE WHEN :sort = 'priceHigh' THEN p.discountedPrice END DESC")
    public List<Product> filterProduct( @Param ("category") String category,
                                        @Param ("minPrice") Integer minPrice,
                                        @Param ("maxPrice") Integer maxPrice,
                                        @Param ("minDiscount") Integer minDiscount,
                                        @Param ("sort") String sort );
}
