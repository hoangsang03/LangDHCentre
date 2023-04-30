package com.langdaihoc.langdhcentre.controller;

import com.langdaihoc.langdhcentre.dto.StoreDTO;
import com.langdaihoc.langdhcentre.entity.mainEntity.FoodStore;
import com.langdaihoc.langdhcentre.repository.FoodStoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoreController {

    private final FoodStoreRepo foodStoreRepo;

    @Autowired
    public StoreController(FoodStoreRepo foodStoreRepo) {
        this.foodStoreRepo = foodStoreRepo;
    }
    @GetMapping("/stores")
    @CrossOrigin()
    public ResponseEntity<StoreDTO> getStores() {

        List<FoodStore> foodStoreByByName = this.foodStoreRepo.findFoodStoreByByName("");
        FoodStore foodStore = foodStoreByByName.get(0);

        StoreDTO storeDTO = new StoreDTO();
        StoreDTO.builder()
                .isStarted(foodStore.isStarted())
                .isShutdown(foodStore.isShutdown())
                .isOpening(foodStore.isOpening())
                .isHidden(foodStore.isHidden())
                .isAutoOpenSetting(foodStore.isAutoOpenSetting())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(storeDTO);
    }
}
