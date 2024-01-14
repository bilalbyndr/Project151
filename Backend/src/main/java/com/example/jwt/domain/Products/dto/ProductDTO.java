package com.example.jwt.domain.Products.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.Products.Product;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class ProductDTO extends ExtendedDTO {
    @NotNull
    private String name;
    private long description;
    @NotNull
    private Product.Country country;
    @NotNull
    private Product.Type tpe;
    @NotNull
    private Date DateOfHarvest;
    @Max(100)
    private int price;

    public ProductDTO( String name, long description, Product.Country country, Product.Type tpe, Date dateOfHarvest, int price) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.tpe = tpe;
        DateOfHarvest = dateOfHarvest;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDescription() {
        return description;
    }

    public void setDescription(long description) {
        this.description = description;
    }

    public Product.Country getCountry() {
        return country;
    }

    public void setCountry(Product.Country country) {
        this.country = country;
    }

    public Product.Type getTpe() {
        return tpe;
    }

    public void setTpe(Product.Type tpe) {
        this.tpe = tpe;
    }

    public Date getDateOfHarvest() {
        return DateOfHarvest;
    }

    public void setDateOfHarvest(Date dateOfHarvest) {
        DateOfHarvest = dateOfHarvest;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
