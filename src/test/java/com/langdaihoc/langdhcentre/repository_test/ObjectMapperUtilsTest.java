package com.langdaihoc.langdhcentre.repository_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.langdaihoc.langdhcentre.common.ApiRequest;
import com.langdaihoc.langdhcentre.common.ApiResponse;
import com.langdaihoc.langdhcentre.controller.form.GetBaseStoreRequest;
import com.langdaihoc.langdhcentre.exception.JsonConvertException;
import com.langdaihoc.langdhcentre.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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
            GetBaseStoreRequest apiRequest = objectMapperUtils.convertJsonStringToObject(
                    jsonRequest, GetBaseStoreRequest.class);
            log.debug(CLASS_NAME + " GetBaseStoreRequest: " + apiRequest);
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
//            GetBaseStoreRequest apiRequest = objectMapper.readValue(decodeData,
//                    GetBaseStoreRequest.class);

            GetBaseStoreRequest apiRequest = objectMapper.readValue(jsonRequest,
                    GetBaseStoreRequest.class);
            if(apiRequest == null){
                log.debug(CLASS_NAME + " GetBaseStoreRequest: apiRequest is null");
            }
            log.debug(CLASS_NAME + " GetBaseStoreRequest:\n " + apiRequest);
        } catch (JsonProcessingException e) {
            Assertions.fail("toJsonStringFromObjectTest : ", e);
        }
    }

    @Test
    public void toJsonStringFromObjectTest() {
        GetBaseStoreRequest object = new GetBaseStoreRequest();

        ApiRequest.Info info = new ApiRequest.Info();
        info.setToken("nskdkoasdfldfsfija");
        object.setInfo(info);

        GetBaseStoreRequest.Input input = new GetBaseStoreRequest.Input();
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
