package com.langdaihoc.langdhcentre.storeManagement.entity.store;

import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.storeManagement.entity.auth.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store_ratings")
public class StoreRating {
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
    @JoinColumn(nullable = false, name = "store_id", referencedColumnName = "id")
    private BaseStore store;

    @OneToMany(mappedBy = "rating",cascade =  CascadeType.ALL, fetch =  FetchType.LAZY)
    private List<StoreRatingImage> ratingImages;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "customer_id", referencedColumnName = "id")
    private Customer customer;
}
