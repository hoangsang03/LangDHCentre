package com.langdaihoc.langdhcentre.storeManagement.entity.subEntity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Districts")
public class District {
    @Id
    @Column(name = "Code")
    private Long Code;

    @Column(name = "Name")
    private String Name;
}

