package com.langdaihoc.langdhcentre.storeManagement.entity.product;

import com.langdaihoc.langdhcentre.storeManagement.entity.auth.Customer;
import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_ratings")
public class ProductRating {
    /**
     * rating + customer + store = unique
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "score")
    private int score;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToMany(mappedBy = "productRating",cascade =  CascadeType.ALL, fetch =  FetchType.LAZY)
    private List<ProductRatingImage> productRatingImages;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "customer_id", referencedColumnName = "id")
    private Customer customer;
}
