package com.langdaihoc.langdhcentre.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ApiRespond<T> {
    {
        this.token = new Token();
    }
    @Setter
    @Getter
    @ToString
    public class Token{
        private String token;
    }

    private Token token;

    private T respond;

    public void setTokenString(String token){
        this.token.setToken(token);
    }

    public String getTokenString() {
        return this.token.getToken();
    }

    @Override
    public String toString() {
        return "ApiRespond{" +
                "token=" + token +
                ", respond type =" + respond.getClass().getSimpleName() +
                '}';
    }
}
