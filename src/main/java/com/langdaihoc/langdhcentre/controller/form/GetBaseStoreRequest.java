package com.langdaihoc.langdhcentre.controller.form;

import com.langdaihoc.langdhcentre.common.ApiRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

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
