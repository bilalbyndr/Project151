package com.example.jwt.domain.Products;

import org.hibernate.annotations.GenericGenerator;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name="description")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name="country")
    private Country country;
    @Column (name="type")
    private Type tpe;
    @Column(name="DateOfHarvest")
    private Date DateOfHarvest;
    public Product() {
    }

    public Product(UUID id, String name, int price, Country country, Type tpe, Date dateOfHarvest,String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.country = country;
        this.tpe = tpe;
        DateOfHarvest = dateOfHarvest;
        this.description=description;
    }



    public enum Type{
        White ,Green, Oolong ,Black, Medicinal_Herbs
    }
    public enum Country{
        China, Taiwan, Japan
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Type getTpe() {
        return tpe;
    }

    public void setTpe(Type tpe) {
        this.tpe = tpe;
    }

    public Date getDateOfHarvest() {
        return DateOfHarvest;
    }

    public void setDateOfHarvest(Date dateOfHarvest) {
        DateOfHarvest = dateOfHarvest;
    }
}
