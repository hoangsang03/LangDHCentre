package com.langdaihoc.langdhcentre.entity.subEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories" )
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

}
