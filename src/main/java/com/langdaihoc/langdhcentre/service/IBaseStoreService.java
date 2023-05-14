package com.langdaihoc.langdhcentre.service;

import com.langdaihoc.langdhcentre.controller.form.GetBaseStoreRequest;
import com.langdaihoc.langdhcentre.controller.responseentity.BaseStoreResponse;
import com.langdaihoc.langdhcentre.dto.BaseStoreDTO;
import com.langdaihoc.langdhcentre.repository.common.Filter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBaseStoreService {
    List<BaseStoreDTO> getAllBaseStores();

    <T> T getApiRequest(String jsonRequest) throws Exception;

    BaseStoreResponse getApiResponse(String jsonRequest);

    String getResponseJson(BaseStoreResponse baseStoreResponse);

    List<BaseStoreDTO> getBaseStoreDTOList(GetBaseStoreRequest apiRequest);

    BaseStoreResponse getApiResponse(List<BaseStoreDTO> baseStoreDTOList);

    List<Filter> getFiltersForBaseStoreInput(GetBaseStoreRequest.Input apiRequest);
    boolean validateRequest(GetBaseStoreRequest apiRequest);

    BaseStoreResponse getDataOutput(GetBaseStoreRequest apiRequest);
}
