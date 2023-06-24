package com.langdaihoc.langdhcentre.storeManagement.repository;

import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.CoffeeShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeShopRepo extends JpaRepository<CoffeeShop,Integer > {
}
