package com.langdaihoc.langdhcentre.storeManagement.entity.subEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rating_images")
public class RatingImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_image_id", nullable = false)
    private Long ratingImageId;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "rating_id", referencedColumnName = "rating_id")
    private Rating rating;

    @Column(name = "image_url")
    private String url;

}
