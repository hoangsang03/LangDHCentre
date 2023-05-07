package com.langdaihoc.langdhcentre.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class ObjectMapperUtils {
    /**
     * reference: <a href="https://www.baeldung.com/jackson-object-mapper-tutorial">...</a>
     */
    private static String CLASS_NAME = "ObjectMapperUtils";
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public <T> T toObjectFromMap(Object o, Class<T> type) throws Exception {
        try {
            objectMapper.setTimeZone(TimeZone.getDefault());
            return objectMapper.convertValue(o, type);
        } catch (Exception ex) {
            log.error("Error ObjectMapperUtils", ex);
            throw new Exception("Error ObjectMapperUtils ", ex);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> List<T> convertListMapToListObject(Object object, Class<T> targetClass) throws Exception {
        try {
            if (object == null) {
                log.error("====Loading====: List<T> is null : " + targetClass);
                return new ArrayList<>();
            }

            if (!(object instanceof List)) {
                throw new Exception("ApiResultリストへの変換失敗");
            }

            List<Object> objectList = (List) object;
            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            om.setTimeZone(TimeZone.getDefault());
            return objectList.stream().map((o) -> {
                return om.convertValue(o, targetClass);
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error convertMapToObject", ex);
            throw new Exception("Mapからオブジェクトに変換エラー", ex);
        }

    }

    public <T> T convertJsonStringToObject(String json, Class<T> targetClass) throws Exception {
        try {
            return objectMapper.readValue(json, targetClass);
        } catch (JsonProcessingException ex) {
            log.error(CLASS_NAME + " - convertJsonStringToObject : JsonProcessingException ", ex);
            throw new Exception("JsonProcessingException ", ex);
        }
    }

}