package com.example.autoservice.repository;

import com.example.autoservice.model.Service;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query("SELECT s "
            + "FROM Service s "
            + "JOIN Mechanic m ON s.mechanic.id = m.id "
            + "WHERE s.wasPaidToMechanic = false AND m.id = :mechanicId")
    List<Service> findAllByMechanicId(@Param("mechanicId") Long mechanicId);

    @Override
    @Query("SELECT s "
            + "FROM Service s "
            + "JOIN FETCH s.mechanic "
            + "WHERE s.id = :id")
    @NonNull
    Optional<Service> findById(@Param("id") @NonNull Long id);
}
