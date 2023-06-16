package com.langdaihoc.langdhcentre.repository_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.langdaihoc.langdhcentre.common.ApiRequest;
import com.langdaihoc.langdhcentre.controller.form.SearchBaseStoreRequest;
import com.langdaihoc.langdhcentre.exception.JsonConvertException;
import com.langdaihoc.langdhcentre.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
@Slf4j
public class ObjectMapperUtilsTest {
    private final ObjectMapperUtils objectMapperUtils = new ObjectMapperUtils();
    private final static String CLASS_NAME = "ObjectMapperUtilsTest";

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void convertJsonStringToObjectTest() {
        String jsonRequest = "{\n" +
                "    \"token\":{\"token\":\"nskdkoasdfldfsfija\"},\n" +
                "    \"input\":{\n" +
                "        \"storeId\":1,\n" +
                "        \"storeName\":\"store\",\n" +
                "        \"storeType\":1\n" +
                "        }\n" +
                "}";
        log.debug(CLASS_NAME + " - convertJsonStringToObjectTest:\n " + jsonRequest);
        try {
            SearchBaseStoreRequest apiRequest = objectMapperUtils.convertJsonStringToObject(
                    jsonRequest, SearchBaseStoreRequest.class);
            log.debug(CLASS_NAME + " SearchBaseStoreRequest: " + apiRequest);
        } catch (JsonConvertException e) {
            Assertions.fail("toJsonStringFromObjectTest : ", e);
        }
    }

    @Test
    public void convertJsonStringToObjectTest_2() {
        String jsonRequest = "{\n" +
                "    \"info\":{\"token\":\"nskdkoasdfldfsfija\"},\n" +
                "    \"input\":{\n" +
                "        \"storeId\":1,\n" +
                "        \"storeName\":\"store\",\n" +
                "        \"storeType\":1\n" +
                "        }\n" +
                "}";
        log.debug(CLASS_NAME + " - convertJsonStringToObjectTest:\n " + jsonRequest);
        try {
            //String decodeData = URLDecoder.decode(jsonRequest, StandardCharsets.UTF_8.toString());
            //log.debug(CLASS_NAME + " - convertJsonStringToObjectTest: decodeData\n " + decodeData);
//            objectMapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
//            SearchBaseStoreRequest apiRequest = objectMapper.readValue(decodeData,
//                    SearchBaseStoreRequest.class);

            SearchBaseStoreRequest apiRequest = objectMapper.readValue(jsonRequest,
                    SearchBaseStoreRequest.class);
            if(apiRequest == null){
                log.debug(CLASS_NAME + " SearchBaseStoreRequest: apiRequest is null");
            }
            log.debug(CLASS_NAME + " SearchBaseStoreRequest:\n " + apiRequest);
        } catch (JsonProcessingException e) {
            Assertions.fail("toJsonStringFromObjectTest : ", e);
        }
    }

    @Test
    public void toJsonStringFromObjectTest() {
        SearchBaseStoreRequest object = new SearchBaseStoreRequest();

        ApiRequest.Info info = new ApiRequest.Info();
        info.setToken("nskdkoasdfldfsfija");
        object.setInfo(info);

        SearchBaseStoreRequest.Input input = new SearchBaseStoreRequest.Input();
        input.setStoreId(1L);
        input.setStoreName("store");
        object.setInput(input);

        try {
            String json = objectMapperUtils.toJsonStringFromObject(object);
            log.debug(CLASS_NAME + " - json:\n " + json);
        } catch (JsonConvertException e) {
            Assertions.fail("toJsonStringFromObjectTest : ", e);
        }
    }
}
