package com.langdaihoc.langdhcentre.entity.subEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    protected OtherService() {};




}
