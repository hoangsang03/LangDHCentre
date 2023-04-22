package com.langdaihoc.langdhcentre.entity.subEntity;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "revenue_details")
public class RevenueDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revenue_detail_id", nullable = false)
    private Long revenueDetailId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "revenue_id", referencedColumnName = "revenue_id")
    private Revenue revenue;

}
