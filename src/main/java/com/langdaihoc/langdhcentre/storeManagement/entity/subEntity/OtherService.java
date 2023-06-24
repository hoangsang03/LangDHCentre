package com.langdaihoc.langdhcentre.storeManagement.entity.subEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
@Entity
public class OtherService extends BaseService{
    protected OtherService() {}


}
