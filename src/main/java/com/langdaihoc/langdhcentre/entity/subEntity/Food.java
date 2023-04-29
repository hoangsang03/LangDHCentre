package com.langdaihoc.langdhcentre.entity.subEntity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
@Entity
public class Food  extends BaseService{
    protected Food() {}





}
