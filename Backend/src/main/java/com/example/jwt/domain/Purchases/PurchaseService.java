package com.example.jwt.domain.Purchases;

import com.example.jwt.domain.Products.Product;
import com.example.jwt.domain.Purchases.dto.PurchaseDTO;
import com.example.jwt.domain.Purchases.dto.PurchasesSummaryDTO;
import com.example.jwt.domain.user.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public interface PurchaseService {
    ResponseEntity createPurchase(UUID userId, PurchaseDTO purchaseRequest);
    List<PurchasesSummaryDTO> getTeaPurchaseSummary(UUID userId);
    User getTopCustomerOfTheMonth();
    String getTopCountryOfOriginLastXDays(int days);


}
