package com.langdaihoc.langdhcentre.repository.common;

import lombok.Getter;

import java.time.LocalTime;
import java.util.Date;

@Getter
public class FieldAndQueryOperatorConstants {

    public final static String BASE_STORE_FIELD_STORE_ID = "storeId";
    public final static QueryOperator BASE_STORE_QUERY_OPERATOR_STORE_ID = QueryOperator.LIKE;

    public final static String BASE_STORE_FIELD_STORE_NAME = "storeName";
    public final static QueryOperator BASE_STORE_QUERY_OPERATOR_STORE_NAME = QueryOperator.LIKE;

    public final static String BASE_STORE_FIELD_STORE_TYPE = "storeType";
    public final static QueryOperator BASE_STORE_QUERY_OPERATOR_STORE_TYPE = QueryOperator.EQUALS;



    public final static String BASE_STORE_FIELD_IS_STARTED = "isStarted";
    public final static QueryOperator BASE_STORE_QUERY_OPERATOR_IS_STARTED  = QueryOperator.EQUALS;

    public final static String BASE_STORE_FIELD_IS_SHUTDOWN = "isShutdown";
    public final static QueryOperator BASE_STORE_QUERY_OPERATOR_IS_SHUTDOWN = QueryOperator.EQUALS;

    public final static String BASE_STORE_FIELD_IS_AUTO_OPEN_SETTING= "isAutoOpenSetting";
    public final static QueryOperator BASE_STORE_QUERY_OPERATOR_IS_AUTO_OPEN_SETTING = QueryOperator.EQUALS;

    public final static String BASE_STORE_FIELD_IS_OPENING= "isOpening";
    public final static QueryOperator BASE_STORE_QUERY_OPERATOR_IS_OPENING = QueryOperator.EQUALS;

    public final static String BASE_STORE_FIELD_IS_HIDDEN = "isHidden";
    public final static QueryOperator BASE_STORE_QUERY_OPERATOR_IS_HIDDEN = QueryOperator.EQUALS;

    /**
     * QueryOperator : LESS_THAN openingTime : store has opened and has not closed
     */
    public LocalTime openingTime;
    /**
     * QueryOperator : LESS_THAN closingTime : store has closed and has opened yet
     */
    public LocalTime closingTime;
    /**
     * QueryOperator : LESS_THAN store has created before
     */
    public Date createdDate;
    /**
     * QueryOperator :  LESS_THAN store has started operation before
     */
    public Date operationStartDate;
    /**
     * QueryOperator : LESS_THAN store has ended operation before
     */
    public Date operationEndDate;
    /**
     * QueryOperator : EQUALS
     */
    public Long ownerId;
    /**
     * QueryOperator : EQUALS
     */
    public Long areaId;
}
