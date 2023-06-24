package com.langdaihoc.langdhcentre.storeManagement.controller.form;

import com.langdaihoc.langdhcentre.storeManagement.common.ApiRequest;
import com.langdaihoc.langdhcentre.storeManagement.common.StoreTypeConstant;
import com.langdaihoc.langdhcentre.storeManagement.entity.subEntity.Category;
import com.langdaihoc.langdhcentre.storeManagement.entity.subEntity.Utility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class SearchBaseStoreRequest extends ApiRequest<SearchBaseStoreRequest.Input> implements Serializable {
    private static final long serialVersionUID = 0L;

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    public static class Input implements Serializable {
        private static final long serialVersionUID = 0L;

        {
            storeType = StoreTypeConstant.BASE_STORE;
        }

        /**
         * QueryOperator : EQUALS
         */
        private Long storeId;
        /**
         * QueryOperator : LIKE
         */
        private String storeName;
        /**
         * QueryOperator : EQUALS
         */
        Integer storeType;
        /**
         * QueryOperator : EQUALS
         */
        private Boolean isStarted;
        /**
         * QueryOperator : EQUALS
         */
        private Boolean isShutdown;
        /**
         * QueryOperator : EQUALS
         */
        private Boolean isAutoOpenSetting;
        /**
         * QueryOperator : EQUALS
         */
        private Boolean isOpening;
        /**
         * QueryOperator : EQUALS
         */
        private Boolean isHidden;
        /**
         * QueryOperator : LESS_THAN openingTime : store has opened and has not closed
         */
        private LocalTime openingTime;
        /**
         * QueryOperator : LESS_THAN closingTime : store has closed and has opened yet
         */
        private LocalTime closingTime;
        /**
         * QueryOperator : LESS_THAN store has created before
         */
        private Date createdDate;
        /**
         * QueryOperator :  LESS_THAN store has started operation before
         */
        private Date operationStartDate;
        /**
         * QueryOperator : LESS_THAN store has ended operation before
         */
        private Date operationEndDate;
        /**
         * QueryOperator : EQUALS
         */
        private Long ownerId;
        /**
         * QueryOperator : EQUALS
         */
        private Long areaId;
        /**
         * QueryOperator : IN stores have all of categories
         */
        private List<Category> categories;
        /**
         * QueryOperator : IN stores have all of utilities
         */
        private List<Utility> utilities;
    }


}
