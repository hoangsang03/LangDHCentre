package com.langdaihoc.langdhcentre.storeManagement.service;

import com.langdaihoc.langdhcentre.storeManagement.common.ApiRequest;
import com.langdaihoc.langdhcentre.storeManagement.common.ApiResponse;
import com.langdaihoc.langdhcentre.storeManagement.controller.form.GetBaseStoreRequest;
import com.langdaihoc.langdhcentre.storeManagement.controller.form.SearchBaseStoreRequest;
import com.langdaihoc.langdhcentre.storeManagement.controller.responses.BaseStoreResponse;
import com.langdaihoc.langdhcentre.storeManagement.dto.BaseStoreDTO;
import com.langdaihoc.langdhcentre.storeManagement.repository.common.Filter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IGetBaseStoreService {
    List<Filter> getFiltersForBaseStoreInput(GetBaseStoreRequest.Input baseStoreReq);

    List<BaseStoreDTO> getAllBaseStores();

    ApiResponse getApiResponse(String jsonRequest);

    ApiResponse getDataOutput(GetBaseStoreRequest apiRequest);

    String getResponseJson(ApiResponse baseStoreResponse);

    List<BaseStoreDTO> getBaseStoreDTOList(SearchBaseStoreRequest apiRequest);

    List<BaseStoreDTO> getBaseStoreDTOList(ApiRequest baseStoreReq);

    List<BaseStoreDTO> getBaseStoreDTOList(GetBaseStoreRequest baseStoreReq);

    BaseStoreResponse getApiResponse(List<BaseStoreDTO> baseStoreDTOList);

    List<Filter> getFiltersForBaseStoreInput(SearchBaseStoreRequest.Input apiRequest);

    boolean validateRequest(SearchBaseStoreRequest apiRequest);

    BaseStoreResponse getDataOutput(SearchBaseStoreRequest apiRequest);

    ApiRequest getApiRequest(String jsonRequest, Class targetClass);
}
