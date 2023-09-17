package com.langdaihoc.langdhcentre.storeManagement.entity.store;

import com.langdaihoc.langdhcentre.storeManagement.entity.common.AbstractEntity;
import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.BaseStore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "store_categories" )
public class StoreCategory extends AbstractEntity {
    protected StoreCategory() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column( name = "name")
    private String name;

    @ManyToMany(mappedBy = "storeCategories", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<BaseStore> stores = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "store_category_family",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id", referencedColumnName = "id")
    )
    @Builder.Default
    private List<StoreCategory> storeCategories = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreCategory category = (StoreCategory) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + id +
                ", category_name='" + name + '\'' +
                '}';
    }
}
