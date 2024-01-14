package com.example.jwt.domain.Products.dto;

import com.example.jwt.core.generic.ExtendedMapper;
import com.example.jwt.domain.Products.Product;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)


public interface ProductMapper  {
    Product productDTOToProduct(ProductDTO productDTO);
}
