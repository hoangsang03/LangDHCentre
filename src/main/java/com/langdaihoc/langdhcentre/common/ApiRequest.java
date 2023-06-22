package com.langdaihoc.langdhcentre.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiRequest<T> {
    @Getter
    @Setter
    @ToString
    public static class Info {
        private static final long serialVersionUID = 0L;
        String token;
    }

    private Info info;
    private T input;

    @Override
    public String toString() {
        return "ApiRequest{" +
                "info=" + info +
                ", input=" + input +
                '}';
    }


}
