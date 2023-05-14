package com.langdaihoc.langdhcentre.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.langdaihoc.langdhcentre.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@Setter
public class ApiResponseEntity {

    public ApiResponseEntity() {
        this.head = new Head();
        setDictionaryUpdateTimestamp();
        setStationUpdateTimestamp();
    }


    @Getter
    @Setter
    public class Head {
        long dictionaryUpdate;
        long stationUpdate;
        String token;
    }


    public static final String STATUS_DISMISSED = "dismiss";
    public static final String STATUS_ACCEPTED = "accept";

    public void dismissed() {
        this.status = STATUS_DISMISSED;
    }

    public void accepted() {
        this.status = STATUS_ACCEPTED;
    }


    public void addErrorItem(String code, int action, List<String> msgParams) {
        if (errors == null) {
            errors = new Error();
        }

        ErrorItem errorItem = new ErrorItem();
        errorItem.setCode(code);
        errorItem.setAct(action);
        errorItem.setMsgParams(msgParams);

        errors.addErrorItem(errorItem);
    }

    public void addErrorItem(ErrorItem errorItem) {
        if (errors == null) {
            errors = new Error();
        }
        errors.addErrorItem(errorItem);
    }


    public void addOutput(String key, Object output_obj) {
        Map<String, Object> mapObject = null;
        if (output == null) {
            mapObject = new HashMap<String, Object>();
        } else {
            if (output instanceof Map<?, ?>) {
                mapObject = (Map<String, Object>) output;
            } else {
                log.warn("異なる型が戻り値として設定されています。既に設定されている値は上書きされます。");
                log.warn("[OLD]" + output.toString());
                log.warn("[NEW]" + output_obj.toString());
                mapObject = new HashMap<String, Object>();
                output = null;
            }
        }
        mapObject.put(key, output_obj);
        output = mapObject;
    }

    /**
     * 戻り値の設定（利用者側で構築したObjectをセットする場合）
     *
     * @param obj
     */
    public void addOutput(Object obj) {
        if (output != null) {
            log.warn("異なる型が戻り値として設定されています。既に設定されている値は上書きされます。");
            log.warn("[OLD]" + output.toString());
            log.warn("[NEW]" + obj.toString());
            output = null;
        }
        output = obj;
    }


    public void setToken(String token) {
        head.setToken(token);
    }


    public void setErrorCode(String errorCode) {
        if (errors == null) {
            errors = new Error();
        }
        errors.setCode(errorCode);
    }


    @Getter
    @Setter
    public class Error {
        {
            items = new ArrayList<>();
        }

        String code;
        List<ErrorItem> items;

        public void addErrorItem(ErrorItem item) {
            items.add(item);
        }
    }


    @Getter
    @Setter
    public static class ErrorItem {
        private String code;
        private Integer act;
        private List<String> msgParams = new ArrayList<>();
    }


    private Head head;
    private String status;                 // dismissed or accepted
    private Error errors = null;
    private Object output = null;


    public void setDictionaryUpdateTimestamp() {
        this.head.setDictionaryUpdate(DateUtil.longNow());
    }

    public void setStationUpdateTimestamp() {
        this.head.setStationUpdate(DateUtil.longNow());
    }
}
