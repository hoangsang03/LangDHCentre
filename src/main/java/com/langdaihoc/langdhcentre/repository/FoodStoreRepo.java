package com.langdaihoc.langdhcentre.repository;

import com.langdaihoc.langdhcentre.entity.mainEntity.FoodStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodStoreRepo extends JpaRepository<FoodStore,Long> {

    @Query("select s from FoodStore s where s.storeId = ?1")
    Optional<FoodStore> findByStoreId(long storeId);
}
