package com.langdaihoc.langdhcentre.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class ApiResponse<T> {
    {
        this.token = new Token();
        this.errors = new Error();
    }

    public static final String STATUS_DISMISSED = "dismiss";
    public static final String STATUS_ACCEPTED = "accept";

    @Setter
    @Getter
    @ToString
    public class Token {
        private String token;
    }

    @Getter
    @Setter
    @ToString
    public class Error{
        String code;
        List<ErrorItem> items = null;
        public void addErrorItem(ErrorItem item) {
            if(items == null) {
                items = new ArrayList<>();
            }
            items.add(item);
        }
    }
    @Getter
    @Setter
    @ToString
    public class ErrorItem {
        {
            msgParams = new ArrayList<>();
        }
        private String code;
        private Integer act;
        private List<String> msgParams;
    }

    private Token token;

    private T output;

    private String status;

    private Error errors;

    public void setTokenString(String token) {
        this.token.setToken(token);
    }

    public String getTokenString() {
        return this.token.getToken();
    }

    public void addErrorItem(String code, int action, List<String> msgParams) {

        if(errors == null) {
            errors = new ApiResponse.Error();
        }

        ApiResponse.ErrorItem errorItem =  new ApiResponse.ErrorItem();
        errorItem.setCode(code);
        errorItem.setAct(action);
        errorItem.setMsgParams(msgParams);

        errors.addErrorItem(errorItem);
    }
    public void addErrorItem(ErrorItem errorItem) {
        if(this.errors == null) {
            this.errors = new ApiResponse.Error();
        }

        errors.addErrorItem(errorItem);
    }

    public void dismissed() {
        this.status = STATUS_DISMISSED;
    }

    public void accepted() {
        this.status = STATUS_ACCEPTED;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "token=" + token +
                ", output type=" + output.getClass().getSimpleName() +
                ", status='" + status + '\'' +
                '}';
    }
}
