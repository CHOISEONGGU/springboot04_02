package io.playdata.springboot04.service;

import io.playdata.springboot04.model.Product;
import io.playdata.springboot04.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> searchProductsByName(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    public List<Product> searchProductsByPrice(int price) {
        return productRepository.findByPriceGreaterThan(price);
    }

    public List<Product> searchProductsByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return productRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public List<Product> searchProductsByPriceRange(int startPrice, int endPrice) {
        return productRepository.findProductsByPriceRange(startPrice, endPrice);
    }

    public List<Product> searchProductsByNameLike(String keyword) {
        return productRepository.findProductsByNameLike(keyword);
    }

    public List<Product> searchProductsByDescriptionAndPriceLessThan(String description, int price) {
        return productRepository.findProductsByDescriptionAndPriceLessThan(description, price);
    }

    // 해당 메소드를 사용하는 기능: 상품 등록, 상품 검색 등
}