package com.langdaihoc.langdhcentre.repository;

import com.langdaihoc.langdhcentre.entity.mainEntity.FoodStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodStoreRepo extends JpaRepository<FoodStore,Long> {
}
