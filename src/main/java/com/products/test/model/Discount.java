package com.products.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @Column(name = "discount_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amountOfDiscount;
}
