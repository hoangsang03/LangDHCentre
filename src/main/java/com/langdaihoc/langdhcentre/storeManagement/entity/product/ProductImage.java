package com.langdaihoc.langdhcentre.storeManagement.entity.product;

import com.langdaihoc.langdhcentre.storeManagement.entity.common.AbstractImage;
import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "product_images")
public class ProductImage extends AbstractImage {
    protected ProductImage() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL , fetch =  FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
