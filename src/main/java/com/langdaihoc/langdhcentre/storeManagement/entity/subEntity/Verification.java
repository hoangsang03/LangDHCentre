package com.langdaihoc.langdhcentre.storeManagement.entity.subEntity;

import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.Operator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "verifications")
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "operator_id", referencedColumnName = "id")
    private Operator operator;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "store_id", referencedColumnName = "id")
    private BaseStore store;

}
