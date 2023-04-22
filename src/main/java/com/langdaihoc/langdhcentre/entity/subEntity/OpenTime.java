package com.langdaihoc.langdhcentre.entity.subEntity;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "open_times")
public class OpenTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "open_time_id", nullable = false)
    private Long openTimeId;

    @Column(name = "opening_time")
    private int openingTime;

    @Column(name = "closing_time")
    private int closingTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private BaseStore store;

    @Override
    public String toString() {
        return "OpenTime{" +
                "openTimeId=" + openTimeId +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                '}';
    }
}
