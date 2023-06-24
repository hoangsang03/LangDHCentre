package com.langdaihoc.langdhcentre.storeManagement.service.impl;

import com.langdaihoc.langdhcentre.storeManagement.common.ApiResponse;
import com.langdaihoc.langdhcentre.storeManagement.controller.form.SearchBaseStoreRequest;
import com.langdaihoc.langdhcentre.storeManagement.controller.responses.BaseStoreResponse;
import com.langdaihoc.langdhcentre.storeManagement.dto.BaseStoreDTO;
import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.storeManagement.exception.JsonConvertException;
import com.langdaihoc.langdhcentre.storeManagement.repository.BaseStoreRepo;
import com.langdaihoc.langdhcentre.storeManagement.repository.common.FieldAndQueryOperatorConstants;
import com.langdaihoc.langdhcentre.storeManagement.repository.common.Filter;
import com.langdaihoc.langdhcentre.storeManagement.repository.common.QueryOperator;
import com.langdaihoc.langdhcentre.storeManagement.service.IBaseStoreService;
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
    private static final String CLASS_NAME = "com.langdaihoc.langdhcentre.storeManagement.impl.service.BaseStoreServiceImpl";
    private final BaseStoreRepo baseStoreRepo;
    private final ObjectMapperUtils objectMapperUtils;

    /**
     * @param jsonRequest: json string request
     * @return : BaseStoreResponse, must not return null
     */
    @Override
    public BaseStoreResponse getApiResponseForSearching(String jsonRequest) {
        //1. Get ApiRequest
        SearchBaseStoreRequest apiRequest = this.getSearchBaseStoreRequest(jsonRequest);

        //2. Get data and put them into ApiResponse
        BaseStoreResponse response = this.getDataOutput(apiRequest);
        return response;
    }

    /**
     * @param apiRequest :
     * @return true if apiRequest != null && apiRequest.getInput() != null , otherwise return false
     */
    public boolean validateRequest(SearchBaseStoreRequest apiRequest) {
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
    public BaseStoreResponse getDataOutput(SearchBaseStoreRequest apiRequest) {
        boolean isValidRequest = validateRequest(apiRequest);
        if (!isValidRequest) {
            return (BaseStoreResponse) responseWithInvalidRequest();
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
        response.setOutput(output);
        return response;
    }

    private BaseStoreResponse responseWithEmptyData() {
        BaseStoreResponse response = new BaseStoreResponse();
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
        ApiResponse.Error error = response.new Error();
        response.setError(error);

        String code = "Invalid Request Code";
        String message = "Invalid Request Message";
        ApiResponse.ErrorItem errorItem = response.new ErrorItem(code,message);
        error.addErrorItem(errorItem);

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
     * @return not limited baseStoreDTOList or empty list
     */
    @Override
    public List<BaseStoreDTO> getBaseStoreDTOList(SearchBaseStoreRequest baseStoreReq) {
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
    public BaseStoreResponse getApiResponseForSearching(List<BaseStoreDTO> baseStoreDTOList) {
        return null;
    }

    /**
     * @param baseStoreReq
     * @return : Filter list with given input of ApiRequest
     */
    @Override
    public List<Filter> getFiltersForBaseStoreInput(SearchBaseStoreRequest.Input baseStoreReq) {
        List<Filter> filterList = new ArrayList<>();

        // storeId
        if (baseStoreReq.getStoreId() != null) {
            Filter equalsStoreId = getFilterForBaseStoreInput(FieldAndQueryOperatorConstants.BASE_STORE_FIELD_STORE_ID,
                    QueryOperator.EQUALS, baseStoreReq.getStoreId());
            filterList.add(equalsStoreId);
        }

        // storeName - like
        if (baseStoreReq.getStoreName() != null) {
            Filter likeStoreName = getFilterForBaseStoreInput(
                    FieldAndQueryOperatorConstants.BASE_STORE_FIELD_STORE_NAME,
                    QueryOperator.LIKE, baseStoreReq.getStoreName());
            filterList.add(likeStoreName);
        }

        // storeType
        if (baseStoreReq.getStoreType() != null) {
            Filter equalsStoreType = getFilterForBaseStoreInput(
                    FieldAndQueryOperatorConstants.BASE_STORE_FIELD_STORE_TYPE,
                    QueryOperator.EQUALS, baseStoreReq.getStoreType());
            filterList.add(equalsStoreType);
        }

        // isStarted
        if (baseStoreReq.getIsStarted() != null) {
            Filter equalsIsStarted = getFilterForBaseStoreInput(
                    FieldAndQueryOperatorConstants.BASE_STORE_FIELD_IS_STARTED,
                    QueryOperator.EQUALS, baseStoreReq.getIsStarted());
            filterList.add(equalsIsStarted);
        }

        // isShutdown
        if (baseStoreReq.getIsShutdown() != null) {
            Filter equalsIsShutdown = getFilterForBaseStoreInput(
                    FieldAndQueryOperatorConstants.BASE_STORE_FIELD_IS_SHUTDOWN,
                    QueryOperator.EQUALS, baseStoreReq.getIsShutdown());
            filterList.add(equalsIsShutdown);
        }

        // isAutoOpenSetting
        if (baseStoreReq.getIsAutoOpenSetting() != null) {
            Filter equalsIsAutoOpenSetting = getFilterForBaseStoreInput(
                    FieldAndQueryOperatorConstants.BASE_STORE_FIELD_IS_AUTO_OPEN_SETTING,
                    QueryOperator.EQUALS, baseStoreReq.getIsAutoOpenSetting());
            filterList.add(equalsIsAutoOpenSetting);
        }

        // isOpening
        if (baseStoreReq.getIsOpening() != null) {
            Filter equalsIsOpening = getFilterForBaseStoreInput(
                    FieldAndQueryOperatorConstants.BASE_STORE_FIELD_IS_OPENING,
                    QueryOperator.EQUALS, baseStoreReq.getIsOpening());
            filterList.add(equalsIsOpening);
        }

        // isHidden
        if (baseStoreReq.getIsHidden() != null) {
            Filter equalsIsHidden = getFilterForBaseStoreInput(
                    FieldAndQueryOperatorConstants.BASE_STORE_FIELD_IS_HIDDEN,
                    QueryOperator.EQUALS, baseStoreReq.getIsHidden());
            filterList.add(equalsIsHidden);
        }
        // openingTime
        // closingTime
        // createdDate
        // operationStartDate
        // operationEndDate
        // ownerId
        if (baseStoreReq.getOwnerId() != null) {
            Filter equalsOwnerId = getFilterForBaseStoreInput(
                    FieldAndQueryOperatorConstants.BASE_STORE_FIELD_OWNER_ID,
                    QueryOperator.EQUALS, baseStoreReq.getOwnerId());
            filterList.add(equalsOwnerId);
        }
        // areaId
        // categories
        // utilities

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
     * @return : converted SearchBaseStoreRequest with given jsonRequest or null
     */
    @Override
    public SearchBaseStoreRequest getSearchBaseStoreRequest(String jsonRequest) {
        try {
            log.debug(CLASS_NAME + " - jsonRequest: " + jsonRequest);
            SearchBaseStoreRequest apiRequest = objectMapperUtils.convertJsonStringToObject(jsonRequest, SearchBaseStoreRequest.class);
            return apiRequest;
        } catch (JsonConvertException e) {
            log.error(CLASS_NAME + " - getApiRequest : ", e);
        }
        return null;
    }
}
