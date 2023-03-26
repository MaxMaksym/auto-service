package com.example.autoservice.repository;

import com.example.autoservice.model.Mechanic;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
    @Query("SELECT SUM(s.price) * 0.4 "
            + "FROM Mechanic m "
            + "JOIN Service s ON s.mechanic.id = m.id "
            + "WHERE m.id = :id "
            + "AND s.wasPaidToMechanic = false")
    BigDecimal calculateSalary(@Param("id") Long id);
}
