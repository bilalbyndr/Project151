package com.example.jwt.domain.Purchases.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.Products.Product;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PurchaseDTO extends ExtendedDTO {
@NotNull
    private UUID productId;
@NotNull
    private int quantity;

    public PurchaseDTO(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
