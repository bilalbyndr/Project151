package com.example.jwt.domain.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product entity) {
        return repository.save(entity);
    }

    @Override
    public Product findById(UUID id) {
        return findOrThrow(repository.findById(id));
    }
    //my implimentation
    public int getpriceById(UUID id){
        return findById(id).getPrice();
    }

    @Override
    public List<Product> findAll(Pageable pageable) {
        Page<Product> pagedProducts = repository.findAll(pageable);
        return pagedProducts.hasContent() ? pagedProducts.getContent() : List.of();
    }

    @Override
    public Product updateById(UUID id, Product entity) throws NoSuchElementException {
        if (repository.existsById(id)) {
            entity.setId(id);
            return repository.save(entity);
        } else {
            throw new NoSuchElementException(String.format("Product with ID '%s' could not be found", id));
        }
    }

    @Override
    public Void deleteById(UUID id) throws NoSuchElementException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException(String.format("Product with ID '%s' could not be found", id));
        }
        return null;
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public Product findOrThrow(Optional<Product> optional) throws NoSuchElementException {
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NoSuchElementException("No value present");
        }
    }

    // As soon as we got a database connected to our application, we can fetch the most expensive product from the database
    // and return it. For now, we just return the most expensive product from a list of products!
    @Override
    public Product findMostExpensive() {

       // return repository.findMaxPriceNativeQuery();
        return null;
    }
    //my impl
    public void createProductsForTeaTypes() {
        // Create and save two products for each type of tea

        // White Tea
        Product whiteTea1 = new Product(UUID.randomUUID(), "White Tea - Premium Blend", 10, Product.Country.China, Product.Type.White, new Date(), "A premium blend of white tea leaves.");
        Product whiteTea2 = new Product(UUID.randomUUID(), "White Tea - Jasmine Infused", 12, Product.Country.Taiwan, Product.Type.White, new Date(), "White tea infused with fragrant jasmine flowers.");
        repository.save(whiteTea1);
        repository.save(whiteTea2);

        // Green Tea
        Product greenTea1 = new Product(UUID.randomUUID(), "Green Tea - Dragonwell", 8, Product.Country.China, Product.Type.Green, new Date(), "Dragonwell green tea with a delicate flavor.");
        Product greenTea2 = new Product(UUID.randomUUID(), "Green Tea - Sencha", 9, Product.Country.Japan, Product.Type.Green, new Date(), "Japanese sencha green tea.");
        repository.save(greenTea1);
        repository.save(greenTea2);

        // Oolong Tea
        Product oolongTea1 = new Product(UUID.randomUUID(), "Oolong Tea - Tie Guan Yin", 15, Product.Country.China, Product.Type.Oolong, new Date(), "Tie Guan Yin oolong tea, a semi-oxidized tea.");
        Product oolongTea2 = new Product(UUID.randomUUID(), "Oolong Tea - Oriental Beauty", 18, Product.Country.Taiwan, Product.Type.Oolong, new Date(), "Oriental Beauty oolong tea with a fruity aroma.");
        repository.save(oolongTea1);
        repository.save(oolongTea2);

        // Black Tea
        Product blackTea1 = new Product(UUID.randomUUID(), "Black Tea - English Breakfast", 14, Product.Country.China, Product.Type.Black, new Date(), "Classic English Breakfast black tea.");
        Product blackTea2 = new Product(UUID.randomUUID(), "Black Tea - Earl Grey", 16, Product.Country.Taiwan, Product.Type.Black, new Date(), "Earl Grey black tea with bergamot flavor.");
        repository.save(blackTea1);
        repository.save(blackTea2);

        // Medicinal Herbs Tea
        Product medicinalHerbsTea1 = new Product(UUID.randomUUID(), "Medicinal Herbs Tea - Chamomile", 25, Product.Country.Taiwan, Product.Type.Medicinal_Herbs, new Date(), "Chamomile herbal tea for relaxation.");
        Product medicinalHerbsTea2 = new Product(UUID.randomUUID(), "Medicinal Herbs Tea - Peppermint", 28, Product.Country.Japan, Product.Type.Medicinal_Herbs, new Date(), "Peppermint herbal tea with a refreshing taste.");
        repository.save(medicinalHerbsTea1);
        repository.save(medicinalHerbsTea2);
    }

}
