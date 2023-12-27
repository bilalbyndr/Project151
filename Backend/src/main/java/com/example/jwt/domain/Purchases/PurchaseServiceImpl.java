package com.example.jwt.domain.Purchases;

import com.example.jwt.domain.Products.Product;
import com.example.jwt.domain.Products.ProductService;
import com.example.jwt.domain.Purchases.dto.PurchaseDTO;
import com.example.jwt.domain.Purchases.dto.PurchasesSummaryDTO;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService; // Inject ProductService


    public ResponseEntity createPurchase(UUID userId, PurchaseDTO purchaseRequest) {
        // Retrieve the user from the UserRepository
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            // Handle the case where the user is not found
            // You can return an error response or throw an exception
            // For simplicity, this example returns null
            return null;
        }
        Product product =productService.findById(purchaseRequest.getProductId());
        if(product==null){
            return null;
        }

        // Calculate the total price based on the user's rank and quantity
        double totalPrice = calculateTotalPrice(user.getRank(), purchaseRequest.getQuantity(), purchaseRequest.getProductId());

        //changing the seeds and total spend and users rank
        double UpdatedSpend= user.getTotalSpend()+totalPrice;
        int nbofseeds = (int) UpdatedSpend/2;
        User.Rank newRank=updateRank(nbofseeds);
        user.setRank(newRank);
        user.setSeeds(nbofseeds);
        user.setTotalSpend(UpdatedSpend);
        // Create the purchase entity
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setTotalPrice(totalPrice);
        purchase.setProduct(product);
        purchase.setQuantity(purchaseRequest.getQuantity());
        // Save the purchase to the database
        purchaseRepository.save(purchase);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private double calculateTotalPrice(User.Rank userRank, int quantity, UUID productId) {
        // Define the rank-based discount percentages
        Map<String, Double> rankDiscounts = Map.of(
                "Bronze", 0.0,
                "Silver", 0.04,
                "Gold", 0.07,
                "Platinum", 0.09,
                "Diamond", 0.11
        );

        // Get the discount percentage for the user's rank
        double discountPercentage = rankDiscounts.getOrDefault(userRank, 0.0);

        // Calculate the total price after applying the discount and product price per 100g
        double totalPrice = calculateTotalPriceForProduct(productId, quantity);

        // Apply the discount
        totalPrice -= totalPrice * discountPercentage;

        return totalPrice;
    }

    private double calculateTotalPriceForProduct(UUID productId, int quantity) {
        // Retrieve the product price per 100g from the ProductService
        double pricePer100g = productService.getpriceById(productId);

        // Calculate the total price based on the price per 100g and quantity
        double totalPrice = (pricePer100g * quantity) / 100.0;

        return totalPrice;
    }
    public User.Rank updateRank(int numberOfSeeds) {
        if (numberOfSeeds >= 300) return User.Rank.DIAMOND;
        if (numberOfSeeds >= 140) return User.Rank.PLATINUM;
        if (numberOfSeeds >= 60) return User.Rank.GOLD;
        if (numberOfSeeds >= 20) return User.Rank.SILVER;
        return User.Rank.BRONZE;
    }




    public List<PurchasesSummaryDTO> getTeaPurchaseSummary(UUID userId) {
        List<Object[]> results = purchaseRepository.getTeaPurchaseSummary(userId);
        List<PurchasesSummaryDTO> purchaseSummaries = new ArrayList<>();

        for (Object[] row : results) {
            Product.Type teaType = (Product.Type) row[0];
            int numberOfProducts = ((Number) row[1]).intValue();
            int count = ((Number) row[2]).intValue(); // Get the count
            double seeds = ((Number) row[3]).doubleValue() / 2.0;// Divide by 2

            PurchasesSummaryDTO summaryDTO = new PurchasesSummaryDTO(teaType,count, numberOfProducts, seeds);
            purchaseSummaries.add(summaryDTO);
        }

        return purchaseSummaries;
    }

    public User getTopCustomerOfTheMonth() {
        List<Object[]> topCustomerList = purchaseRepository.getTotalPurchaseAmountByUserInMonth();
        if (!topCustomerList.isEmpty()) {
            Object[] topCustomer = topCustomerList.get(0);
            UUID userId = (UUID) topCustomer[0];
            return userRepository.findById(userId).orElse(null);
        } else {
            return null; // Handle the case when there are no purchases in the past month
        }
    }
    @Override
    public String getTopCountryOfOriginLastXDays(int numberOfDays) {
        // Calculate the start date as X days ago from the current date
        LocalDateTime startDate = LocalDateTime.now().minusDays(numberOfDays);

        // Query the database to find the country of origin with the most orders in the specified time frame
        List<String> topCountry = purchaseRepository.findTopCountryOfOrigin(startDate);
        if (!topCountry.isEmpty()) {
            String top = topCountry.get(0);

            return top;
        } else {
            return null; // Handle the case when there are no purchases in the past month
        }

    }




}
