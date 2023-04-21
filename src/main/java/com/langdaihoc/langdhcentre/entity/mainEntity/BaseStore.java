package com.langdaihoc.langdhcentre.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "stores")
public class BaseStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "store_id")
    private Long storeId;

    @Column(name ="store_name")
    private String storeName;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = true, referencedColumnName = "owner_id")
    private Owner owner;

    @OneToOne
    private Address address;

}
