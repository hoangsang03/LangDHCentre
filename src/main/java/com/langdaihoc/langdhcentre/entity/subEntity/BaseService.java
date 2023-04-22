package com.langdaihoc.langdhcentre.entity.subEntity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Objects;

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "base_services")
public class BaseService {
    protected BaseService(){};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "base_service_id", nullable = false)
    private Long baseServiceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_price")
    private BigDecimal servicePrice;

    @Column(name = "service_description")
    private String serviceDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseService that = (BaseService) o;
        return Objects.equals(baseServiceId, that.baseServiceId) && Objects.equals(serviceName, that.serviceName) && Objects.equals(servicePrice, that.servicePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseServiceId, serviceName, servicePrice);
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "menu_id", referencedColumnName = "menu_id")
    private Menu menu;

    @Override
    public String toString() {
        return "BaseService{" +
                "baseServiceId=" + baseServiceId +
                ", serviceName='" + serviceName + '\'' +
                ", servicePrice=" + servicePrice +
                ", serviceDescription='" + serviceDescription + '\'' +
                '}';
    }
}
