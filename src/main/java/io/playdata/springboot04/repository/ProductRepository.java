package io.playdata.springboot04.repository;

import io.playdata.springboot04.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // custom method 1
    List<Product> findByNameContaining(String keyword);

    // custom method 2
    List<Product> findByPriceGreaterThan(int price);

    // custom method 3
    List<Product> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findProductsByPriceRange(int startPrice, int endPrice);

    @Query(value = "SELECT * FROM products WHERE name LIKE %?1%", nativeQuery = true)
    List<Product> findProductsByNameLike(String keyword);

    @Query(value = "SELECT p FROM Product p WHERE p.description LIKE %?1% AND p.price < ?2")
    List<Product> findProductsByDescriptionAndPriceLessThan(String description, int price);

    // 해당 메소드를 사용하는 기능: 상품 검색, 할인 이벤트, 기간별 상품 조회 등
}