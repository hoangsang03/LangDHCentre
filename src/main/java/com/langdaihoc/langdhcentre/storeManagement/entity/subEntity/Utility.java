package com.langdaihoc.langdhcentre.storeManagement.entity.subEntity;

import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.BaseStore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilities")
public class Utility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "utilities", cascade = CascadeType.ALL, fetch =  FetchType.LAZY )
    private List<BaseStore> stores;

    @Override
    public String toString() {
        return "Utility{" +
                "utilityId=" + id +
                ", utilityName='" + name + '\'' +
                '}';
    }
}
