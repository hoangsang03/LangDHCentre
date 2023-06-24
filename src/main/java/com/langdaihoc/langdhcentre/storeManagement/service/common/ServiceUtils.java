package com.langdaihoc.langdhcentre.storeManagement.service.common;

import com.langdaihoc.langdhcentre.storeManagement.common.ApiRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

}
