package com.langdaihoc.langdhcentre.storeManagement.entity.store;

import com.langdaihoc.langdhcentre.storeManagement.entity.common.AbstractImage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "store_rating_images")
public class StoreRatingImage extends AbstractImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "rating_id", referencedColumnName = "id")
    private StoreRating rating;
}
