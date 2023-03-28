package com.example.autoservice.repository;

import com.example.autoservice.model.Order;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    @Query("SELECT o "
            + "FROM Order o "
            + "LEFT JOIN FETCH o.services "
            + "LEFT JOIN FETCH o.products "
            + "LEFT JOIN FETCH o.car WHERE o.id = :id")
    @NonNull
    Optional<Order> findById(@Param("id") @NonNull Long id);

    @Query("SELECT DISTINCT o "
            + "FROM CarOwner co "
            + "LEFT JOIN co.orders o "
            + "LEFT JOIN FETCH o.services "
            + "LEFT JOIN FETCH o.products "
            + "WHERE co.id = :carOwnerId")
    List<Order> findAllByCarOwnerId(@Param("carOwnerId") Long carOwnerId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE orders o SET car_owner_id = :carOwnerId "
            + "WHERE o.id = :orderId", nativeQuery = true)
    void addCarOwner(@Param("orderId") Long orderId, @Param("carOwnerId") Long carOwnerId);
}
