package com.langdaihoc.langdhcentre.storeManagement.entity.address;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "districts")
public class District {
    @Id
    @Column(name = "code")
    private Long Code;

    @Column(name = "name")
    private String Name;
}

