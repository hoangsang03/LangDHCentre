package com.langdaihoc.langdhcentre.storeManagement.controller;

import com.langdaihoc.langdhcentre.storeManagement.common.ApiResponse;
import com.langdaihoc.langdhcentre.storeManagement.service.IGetBaseStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
@Slf4j
public class GetBaseStoreController {
    private static final String CLASS_NAME = "GetBaseStoreController";
    private final IGetBaseStoreService getBaseStoreService;


    @GetMapping
    public String getBaseStores(@RequestBody String jsonRequest){
        ApiResponse baseStoreResponse = this.getBaseStoreService.getApiResponse(jsonRequest);
        return this.getBaseStoreService.getResponseJson(baseStoreResponse);

    }


}
