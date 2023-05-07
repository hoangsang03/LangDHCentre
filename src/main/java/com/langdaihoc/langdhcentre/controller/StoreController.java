package com.langdaihoc.langdhcentre.controller;

import com.langdaihoc.langdhcentre.common.ApiRequest;
import com.langdaihoc.langdhcentre.common.ApiRespond;
import com.langdaihoc.langdhcentre.dto.BaseStoreDTO;
import com.langdaihoc.langdhcentre.service.IBaseStoreService;
import com.langdaihoc.langdhcentre.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("rawtypes")
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
    public ResponseEntity<ApiRespond> getBaseStores(
            @RequestBody String jsonRequest
    ) {
        ApiRequest apiRequest = this.baseStoreService.getApiRequest(jsonRequest);
        log.debug("apiRequest : " + apiRequest);
        String tokenReq = apiRequest.getToken();

        ApiRespond<List<BaseStoreDTO>> apiRespond = new ApiRespond<>();
        List<BaseStoreDTO> stores = this.baseStoreService.getAllBaseStores();
        log.debug(CLASS_NAME + " getBaseStores - stores.size(): " + stores.size());
        apiRespond.setRespond(stores);
        apiRespond.getToken().setToken(tokenReq);
        log.debug(CLASS_NAME + " getBaseStores : ApiRespond " + apiRespond);

        return ResponseEntity.status(HttpStatus.OK).body(apiRespond);
    }
}
