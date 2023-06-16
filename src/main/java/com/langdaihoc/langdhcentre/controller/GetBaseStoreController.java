package com.langdaihoc.langdhcentre.controller;

import com.langdaihoc.langdhcentre.common.ApiResponse;
import com.langdaihoc.langdhcentre.controller.responseentity.BaseStoreResponse;
import com.langdaihoc.langdhcentre.service.IGetBaseStoreService;
import com.langdaihoc.langdhcentre.util.ObjectMapperUtils;
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
    private final ObjectMapperUtils objectMapperConverter;


    @GetMapping
    public String getBaseStores(@RequestBody String jsonRequest){
        ApiResponse baseStoreResponse = this.getBaseStoreService.getApiResponse(jsonRequest);
        return this.getBaseStoreService.getResponseJson(baseStoreResponse);

    }


}
