package com.example.autoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
    private String description;
    private LocalDate creationDate;
    private BigDecimal totalPrice;
    private LocalDate completionDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany
    @JoinColumn(name = "order_id")
    private Set<Service> services;
    @ManyToMany
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    public enum Status {
        ACCEPTED("accepted"),
        IN_PROGRESS("in_progress"),
        SUCCESSFUL("successful"),
        UNSUCCESSFUL("unsuccessful"),
        PAID("paid");

        private final String statusName;

        Status(String statusName) {
            this.statusName = statusName;
        }

        public static Status getStatus(String statusName) {
            for (Status status : Status.values()) {
                if (statusName.equals(status.statusName)) {
                    return status;
                }
            }
            throw new NoSuchElementException("Can't find status by status name" + statusName);
        }
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}

