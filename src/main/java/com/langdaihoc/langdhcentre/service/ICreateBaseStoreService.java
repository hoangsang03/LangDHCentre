package com.langdaihoc.langdhcentre.service;

import com.langdaihoc.langdhcentre.common.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface ICreateBaseStoreService {
    ApiResponse getApiResponse(String createBaseStoreRequest);

    String getResponseJson(ApiResponse baseStoreResponse);
}
