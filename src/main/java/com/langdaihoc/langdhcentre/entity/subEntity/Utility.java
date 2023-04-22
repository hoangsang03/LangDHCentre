package com.langdaihoc.langdhcentre.entity.subEntity;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
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
    @Column(name = "utility_id", nullable = false)
    private Long utilityId;

    @Column(name = "utility_name")
    private String utilityName;

    @ManyToMany(mappedBy = "utilities", cascade = CascadeType.ALL, fetch =  FetchType.LAZY )
    private List<BaseStore> stores;

    @Override
    public String toString() {
        return "Utility{" +
                "utilityId=" + utilityId +
                ", utilityName='" + utilityName + '\'' +
                '}';
    }
}
