package com.langdaihoc.langdhcentre.repository;

import com.langdaihoc.langdhcentre.entity.mainEntity.CoffeeShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeShopRepo extends JpaRepository<CoffeeShop,Integer > {
}
