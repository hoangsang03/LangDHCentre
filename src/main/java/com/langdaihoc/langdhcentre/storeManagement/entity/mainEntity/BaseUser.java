package com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "user")
    private List<Authority> authorities;
}
