package com.ecommerce.productsbackend.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.productsbackend.model.Product;
import com.ecommerce.productsbackend.repo.ProductRepository;
import com.ecommerce.productsbackend.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
    public Product addProduct(Product product) {
        log.debug("Adding product: {}", product);
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        log.debug("Updating product: {}", product);
        return this.productRepository.save(product);
    }

    @Override
    public Set<Product> getProducts() {
        log.debug("Fetching all products");
        return new LinkedHashSet<>(this.productRepository.findAll());
    }

    @Override
    public Product getProduct(Integer productId) {
        log.debug("Fetching product with ID: {}", productId);
        return this.productRepository.findById(productId).orElse(null);
    }

    @Override
    public void deleteProduct(Integer productId) {
        log.debug("Deleting product with ID: {}", productId);
        this.productRepository.deleteById(productId);
    }

    @Override
    public Set<Product> getSearchProduct(String str) {
        log.debug("Searching products with string: {}", str);

        Set<Product> searchedItems = new HashSet<>();
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(str);

        List<String> words = new ArrayList<>();
        while (m.find()) {
            words.add(m.group());
            log.debug("Found search word: {}", m.group());
        }

        for (String word : words) {
            searchedItems.addAll(this.productRepository.getSearchProducts(word));
        }

        return searchedItems;
    }
}