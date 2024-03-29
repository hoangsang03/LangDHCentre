package com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity;

import com.langdaihoc.langdhcentre.storeManagement.common.StoreTypeConstant;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;


@Getter
@Setter
//@ToString(callSuper = true)
@Entity
@DiscriminatorValue("1")
public class FoodStore extends BaseStore{
    {
        this.setType(StoreTypeConstant.FOOD_STORE);
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
