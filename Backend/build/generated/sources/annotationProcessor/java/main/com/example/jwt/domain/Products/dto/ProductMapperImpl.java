package com.example.jwt.domain.Products.dto;

import com.example.jwt.domain.Products.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-27T09:48:13+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDTO.getId() );
        product.setDescription( String.valueOf( productDTO.getDescription() ) );
        product.setName( productDTO.getName() );
        product.setPrice( productDTO.getPrice() );
        product.setCountry( productDTO.getCountry() );
        product.setTpe( productDTO.getTpe() );
        product.setDateOfHarvest( productDTO.getDateOfHarvest() );

        return product;
    }
}
