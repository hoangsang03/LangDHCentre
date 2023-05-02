package com.langdaihoc.langdhcentre.entity.mainEntity;

import com.langdaihoc.langdhcentre.common.StoreTypeConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
public class CoffeeShop extends BaseStore {
    {
        this.setStoreType(StoreTypeConstant.COFFEE_SHOP);
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
                ", storeType=" + storeType +
                ", super=" + super.toString() +
                '}';
    }
}
