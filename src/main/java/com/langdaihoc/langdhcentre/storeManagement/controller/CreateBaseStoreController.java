package com.langdaihoc.langdhcentre.storeManagement.controller;

import com.langdaihoc.langdhcentre.storeManagement.controller.responses.CreateBaseStoreResponse;
import com.langdaihoc.langdhcentre.storeManagement.service.ICreateBaseStoreService;
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
        // validate token
        CreateBaseStoreResponse createBaseStoreResponse = (CreateBaseStoreResponse) this.createBaseStoreService.getApiResponse(createBaseStoreRequest);
        return this.createBaseStoreService.getResponseJson(createBaseStoreResponse);
    }
}
