package com.example.jwt.domain.Purchases;

import com.example.jwt.core.generic.ExtendedAuditEntity;
import com.example.jwt.domain.Products.Product;
import com.example.jwt.domain.user.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "purchase")
public class Purchase extends ExtendedAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; // Unique UUID for the purchase

    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign key for the user
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id") // Foreign key for the product
    private Product product;
@Column(name="quantity")
    private int quantity;
@Column(name="totalPrice")
    private double totalPrice;

    public Purchase(UUID id, User user, Product product, int quantity, double totalPrice) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Purchase() {

    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
