package com.example.autoservice.repository;

import com.example.autoservice.model.Car;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Override
    @Query("FROM Car c "
            + "LEFT JOIN FETCH c.carOwner "
            + "WHERE c.id = :id")
    @NonNull
    Optional<Car> findById(@Param("id") @NonNull Long id);
}
