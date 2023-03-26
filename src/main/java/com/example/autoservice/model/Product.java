package com.example.autoservice.model;

import com.example.autoservice.model.abstraction.Offering;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends Offering {
}
