package com.langdaihoc.langdhcentre.entity.subEntity;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "menus")
public class Menu {
    protected Menu(){}
    @Id
    @Column(name = "store_id", nullable = false)
    private Long menuId;

    @Column(name = "menu_name")
    private String menuName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private BaseStore store;

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Beverage> beverages = new ArrayList<>();

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Food> foods = new ArrayList<>();

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<OtherService> otherServices = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(menuId, menu.menuId) && Objects.equals(menuName, menu.menuName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, menuName);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                '}';
    }

    public void addBeverage(Beverage beverageInput){
        beverageInput.setMenu(this);
        if(this.beverages == null){
            this.beverages = new ArrayList<>();
        }
        this.beverages.add(beverageInput);
    }
    public void addFood(Food foodInput){
        foodInput.setMenu(this);
        if(this.foods == null){
            this.foods = new ArrayList<>();
        }
        this.foods.add(foodInput);
    }
    public void addOtherService(OtherService otherServiceInput){
        otherServiceInput.setMenu(this);
        if(this.otherServices == null){
            this.otherServices = new ArrayList<>();
        }
        this.otherServices.add(otherServiceInput);
    }
}
