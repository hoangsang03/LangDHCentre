package com.langdaihoc.langdhcentre.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.langdaihoc.langdhcentre.exception.JsonConvertException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;


/**
 * レスポンス変換機能
 */
@Component
@RequiredArgsConstructor
public class ObjectStringConverter {

    private static ObjectMapper mapper = null;

    public static synchronized ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
            mapper.setTimeZone(TimeZone.getDefault());
        }
        return mapper;
    }

    /**
     *
     * @param object
     * @return
     */
    public String toJsonStringFromObject(Object object) throws Exception {
        try {
            String jsonStr = getMapper().writeValueAsString(object);
            return jsonStr;
        } catch (Exception ex) {
            throw new JsonConvertException(ex.getMessage(),ex);
        }
    }

    /**
     *
     *
     * @param objMap
     * @return
     */
    public String toJsonStringFromMap(Map<String, Object> objMap) throws Exception {
        try {
            String jsonStr = getMapper().writeValueAsString(objMap);

            return jsonStr;
        } catch (Exception ex) {
            throw new JsonConvertException(ex.getMessage(),ex);
        }
    }

    /**
     * JSON
     *
     * @param <T>
     * @param jsonStr
     * @param valueType
     * @return
     * @throws
     */
    public <T> T toObjectFromJsonString(String jsonStr, Class<T> valueType) throws Exception {

        try {

            return (T) getMapper().readValue(jsonStr, valueType);
        } catch (Exception ex) {
            throw new JsonConvertException(ex.getMessage(),ex);
        }
    }

}

