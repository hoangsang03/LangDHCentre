package com.langdaihoc.langdhcentre.controller;

import com.langdaihoc.langdhcentre.controller.responseentity.BaseStoreResponse;
import com.langdaihoc.langdhcentre.service.IBaseStoreService;
import com.langdaihoc.langdhcentre.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
@Slf4j
public class StoreController {
    private static final String CLASS_NAME = "StoreController";
    private final IBaseStoreService baseStoreService;
    private final ObjectMapperUtils objectMapperConverter;


    @GetMapping("")
    @CrossOrigin()
    public String getBaseStores(
            @RequestBody String jsonRequest
    ) {
        BaseStoreResponse baseStoreResponse = this.baseStoreService.getApiResponse(jsonRequest);
        return this.baseStoreService.getResponseJson(baseStoreResponse);
    }

    @GetMapping("/{id}")
    public String getBaseStore(@PathVariable String id) {

        return null;
    }
}
