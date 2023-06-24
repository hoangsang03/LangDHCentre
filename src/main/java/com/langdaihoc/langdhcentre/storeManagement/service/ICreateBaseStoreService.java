package com.langdaihoc.langdhcentre.storeManagement.service;

import com.langdaihoc.langdhcentre.storeManagement.common.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface ICreateBaseStoreService {
    ApiResponse getApiResponse(String createBaseStoreRequest);

    String getResponseJson(ApiResponse baseStoreResponse);
}
