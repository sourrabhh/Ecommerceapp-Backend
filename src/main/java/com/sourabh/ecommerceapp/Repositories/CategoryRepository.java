package com.sourabh.ecommerceapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sourabh.ecommerceapp.Model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long>
{
    public Category findByName(String name);

    @Query("Select c from Category c Where c.name = :name AND c.parentCategory.name = :parentCategoryName")
    public Category findByNameAndParentCategory(@Param(value = "name") String name, @Param(value = "parentCategoryName") String parentCategoryName);
}
