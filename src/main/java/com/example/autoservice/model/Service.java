package com.example.autoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "services")
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    private Mechanic mechanic;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PAID("paid"),
        UNPAID("unpaid");

        private final String statusName;

        Status(String statusName) {
            this.statusName = statusName;
        }

        public static Service.Status getStatus(String statusName) {
            for (Service.Status status : Service.Status.values()) {
                if (statusName.equals(status.statusName)) {
                    return status;
                }
            }
            throw new NoSuchElementException("Can't find status by status name" + statusName);
        }
    }
}
