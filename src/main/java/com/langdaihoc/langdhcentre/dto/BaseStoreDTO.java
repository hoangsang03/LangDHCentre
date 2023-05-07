package com.langdaihoc.langdhcentre.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.langdaihoc.langdhcentre.common.StoreTypeConstant;
import com.langdaihoc.langdhcentre.entity.mainEntity.Owner;
import com.langdaihoc.langdhcentre.entity.subEntity.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class BaseStoreDTO  {

    private int storeType;

    private String storeName;

    private boolean isStarted;

    private boolean isShutdown;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openingTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closingTime;

    private boolean isAutoOpenSetting;

    private boolean isOpening;

    private boolean isHidden;

    private Date createdDate;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date operationStartDate;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date operationEndDate;

    private String storeUrl;

    private long ownerId;

    private long addressId;

    private String foodType;

    private String coffeeType;
}
