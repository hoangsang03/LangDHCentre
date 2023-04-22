package com.langdaihoc.langdhcentre.entity.subEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Entity
public class Food  extends BaseService{
    protected Food() {};

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    private Menu menu;



}
