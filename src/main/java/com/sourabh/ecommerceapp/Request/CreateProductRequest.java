package com.sourabh.ecommerceapp.Request;

import java.util.HashSet;
import java.util.Set;

import com.sourabh.ecommerceapp.Model.Size;

public class CreateProductRequest 
{
    private String title;
    private String description;
    private String brand;
    private String color;
    private int price;
    private int discountedPrice;
    private int discountPercentage;
    private int quantity;

    private Set<Size> size = new HashSet<>();

    private String imageUrl;
    private String toplevelCategory;
    private String secondlevelCategory;
    private String thirdlevelCategory;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getDiscountedPrice() {
        return discountedPrice;
    }
    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public int getDiscountPercentage() {
        return discountPercentage;
    }
    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Set<Size> getSize() {
        return size;
    }
    public void setSize(Set<Size> size) {
        this.size = size;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getToplevelCategory() {
        return toplevelCategory;
    }
    public void setToplevelCategory(String toplevelCategory) {
        this.toplevelCategory = toplevelCategory;
    }
    public String getSecondlevelCategory() {
        return secondlevelCategory;
    }
    public void setSecondlevelCategory(String secondlevelCategory) {
        this.secondlevelCategory = secondlevelCategory;
    }
    public String getThirdlevelCategory() {
        return thirdlevelCategory;
    }
    public void setThirdlevelCategory(String thirdlevelCategory) {
        this.thirdlevelCategory = thirdlevelCategory;
    }

}
