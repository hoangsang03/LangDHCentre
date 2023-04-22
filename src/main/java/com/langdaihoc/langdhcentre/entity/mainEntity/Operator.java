package com.langdaihoc.langdhcentre.entity.mainEntity;

import com.langdaihoc.langdhcentre.entity.subEntity.RentalFee;
import com.langdaihoc.langdhcentre.entity.subEntity.Verification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "operators")
public class Operator {
    protected Operator(){};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operator_id", nullable = false)
    private Long operatorId;

    @Column(name = "operator_name")
    private String operatorName;

    @OneToMany(mappedBy = "createByOperator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RentalFee> rentalFees;

    @OneToOne(mappedBy = "operator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Verification verification;

}
