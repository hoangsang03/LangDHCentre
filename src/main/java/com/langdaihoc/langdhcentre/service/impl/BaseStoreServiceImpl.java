package com.langdaihoc.langdhcentre.service.impl;

import com.langdaihoc.langdhcentre.common.ApiResponse;
import com.langdaihoc.langdhcentre.controller.form.GetBaseStoreRequest;
import com.langdaihoc.langdhcentre.controller.responseentity.BaseStoreResponse;
import com.langdaihoc.langdhcentre.dto.BaseStoreDTO;
import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.exception.JsonConvertException;
import com.langdaihoc.langdhcentre.repository.BaseStoreRepo;
import com.langdaihoc.langdhcentre.repository.common.FieldAndQueryOperatorConstants;
import com.langdaihoc.langdhcentre.repository.common.Filter;
import com.langdaihoc.langdhcentre.repository.common.QueryOperator;
import com.langdaihoc.langdhcentre.service.IBaseStoreService;
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
public class BaseStoreServiceImpl implements IBaseStoreService {
    private static final String CLASS_NAME = "com.langdaihoc.langdhcentre.service.impl.BaseStoreServiceImpl";
    private final BaseStoreRepo baseStoreRepo;
    private final ObjectMapperUtils objectMapperUtils;

    /**
     * @param jsonRequest: json string request
     * @return : BaseStoreResponse, must not return null
     */
    @Override
    public BaseStoreResponse getApiResponse(String jsonRequest) {
        //1. Get ApiRequest
        GetBaseStoreRequest apiRequest = this.getApiRequest(jsonRequest);

        //2. Get data and put them into ApiResponse
        BaseStoreResponse response = this.getDataOutput(apiRequest);
        return response;
    }

    public boolean validateRequest(GetBaseStoreRequest apiRequest) {
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
    public BaseStoreResponse getDataOutput(GetBaseStoreRequest apiRequest) {
        boolean isValidRequest = validateRequest(apiRequest);
        if (!isValidRequest) {
            return (BaseStoreResponse) responseWithInvalidRequest();
        }
        // from here if there is any errors, responsibility belong to backend
        List<BaseStoreDTO> baseStoreDTOList = this.getBaseStoreDTOList(apiRequest);
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
        errorItem.setMsgParams(msgParams);
        response.addErrorItem(errorItem);
        return response;
    }


    @Override
    public String getResponseJson(BaseStoreResponse baseStoreResponse) {
        try {
            return objectMapperUtils.toJsonStringFromObject(baseStoreResponse);
        } catch (JsonConvertException e) {
            log.error(CLASS_NAME + " getResponseJson has error", e);
        }
        return CLASS_NAME + " getResponseJson has error";
    }

    /**
     * @param baseStoreReq
     * @return baseStoreDTOList or empty list
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
                log.debug(CLASS_NAME + " getBaseStoreDTOList - got empty list");
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

    @Override
    public List<Filter> getFiltersForBaseStoreInput(GetBaseStoreRequest.Input baseStoreReq) {
        List<Filter> filterList = new ArrayList<>();
        if (baseStoreReq.getStoreId() != null) {
            Filter equalsStoreId = getFilterForBaseStoreInput(FieldAndQueryOperatorConstants.BASE_STORE_FIELD_STORE_ID,
                    QueryOperator.EQUALS, baseStoreReq.getStoreId());
            filterList.add(equalsStoreId);
        }

        if (baseStoreReq.getStoreName() != null) {
            Filter likeStoreName = getFilterForBaseStoreInput(
                    FieldAndQueryOperatorConstants.BASE_STORE_FIELD_STORE_NAME,
                    QueryOperator.LIKE, baseStoreReq.getStoreName());
            filterList.add(likeStoreName);
        }

        if (baseStoreReq.getStoreType() != null) {
            Filter equalsStoreType = getFilterForBaseStoreInput(
                    FieldAndQueryOperatorConstants.BASE_STORE_FIELD_STORE_TYPE,
                    QueryOperator.EQUALS, baseStoreReq.getStoreType());
            filterList.add(equalsStoreType);
        }

        if (filterList.size() == 0) {
            log.error(CLASS_NAME + " getFiltersForBaseStoreInput - " + " filters : null");
        } else {
            log.debug(CLASS_NAME + " getFiltersForBaseStoreInput \n" + filterList);
        }
        return filterList;
    }

    public Filter getFilterForBaseStoreInput(String field, QueryOperator queryOperator, Object value) {
        if (field == null || queryOperator == null || value == null) {
            log.error(CLASS_NAME + " getFilterForBaseStoreInput " + " invalid parameters");
            return new Filter();
        }
        String valueStr = String.valueOf(value);
        Filter filter = Filter.builder()
                .field(field)
                .operator(queryOperator)
                .value(valueStr)
                .build();
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
    public GetBaseStoreRequest getApiRequest(String jsonRequest) {
        try {
            log.debug(CLASS_NAME + " - jsonRequest: " + jsonRequest);
            GetBaseStoreRequest apiRequest = objectMapperUtils.convertJsonStringToObject(jsonRequest, GetBaseStoreRequest.class);
            return apiRequest;
        } catch (JsonConvertException e) {
            log.error(CLASS_NAME + " - getApiRequest : ", e);
        }
        return null;
    }
}
