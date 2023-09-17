package com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity;

import com.langdaihoc.langdhcentre.storeManagement.entity.common.AbstractEntity;
import com.langdaihoc.langdhcentre.storeManagement.entity.product.ProductCategory;
import com.langdaihoc.langdhcentre.storeManagement.entity.product.ProductImage;
import com.langdaihoc.langdhcentre.storeManagement.entity.product.ProductRating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {
    protected Product(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "information")
    private String information;

    @Column(name = "barCode")
    private String barCode;

    @Column(name = "weight")
    private double weight;

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "promotional_information")
    private String promotionalInformation;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "sold_quantity")
    private int soldQuantity;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "discount")
    private double discount;

    @Column(name = "is_availability")
    private boolean isAvailability;

    @ManyToOne(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "store_id", referencedColumnName = "id")
    private BaseStore store;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Builder.Default
    private List<ProductCategory> productCategories = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<ProductRating> productRatings = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<ProductImage> productImages = new ArrayList<>();
}
