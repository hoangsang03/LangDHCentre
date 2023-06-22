package com.langdaihoc.langdhcentre.service.impl;

import com.langdaihoc.langdhcentre.common.ApiRequest;
import com.langdaihoc.langdhcentre.common.ApiResponse;
import com.langdaihoc.langdhcentre.controller.form.GetBaseStoreRequest;
import com.langdaihoc.langdhcentre.controller.form.SearchBaseStoreRequest;
import com.langdaihoc.langdhcentre.controller.responses.BaseStoreResponse;
import com.langdaihoc.langdhcentre.dto.BaseStoreDTO;
import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.exception.JsonConvertException;
import com.langdaihoc.langdhcentre.repository.BaseStoreRepo;
import com.langdaihoc.langdhcentre.repository.common.Filter;
import com.langdaihoc.langdhcentre.repository.common.QueryOperator;
import com.langdaihoc.langdhcentre.service.IGetBaseStoreService;
import com.langdaihoc.langdhcentre.service.common.ServiceUtils;
import com.langdaihoc.langdhcentre.util.ModelMapperUtils;
import com.langdaihoc.langdhcentre.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetBaseStoreServiceImpl implements IGetBaseStoreService {
    private static final String CLASS_NAME = "com.langdaihoc.langdhcentre.service.impl.GetBaseStoreServiceImpl";
    private final BaseStoreRepo baseStoreRepo;
    private final ObjectMapperUtils objectMapperUtils;

    /**
     * @param jsonRequest: json string request
     * @return : BaseStoreResponse, must not return null
     */
    @Override
    public ApiResponse getApiResponse(String jsonRequest) {
        //1. Get ApiRequest
        GetBaseStoreRequest apiRequest = null;
        try {
            apiRequest = (GetBaseStoreRequest) this.getApiRequest(jsonRequest, GetBaseStoreRequest.class);
        } catch (Exception e){
            log.error(CLASS_NAME + " - getApiResponse " , e);
        }

        //2. Get data and put them into ApiResponse
        ApiResponse response = this.getDataOutput(apiRequest);
        return response;
    }

    /**
     * @param apiRequest
     * @return true if apiRequest != null && apiRequest.getInput() != null , otherwise return false
     */
    public boolean validateRequest(ApiRequest apiRequest) {
        if (apiRequest != null && apiRequest.getInput() != null) {
            return true;
        }
        log.debug(CLASS_NAME + " apiRequest == null || apiRequest.getInput() == null");
        return false;
    }

    /**
     * @param apiRequest : ApiRequest Object
     * @return : ApiResponse with got output and error if any
     */
    @Override
    public ApiResponse getDataOutput(GetBaseStoreRequest apiRequest) {
        boolean isValidRequest = ServiceUtils.validateRequest(apiRequest);
        if (!isValidRequest) {
            return responseWithInvalidRequest();
        }
        log.debug(CLASS_NAME + " - Starting getBaseStoreDTOList from valid SearchBaseStoreRequest");
        // from here if there is any errors, responsibility belong to backend
        List<BaseStoreDTO> baseStoreDTOList = this.getBaseStoreDTOList(apiRequest);
        log.debug(CLASS_NAME + " - Starting getBaseStoreDTOList from valid SearchBaseStoreRequest");

        BaseStoreResponse response = responseWithValidRequest(baseStoreDTOList);

        return response;
    }

    private BaseStoreResponse responseWithValidRequest(List<BaseStoreDTO> output) {
        BaseStoreResponse response = new BaseStoreResponse();
        if (output.isEmpty()) {
            response = responseWithEmptyData();
            log.error(CLASS_NAME + " - responseWithValidRequest " + " - responseWithEmptyData()");
        } else {
            response = responseWithData(output);
            log.debug(CLASS_NAME + " - responseWithValidRequest " + " - responseWithData()");
        }
        return response;
    }

    private BaseStoreResponse responseWithData(List<BaseStoreDTO> output) {
        BaseStoreResponse response = new BaseStoreResponse();
        response.accepted();
        response.setOutput(output);
        return response;
    }

    private BaseStoreResponse responseWithEmptyData() {
        BaseStoreResponse response = new BaseStoreResponse();
        response.accepted();
        //response.setMessageForEmptyData
        return response;
    }

    /**
     * this method should be common method
     *
     * @return
     */
    public ApiResponse responseWithInvalidRequest() {
        ApiResponse response = new ApiResponse();
        ApiResponse.ErrorItem errorItem = response.new ErrorItem();
        Integer act = null;
        String codeErr = "empty code";
        List<String> msgParams = new ArrayList<>();
        msgParams.add("responseWithInvalidRequest");

        errorItem.setCode(codeErr);
        errorItem.setAct(act);
        errorItem.setMessage(msgParams);
        response.addErrorItem(errorItem);
        return response;
    }


    @Override
    public String getResponseJson(ApiResponse baseStoreResponse) {
        try {
            return objectMapperUtils.toJsonStringFromObject(baseStoreResponse);
        } catch (JsonConvertException e) {
            log.error(CLASS_NAME + " getResponseJson has error", e);
        }
        return CLASS_NAME + " getResponseJson has error";
    }

    /**
     * @param apiRequest
     * @return
     */
    @Override
    public List<BaseStoreDTO> getBaseStoreDTOList(SearchBaseStoreRequest apiRequest) {
        return null;
    }

    /**
     * @param baseStoreReq
     * @return
     */
    @Override
    public List<BaseStoreDTO> getBaseStoreDTOList(ApiRequest baseStoreReq) {
        return null;
    }

    /**
     * @param baseStoreReq
     * @return not limited baseStoreDTOList or empty list
     */
    @Override
    public List<BaseStoreDTO> getBaseStoreDTOList(GetBaseStoreRequest baseStoreReq) {
        try {
            // not done
            List<Filter> filters = this.getFiltersForBaseStoreInput(baseStoreReq.getInput());
            // not done
            Specification<BaseStore> spec = BaseStoreRepo.BaseStoreSpecification.getSpecificationFromFilters(filters);
            // not done
            List<BaseStore> baseStoreList = this.baseStoreRepo.findAll(spec);
            // not done
            if (baseStoreList.size() > 0) {
                List<BaseStoreDTO> baseStoreDTOList = ModelMapperUtils.mapAll(baseStoreList, BaseStoreDTO.class);
                return baseStoreDTOList;
            } else {
                log.debug(CLASS_NAME + " getBaseStoreDTOList - got empty BaseStoreDTO list");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error(CLASS_NAME + " - getBaseStoreDTOList has errors", e);
        }
        return Collections.emptyList();
    }

    @Override
    public BaseStoreResponse getApiResponse(List<BaseStoreDTO> baseStoreDTOList) {
        return null;
    }

    /**
     * @param apiRequest
     * @return
     */
    @Override
    public List<Filter> getFiltersForBaseStoreInput(SearchBaseStoreRequest.Input apiRequest) {
        return null;
    }

    /**
     * @param apiRequest
     * @return
     */
    @Override
    public boolean validateRequest(SearchBaseStoreRequest apiRequest) {
        return false;
    }

    /**
     * @param apiRequest
     * @return
     */
    @Override
    public BaseStoreResponse getDataOutput(SearchBaseStoreRequest apiRequest) {
        return null;
    }



    /**
     * @param baseStoreReq
     * @return : Filter list with given input of ApiRequest
     */
    @Override
    public List<Filter> getFiltersForBaseStoreInput(GetBaseStoreRequest.Input baseStoreReq) {
        List<Filter> filterList = new ArrayList<>();

        if (filterList.size() == 0) {
            log.warn(CLASS_NAME + " getFiltersForBaseStoreInput - " + " filters : null");
        } else {
            log.debug(CLASS_NAME + " getFiltersForBaseStoreInput \n" + filterList);
        }
        return filterList;
    }

    /**
     * @param field          : attribute name
     * @param queryOperator: =, <>, like
     * @param value
     * @return
     */
    public Filter getFilterForBaseStoreInput(String field, QueryOperator queryOperator, Object value) {
        if (field == null || queryOperator == null || value == null) {
            log.error(CLASS_NAME + " getFilterForBaseStoreInput " + " invalid parameters");
            return new Filter();
        }

        Filter filter = Filter.builder()
                .field(field)
                .operator(queryOperator)
                .build();
        if (value instanceof List<?>) {
            filter.setValues((List<String>) value);
        } else if (value instanceof Number || value instanceof String) {
            String valueStr = String.valueOf(value);
            filter.setValue(valueStr);
        } else {
            log.error(CLASS_NAME + " - getFilterForBaseStoreInput: unexpect type of value, type of value: "
                    + value.getClass());
        }
        return filter;
    }

    @Override
    public List<BaseStoreDTO> getAllBaseStores() {
        log.info(CLASS_NAME + " - getAllBaseStores only get first 20 stores");
        int limit = 20;
        int offset = 0;
        List<BaseStore> stores = this.baseStoreRepo.getAllBaseStore(limit, offset);
        log.debug(CLASS_NAME + "getAllBaseStores : stores.size() " + stores.size());
        return ModelMapperUtils.mapAll(stores, BaseStoreDTO.class);
    }


    /**
     * @param jsonRequest
     * @return : converted GetBaseStoreRequest with given jsonRequest or null
     */
    @Override
    public ApiRequest getApiRequest(String jsonRequest, Class targetClass) {
        try {
            log.debug(CLASS_NAME + " - jsonRequest: " + jsonRequest);
            ApiRequest apiRequest = (ApiRequest) objectMapperUtils.convertJsonStringToObject(jsonRequest, targetClass);
            return apiRequest;
        } catch (JsonConvertException e) {
            log.error(CLASS_NAME + " - getApiRequest : ", e);
        }
        return null;
    }
}
