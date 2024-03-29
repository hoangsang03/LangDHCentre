package com.langdaihoc.langdhcentre.storeManagement.entity.subEntity;

import com.langdaihoc.langdhcentre.storeManagement.entity.common.AbstractEntity;
import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.storeManagement.entity.auth.Operator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rental_fees")
public class RentalFee extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_fee_id", nullable = false)
    private Long rentalFeeId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "store_id", referencedColumnName = "id")
    private BaseStore store;

    @Column(name = "rental_month")
    private int month;

    @Column(name = "rental_year")
    private int year;

    @Column(name = "money_number")
    private BigDecimal moneyNumber;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(nullable = false, name = "created_operator_id", referencedColumnName = "id")
//    private Operator createByOperator;
    @Column(name = "created_by_operator")
    private long created_by_operator;


}
