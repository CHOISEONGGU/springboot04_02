package io.playdata.springboot04.controller;

import io.playdata.springboot04.model.Product;
import io.playdata.springboot04.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping(params = "keyword")
    public List<Product> searchProductsByName(@RequestParam String keyword) {
        return productService.searchProductsByName(keyword);
    }

    @GetMapping(params = "price")
    public List<Product> searchProductsByPrice(@RequestParam int price) {
        return productService.searchProductsByPrice(price);
    }

    @GetMapping(params = {"startDate", "endDate"})
    public List<Product> searchProductsByDate(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime endDate) {
        return productService.searchProductsByDate(startDate, endDate);
    }

    @GetMapping(params = {"startPrice", "endPrice"})
    public List<Product> searchProductsByPriceRange(@RequestParam int startPrice, @RequestParam int endPrice) {
        return productService.searchProductsByPriceRange(startPrice, endPrice);
    }

    @GetMapping(params = "nameLike")
    public List<Product> searchProductsByNameLike(@RequestParam String nameLike) {
        return productService.searchProductsByNameLike(nameLike);
    }

    @GetMapping(params = {"description", "price"})
    public List<Product> searchProductsByDescriptionAndPriceLessThan(@RequestParam String description, @RequestParam int price) {
        return productService.searchProductsByDescriptionAndPriceLessThan(description, price);
    }
}
/*
상품 등록 API
    Title: Add Product
    URL: POST http://localhost:8080/products
    Body:
    {
        "name": "Apple iPhone 13",
        "description": "The latest iPhone model from Apple",
        "price": 1099000
    }
상품 이름으로 검색 API
    Title: Search Products by Name
    URL: GET http://localhost:8080/products?keyword=iphone
가격으로 상품 검색 API
    Title: Search Products by Price
    URL: GET http://localhost:8080/products?price=1000000
등록일자로 상품 검색 API
    Title: Search Products by Date
    URL: GET http://localhost:8080/products?startDate=2022-01-01T00:00:00&endDate=2022-12-31T23:59:59
가격 범위로 상품 검색 API
    Title: Search Products by Price Range
    URL: GET http://localhost:8080/products?startPrice=500000&endPrice=1000000
이름 부분 일치로 상품 검색 API
    Title: Search Products by Name Like
    URL: GET http://localhost:8080/products?nameLike=iphone
설명과 가격 조건으로 상품 검색 API
    Title: Search Products by Description and Price
    URL: GET http://localhost:8080/products?description=latest&price=1000000
 */