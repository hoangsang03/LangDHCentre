package com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity;

import com.langdaihoc.langdhcentre.storeManagement.common.StoreTypeConstant;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("2")
public class CoffeeShop extends BaseStore {
    {
        this.setType(StoreTypeConstant.COFFEE_SHOP);
        this.setCoffeeType("coffee type");
    }

    public CoffeeShop() {
    }

    @Column(name = "coffee_type")
    private String coffeeType;

    @Override
    public String toString() {
        return "CoffeeShop{" +
                "coffeeType='" + coffeeType + '\'' +
                ", storeType=" + type +
                ", super=" + super.toString() +
                '}';
    }
}
