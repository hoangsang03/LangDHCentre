package com.langdaihoc.langdhcentre.entity.mainEntity;

import com.langdaihoc.langdhcentre.entity.subEntity.Rating;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Entity
@Table(name = "customers")
public class Customer {
    protected Customer(){};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Rating rating;

    @Column(name = "customer_name")
    private String customerName;

}
