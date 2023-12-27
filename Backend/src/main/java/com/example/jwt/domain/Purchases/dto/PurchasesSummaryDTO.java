package com.example.jwt.domain.Purchases.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.Products.Product;

public class PurchasesSummaryDTO extends ExtendedDTO {
    private Product.Type teaType;
    private int count;
    private int numberOfProducts;
    private Double seeds;


    public PurchasesSummaryDTO(Product.Type teaType, int count, int numberOfProducts, Double seeds) {
        this.teaType = teaType;
        this.count = count;
        this.numberOfProducts = numberOfProducts;
        this.seeds = seeds;
    }

    public Product.Type getTeaType() {
        return teaType;
    }

    public void setTeaType(Product.Type teaType) {
        this.teaType = teaType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public Double getSeeds() {
        return seeds;
    }

    public void setSeeds(Double seeds) {
        this.seeds = seeds;
    }
}
