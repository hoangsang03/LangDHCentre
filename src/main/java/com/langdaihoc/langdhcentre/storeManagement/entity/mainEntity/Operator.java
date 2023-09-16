package com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity;

import com.langdaihoc.langdhcentre.storeManagement.entity.subEntity.RentalFee;
import com.langdaihoc.langdhcentre.storeManagement.entity.subEntity.Verification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "operators")
public class Operator {
    protected Operator(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "createByOperator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<RentalFee> createdRentalFees = new ArrayList<>();

    @OneToOne(mappedBy = "operator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Verification verification;

    public boolean addRentalFee(RentalFee rentalFeeInput) {
        rentalFeeInput.setCreateByOperator(this);
        if(this.createdRentalFees == null){
            this.createdRentalFees = new ArrayList<>();
        }
        this.createdRentalFees.add(rentalFeeInput);
        return true;
    }

}
