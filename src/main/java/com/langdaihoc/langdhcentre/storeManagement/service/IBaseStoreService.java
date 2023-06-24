package com.langdaihoc.langdhcentre.storeManagement.service;

import com.langdaihoc.langdhcentre.storeManagement.controller.form.SearchBaseStoreRequest;
import com.langdaihoc.langdhcentre.storeManagement.controller.responses.BaseStoreResponse;
import com.langdaihoc.langdhcentre.storeManagement.dto.BaseStoreDTO;
import com.langdaihoc.langdhcentre.storeManagement.repository.common.Filter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBaseStoreService {
    List<BaseStoreDTO> getAllBaseStores();

    <T> T getSearchBaseStoreRequest(String jsonRequest) throws Exception;

    BaseStoreResponse getApiResponseForSearching(String jsonRequest);

    String getResponseJson(BaseStoreResponse baseStoreResponse);

    List<BaseStoreDTO> getBaseStoreDTOList(SearchBaseStoreRequest apiRequest);

    BaseStoreResponse getApiResponseForSearching(List<BaseStoreDTO> baseStoreDTOList);

    List<Filter> getFiltersForBaseStoreInput(SearchBaseStoreRequest.Input apiRequest);
    boolean validateRequest(SearchBaseStoreRequest apiRequest);

    BaseStoreResponse getDataOutput(SearchBaseStoreRequest apiRequest);
}
