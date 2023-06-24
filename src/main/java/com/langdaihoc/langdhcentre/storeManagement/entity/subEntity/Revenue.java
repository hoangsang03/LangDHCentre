package com.langdaihoc.langdhcentre.storeManagement.entity.subEntity;

import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.BaseStore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "revenues")
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revenue_id", nullable = false)
    private Long revenueId;

    @ManyToOne(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "store_id", referencedColumnName = "store_id")
    private BaseStore store;

    @OneToOne(mappedBy = "revenue", cascade = CascadeType.ALL)
    private RevenueDetail revenueDetail;

    @Column(name = "total_month_revenue")
    private BigDecimal totalMonthRevenue;

    @Override
    public String toString() {
        return "Revenue{" +
                "revenueId=" + revenueId +
                ", totalMonthRevenue=" + totalMonthRevenue +
                '}';
    }
}
