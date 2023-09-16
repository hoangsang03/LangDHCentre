package com.langdaihoc.langdhcentre.storeManagement.entity.subEntity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Streets")
public class Street {
    @Id
    @Column(name = "Code")
    private Long Code;

    @Column(name = "Name")
    private String Name;
}
