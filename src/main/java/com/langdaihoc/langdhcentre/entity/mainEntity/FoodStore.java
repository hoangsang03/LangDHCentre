package com.langdaihoc.langdhcentre.entity.mainEntity;

import com.langdaihoc.langdhcentre.common.StoreTypeConstant;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
//@ToString(callSuper = true)
@Entity
public class FoodStore extends BaseStore{
    {
        this.setStoreType(StoreTypeConstant.FOOD_STORE);
        //
        this.setFoodType("food type");
    }
    public FoodStore(){

    }

    @Column(name = "food_type")
    private String foodType;


    @Override
    public String toString() {
        return "FoodStore{" +
                "foodType='" + foodType + '\'' +
                ", super=" + super.toString() +
                '}';
    }
}
