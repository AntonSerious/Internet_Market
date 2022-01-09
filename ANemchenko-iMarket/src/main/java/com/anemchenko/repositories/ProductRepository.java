package com.anemchenko.repositories;

import com.anemchenko.model.Product;
import com.anemchenko.model.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByPriceGreaterThanEqual(int min);
    List<Product> findAllByPriceLessThanEqual(int max);
    List<Product> findAllByPriceBetween(int min, int max);

    @Query("select c from ProductComment c where c.product.productId = :productId")
    List<ProductComment> findAllComments(Long productId);

    @Query("select count (c) from ProductComment c where c.product.productId = :id and c.user.username = :name")
    int totalCommentSent(String name, Long id);
}
