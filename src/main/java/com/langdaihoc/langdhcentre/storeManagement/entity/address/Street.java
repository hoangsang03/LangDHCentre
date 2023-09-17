package com.langdaihoc.langdhcentre.storeManagement.entity.address;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "streets")
public class Street {
    @Id
    @Column(name = "code")
    private Long Code;

    @Column(name = "name")
    private String Name;
}
