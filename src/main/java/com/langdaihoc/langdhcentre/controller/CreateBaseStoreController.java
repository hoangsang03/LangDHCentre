package com.langdaihoc.langdhcentre.controller;

import com.langdaihoc.langdhcentre.common.ApiResponse;
import com.langdaihoc.langdhcentre.service.ICreateBaseStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
@Slf4j
public class CreateBaseStoreController {
    private static final String CLASS_NAME = "CreateBaseStoreController";
    private final ICreateBaseStoreService createBaseStoreService;

    @PostMapping
    public String createBaseStore(@RequestBody String createBaseStoreRequest){
        ApiResponse baseStoreResponse = this.createBaseStoreService.getApiResponse(createBaseStoreRequest);
        return this.createBaseStoreService.getResponseJson(baseStoreResponse);
    }
}
