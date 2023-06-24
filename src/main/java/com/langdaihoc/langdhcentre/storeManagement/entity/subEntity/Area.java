package com.langdaihoc.langdhcentre.storeManagement.entity.subEntity;

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
public class Area {
    protected Area(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id", nullable = false)
    private Long areaId;

    @Column(name = "area_name")
    private String nameArea;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "nearly_area",
            joinColumns = @JoinColumn(name = "area_id", referencedColumnName = "area_id"),
            inverseJoinColumns = @JoinColumn(name = "nearly_area_id", referencedColumnName = "area_id")
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
        return Objects.equals(areaId, area.areaId) && Objects.equals(nameArea, area.nameArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaId, nameArea);
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                ", nameArea='" + nameArea + '\'' +
                '}';
    }

    public void addNearlyArea(Area nearlyAreaInput) {
        if(this.nearAreas == null){
            this.nearAreas = new ArrayList<>();
        }
        this.nearAreas.add(nearlyAreaInput);
    }
}
