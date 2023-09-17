package com.langdaihoc.langdhcentre.storeManagement.entity.store;

import com.langdaihoc.langdhcentre.storeManagement.entity.common.AbstractImage;
import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.BaseStore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "store_images")
public class StoreImage extends AbstractImage {
    protected StoreImage() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL , fetch =  FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private BaseStore store;
}
