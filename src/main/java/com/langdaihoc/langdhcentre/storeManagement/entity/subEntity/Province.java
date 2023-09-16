package com.langdaihoc.langdhcentre.storeManagement.entity.subEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Provinces")
public class Province {
    @Id
    @Column(name = "Code")
    private Long Code;

    @Column(name = "Name")
    private String Name;

    @Column(name = "NameEn")
    private String NameEn;

    @Column(name = "FullName")
    private String FullName;

    @Column(name = "FullNameEn")
    private String FullNameEn;

    @Column(name = "CodeName")
    private Long CodeName;
}
