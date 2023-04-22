package com.langdaihoc.langdhcentre.entity.subEntity;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.entity.mainEntity.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating {
    /**
     * rating + customer + store = unique
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rating_id;

    @Column(name = "rating_score")
    private int ratingScore;

    @Column(name = "rating_content")
    private String ratingContent;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "store_id", referencedColumnName = "store_id")
    private BaseStore store;

    @OneToMany(mappedBy = "rating",cascade =  CascadeType.ALL, fetch =  FetchType.LAZY)
    private List<RatingImage> ratingImages;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
}
