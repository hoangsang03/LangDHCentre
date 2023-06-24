package com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity;

import com.langdaihoc.langdhcentre.storeManagement.entity.subEntity.Rating;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "customers")
public class Customer {
    protected Customer(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Rating rating;

    @Column(name = "customer_name")
    private String customerName;

}
