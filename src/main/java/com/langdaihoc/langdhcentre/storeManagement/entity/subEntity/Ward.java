package com.langdaihoc.langdhcentre.storeManagement.entity.subEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Wards")
public class Ward {
    @Id
    @Column(name = "Code")
    private Long Code;

    @Column(name = "Name")
    private String Name;
}
