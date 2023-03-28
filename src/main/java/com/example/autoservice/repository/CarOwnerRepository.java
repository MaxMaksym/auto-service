package com.example.autoservice.repository;

import com.example.autoservice.model.CarOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {
    @Query("SELECT COUNT(or) "
            + "FROM Order o "
            + "JOIN o.car c "
            + "JOIN c.carOwner co "
            + "JOIN co.orders or "
            + "WHERE o.id = :orderId")
    int getAmountOfCarOwnerOrders(@Param("orderId") Long orderId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE orders o SET car_owner_id = :ownerId "
            + "WHERE o.id = :orderId", nativeQuery = true)
    void addOrderToOwner(@Param("orderId") Long orderId, @Param("ownerId") Long ownerId);
}
