package com.example.jwt.domain.Products;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product save(Product product);

    //Retrieve product or products
    Product findById(UUID id);
    List<Product> findAll(Pageable pageable);

    int getpriceById(UUID id);



    //Update product
    Product updateById(UUID id, Product entity) throws NoSuchElementException;

    //Delete product
    Void deleteById(UUID id) throws NoSuchElementException;

    //Helper methods
    boolean existsById(UUID id);

    Product findOrThrow(Optional<Product> optional) throws NoSuchElementException;

    //Aggregate methods
    Product findMostExpensive();
    void createProductsForTeaTypes();
}
