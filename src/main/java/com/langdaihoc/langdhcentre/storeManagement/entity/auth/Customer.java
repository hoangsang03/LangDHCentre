package com.langdaihoc.langdhcentre.storeManagement.entity.auth;

import com.langdaihoc.langdhcentre.storeManagement.entity.store.StoreRating;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Entity
@Table(name = "customers")
public class Customer {
    protected Customer(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long customerId;

    @OneToMany(mappedBy = "customer",cascade =  CascadeType.ALL, fetch =  FetchType.LAZY)
    private List<StoreRating> ratings;

    @Column(name = "name")
    private String name;

}
