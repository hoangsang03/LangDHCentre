package com.langdaihoc.langdhcentre.controller;

import com.langdaihoc.langdhcentre.controller.responses.BaseStoreResponse;
import com.langdaihoc.langdhcentre.dto.BaseStoreDTO;
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


//    @GetMapping
//    public String getBaseStores(@RequestBody String jsonRequest){
//        BaseStoreResponse baseStoreResponse = this.baseStoreService.getApiResponseFo(jsonRequest);
//        return this.baseStoreService.getResponseJson(baseStoreResponse);
//
//    }

    /**
     *
     * @param jsonRequest : json String request
     * @return
     */
    @PostMapping
    @CrossOrigin()
    public String search(
            @RequestBody String jsonRequest
    ) {
        BaseStoreResponse baseStoreResponse = this.baseStoreService.getApiResponseForSearching(jsonRequest);
        return this.baseStoreService.getResponseJson(baseStoreResponse);

    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String getBaseStore(@PathVariable String id) {

        return null;
    }

    /**
     *
     * @param baseStoreDTO
     * @return
     */
    @PostMapping
    public String createBaseStore(@RequestBody BaseStoreDTO baseStoreDTO){

        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteBaseStore(@PathVariable String id){

        return null;
    }
}
