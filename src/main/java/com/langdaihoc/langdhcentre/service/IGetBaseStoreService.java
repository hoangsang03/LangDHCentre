package com.langdaihoc.langdhcentre.service;

import com.langdaihoc.langdhcentre.common.ApiRequest;
import com.langdaihoc.langdhcentre.common.ApiResponse;
import com.langdaihoc.langdhcentre.controller.form.GetBaseStoreRequest;
import com.langdaihoc.langdhcentre.controller.form.SearchBaseStoreRequest;
import com.langdaihoc.langdhcentre.controller.responses.BaseStoreResponse;
import com.langdaihoc.langdhcentre.dto.BaseStoreDTO;
import com.langdaihoc.langdhcentre.repository.common.Filter;
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
