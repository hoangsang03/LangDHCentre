package com.langdaihoc.langdhcentre.storeManagement.entity.address;

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
@Table(name = "provinces")
public class Province {
    @Id
    @Column(name = "code")
    private Long Code;

    @Column(name = "name")
    private String Name;

    @Column(name = "name_en")
    private String NameEn;

    @Column(name = "full_name")
    private String FullName;

    @Column(name = "full_name_en")
    private String FullNameEn;

    @Column(name = "code_name")
    private Long CodeName;
}
