package com.example.jwt.domain.Purchases;

import com.example.jwt.domain.Products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PurchaseRepository  extends JpaRepository<Purchase, UUID> {
    @Query("SELECT p.product.tpe, SUM(p.quantity), COUNT(p), SUM(p.totalPrice) " +
            "FROM Purchase p WHERE p.user.id = :userId GROUP BY p.product.tpe")
    List<Object[]> getTeaPurchaseSummary(@Param("userId") UUID userId);
    @Query("SELECT p.user.id, SUM(p.totalPrice) " +
            "FROM Purchase p " +
            "WHERE p.createdAt >= DATE_TRUNC('MONTH', CURRENT_DATE) " +
            "GROUP BY p.user.id " +
            "ORDER BY SUM(p.totalPrice) DESC")
    List<Object[]> getTotalPurchaseAmountByUserInMonth(
    );
    @Query("SELECT p.product.country " +
            "FROM Purchase p " +
            "WHERE p.createdAt >= :startDate " +
            "GROUP BY p.product.country " +
            "ORDER BY COUNT(p) DESC " )
    List<String> findTopCountryOfOrigin(@Param("startDate") LocalDateTime startDate);

}
