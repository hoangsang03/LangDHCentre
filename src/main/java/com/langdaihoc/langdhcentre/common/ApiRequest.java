package com.langdaihoc.langdhcentre.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiRequest<T> {
    private String token;
    private T input;

    @Override
    public String toString() {
        return "ApiRequest{" +
                "token='" + token + '\'' +
                ", input=" + input +
                '}';
    }
}
