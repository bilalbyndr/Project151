package com.example.jwt.domain.Purchases;

import com.example.jwt.domain.Purchases.dto.PurchaseDTO;
import com.example.jwt.domain.Purchases.dto.PurchasesSummaryDTO;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserDetailsImpl;
import com.example.jwt.domain.user.dto.UserDTO;
import com.example.jwt.domain.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final UserMapper userMapper;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, UserMapper userMapper) {
        this.purchaseService = purchaseService;
        this.userMapper = userMapper;
    }

    @PostMapping("/buy")
    @PreAuthorize("hasAuthority('CAN_PLACE_ORDER')")
    public ResponseEntity createPurchase(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody PurchaseDTO purchaseDTO) {
      UUID userid=userDetails.getId();//  UUID userId=userDetails.getId();// Get the user ID from the JWT token
        purchaseService.createPurchase(userid, purchaseDTO);
        return  new ResponseEntity<>(HttpStatus.OK);}

    @GetMapping("/myHistory")
    @PreAuthorize("hasAuthority('CAN_RETRIEVE_PURCHASE_HISTORY')")
    public ResponseEntity<List<PurchasesSummaryDTO>> getTeaPurchaseSummary(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        UUID userId = userDetails.getId();

        List<PurchasesSummaryDTO> teaPurchaseSummary = purchaseService.getTeaPurchaseSummary(userId);

        return new ResponseEntity<>(teaPurchaseSummary, HttpStatus.OK);
    }

    @GetMapping("/HighestRevenue")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getTopCustomerOfTheMonth(){
            User topCustomer = purchaseService.getTopCustomerOfTheMonth();

        if (topCustomer != null) {
        UserDTO userDTO = userMapper.toDTO(topCustomer);
        return ResponseEntity.ok(userDTO);
    } else {
        return ResponseEntity.notFound().build();
    }}
    @GetMapping("/topcountry")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getTopCountryOfOriginLastXDays(@RequestParam(name = "days") int numberOfDays) {
        String topCountry = purchaseService.getTopCountryOfOriginLastXDays(numberOfDays);

        if (topCountry != null) {
            return ResponseEntity.ok(topCountry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    }
