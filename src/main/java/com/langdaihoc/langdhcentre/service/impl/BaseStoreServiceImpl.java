package com.langdaihoc.langdhcentre.service.impl;

import com.langdaihoc.langdhcentre.common.ApiRequest;
import com.langdaihoc.langdhcentre.dto.BaseStoreDTO;
import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.repository.BaseStoreRepo;
import com.langdaihoc.langdhcentre.service.IBaseStoreService;
import com.langdaihoc.langdhcentre.util.ModelMapperUtils;
import com.langdaihoc.langdhcentre.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BaseStoreServiceImpl implements IBaseStoreService {
    private static String CLASS_NAME = "BaseStoreServiceImpl";
    private final BaseStoreRepo baseStoreRepo;
    private final ObjectMapperUtils objectMapperUtils;


    @Override
    public List<BaseStoreDTO> getAllBaseStores() {
        log.info(CLASS_NAME + " - getAllBaseStores only get first 20 stores");
        int limit = 20;
        int offset = 0;
        List<BaseStore> stores = this.baseStoreRepo.getAllBaseStore(limit, offset);
        log.debug(CLASS_NAME + "getAllBaseStores : stores.size() " + stores.size());
        return ModelMapperUtils.mapAll(stores, BaseStoreDTO.class);
    }

    @Override
    public ApiRequest getApiRequest(String jsonRequest) {
        try {
            ApiRequest apiRequest = objectMapperUtils.convertJsonStringToObject(jsonRequest, ApiRequest.class);
            return apiRequest;
        } catch (Exception e) {
            log.error(CLASS_NAME + " - getApiRequest : ", e);
            throw new RuntimeException(e);
        }
    }
}
