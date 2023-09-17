package com.langdaihoc.langdhcentre.storeManagement.entity.address;

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
@Table( name = "areas")
public class Area extends AbstractEntity {
    protected Area(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "nearly_area",
            joinColumns = @JoinColumn(name = "Id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "nearly_area_id", referencedColumnName = "id")
    )
    @Builder.Default
    private List<Area> nearAreas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area", fetch = FetchType.LAZY)
    private List<BaseStore> stores;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return Objects.equals(id, area.id) && Objects.equals(name, area.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + id +
                ", nameArea='" + name + '\'' +
                '}';
    }

    public void addNearlyArea(Area nearlyAreaInput) {
        if(this.nearAreas == null){
            this.nearAreas = new ArrayList<>();
        }
        this.nearAreas.add(nearlyAreaInput);
    }
}
