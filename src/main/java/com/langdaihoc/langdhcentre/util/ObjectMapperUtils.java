package com.langdaihoc.langdhcentre.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.langdaihoc.langdhcentre.storeManagement.exception.JsonConvertException;
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
    {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setTimeZone(TimeZone.getDefault());
    }

    public <T> T toObjectFromMap(Object o, Class<T> type) throws JsonConvertException {
        try {
            objectMapper.setTimeZone(TimeZone.getDefault());
            return objectMapper.convertValue(o, type);
        } catch (Exception ex) {
            throw new JsonConvertException("Cannot convert from Object to " + type.getSimpleName());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> List<T> convertListMapToListObject(Object object, Class<T> targetClass) throws JsonConvertException {
        try {
            if (object == null) {
                log.error("====Loading====: List<T> is null : " + targetClass);
                return new ArrayList<>();
            }

            if (!(object instanceof List)) {
                throw new JsonConvertException("Cannot convert list of map to list of object");
            }

            List<Object> objectList = (List) object;
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.setTimeZone(TimeZone.getDefault());
            return objectList.stream().map((o) -> {
                return objectMapper.convertValue(o, targetClass);
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new JsonConvertException("Cannot convert list of map to list of object");
        }

    }

    /**
     *
     * @param json : json string
     * @param targetClass : targetClass type
     * @return object with targetClass type
     * @param <T>
     * @throws JsonConvertException
     */
    public <T> T convertJsonStringToObject(String json, Class<T> targetClass) throws JsonConvertException {
        try {
            log.debug(CLASS_NAME + " - json:\n " + json);
            return (T)objectMapper.readValue(json, targetClass);
        } catch (JsonProcessingException ex) {
            throw new JsonConvertException("Cannot convert json string to " + targetClass.getSimpleName(), ex);
        } catch (Exception ex) {
            throw new JsonConvertException("Cannot convert json string to " + targetClass.getSimpleName(), ex);
        }
    }

    /**
     *
     * @param object : object will be converted to json
     * @return : json string
     * @throws JsonConvertException
     */
    public String toJsonStringFromObject(Object object) throws JsonConvertException {
        try {
            String jsonStr = objectMapper.writeValueAsString(object);
            return jsonStr;
        } catch (Exception ex) {
            throw new JsonConvertException("Cannot convert object " + object.getClass() + " to string");
        }
    }

}