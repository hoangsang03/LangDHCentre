package com.langdaihoc.langdhcentre.storeManagement.common;

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
    public ApiResponse(){
        this.error = new Error();
    }

    String token;
    @Getter
    @Setter
    @ToString
    public class Error{
        private String code;
        private List<ErrorItem> items;

        public Error(){
            this.items = new ArrayList<>();
        }
        public void addErrorItem(ErrorItem item) {
            items.add(item);
        }
    }
    @Getter
    @Setter
    @ToString
    public class ErrorItem {
        private String code;
        private String message;
        public ErrorItem(String codeIn, String messageIn){
            this.code = codeIn;
            this.message = messageIn;
        }

    }
    private T output;
    private Error error;

    public void addErrorItem(String code, String message) {
        error.addErrorItem(new ErrorItem(code,message));
    }
}
