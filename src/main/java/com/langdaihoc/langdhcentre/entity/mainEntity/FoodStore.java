package com.langdaihoc.langdhcentre.entity.mainEntity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@Entity
public class FoodStore extends BaseStore{
    protected FoodStore(){}
}
