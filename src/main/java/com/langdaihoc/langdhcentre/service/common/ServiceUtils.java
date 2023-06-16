package com.langdaihoc.langdhcentre.service.common;

import com.langdaihoc.langdhcentre.common.ApiRequest;
import com.langdaihoc.langdhcentre.common.ApiResponse;
import com.langdaihoc.langdhcentre.controller.responseentity.BaseStoreResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ServiceUtils {
    private static final String CLASS_NAME = "ServiceUtils";
    /**
     * @param apiRequest
     * @return true if apiRequest != null && apiRequest.getInput() != null , otherwise return false
     */
    public static boolean validateRequest(ApiRequest apiRequest) {
        if (apiRequest != null && apiRequest.getInput() != null) {
            return true;
        }
        log.debug(CLASS_NAME + " apiRequest == null || apiRequest.getInput() == null");
        return false;
    }

    /**
     * this method should be common method
     *
     * @return
     */
    public ApiResponse responseWithInvalidRequest() {
        ApiResponse<Object> response = new ApiResponse();
        ApiResponse.ErrorItem errorItem = response.new ErrorItem();
        Integer act = null;
        String codeErr = "empty code";
        List<String> msgParams = new ArrayList<>();
        msgParams.add("responseWithInvalidRequest");

        errorItem.setCode(codeErr);
        errorItem.setAct(act);
        errorItem.setMsgParams(msgParams);
        response.addErrorItem(errorItem);
        response.setOutput(null);
        return response;
    }
}
