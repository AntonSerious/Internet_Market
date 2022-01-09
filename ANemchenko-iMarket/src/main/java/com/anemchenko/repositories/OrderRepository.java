package com.anemchenko.repositories;

import com.anemchenko.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.username = :username")
    @EntityGraph(value = "orders.for-front")
    List<Order> findByUser_Username(String username);

    @Query("select count (i) from Order o inner join o.items i where o.user.username = :userName and i.product.productId = :productId")
    int totalProductsBought(String userName, Long productId);
}
