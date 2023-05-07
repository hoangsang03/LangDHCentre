package com.langdaihoc.langdhcentre.service;

import com.langdaihoc.langdhcentre.common.ApiRequest;
import com.langdaihoc.langdhcentre.dto.BaseStoreDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBaseStoreService {
    List<BaseStoreDTO> getAllBaseStores();

    <T> T getApiRequest(String jsonRequest);
}
