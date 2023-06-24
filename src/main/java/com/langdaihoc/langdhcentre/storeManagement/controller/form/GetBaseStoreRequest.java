package com.langdaihoc.langdhcentre.storeManagement.controller.form;

import com.langdaihoc.langdhcentre.storeManagement.common.ApiRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class GetBaseStoreRequest extends ApiRequest<GetBaseStoreRequest.Input> implements Serializable {

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    public static class Input implements Serializable{
        Integer page;
        Integer size;
        String orderBy;
    }
}
