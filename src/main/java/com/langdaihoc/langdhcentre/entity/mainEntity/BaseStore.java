package com.langdaihoc.langdhcentre.entity.mainEntity;

import com.langdaihoc.langdhcentre.entity.subEntity.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "stores")
public class BaseStore {
    protected  BaseStore(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "store_name")
    private String storeName;

    /**
     * if isStarted is true, it means this store has started business
     * default it is false when initialize
     */
    @Column(name = "is_started")
    private boolean isStarted = false;

    /**
     * if isShutdown is true, it means this store has stopped business
     * -> then customer cannot see it
     * default it is false when initialize
     */
    @Column(name = "is_shutdown")
    private boolean isShutdown = false;

    /**
     * if autoOpenSetting is false, need to check isOpening in order to know
     * is it opening now?
     * default it is true when initialize
     * -> and check is it opening now  by OpenTime
     */

    @Column(name = "auto_open_setting")
    private boolean autoOpenSetting = true;
    /**
     * if isOpening is true, it means this store is opening at that moment
     * default it is false when initialize
     * -> only need to check it when autoOpenSetting = false;
     */
    @Column(name = "is_opening")
    private boolean isOpening = false;

    /**
     * is isHidden = true: no one can see it on web except authorized operator
     * default: isHidden = false: everyone can see it
     */
    @Column(name = "is_hidden")
    private boolean isHidden = false;

    /**
     * the date the store has been created in database
     */
    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "operation_start_date")
    private Date operationStartDate;

    @Column(name = "operation_end_date")
    private Date operationEndDate;


    @Column(name = "store_url")
    private String storeUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false, referencedColumnName = "owner_id")
    private Owner owner;

    @OneToOne(mappedBy = "store",optional = false, cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OpenTime openTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id", referencedColumnName = "area_id")
    private Area area;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "store")
    private List<Verification> verifications = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Revenue> revenues;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RentalFee> rentalFee;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @OneToOne(mappedBy = "store",cascade = CascadeType.ALL)
    private Menu menu;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "store_category",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "store_utility",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "utility_id")
    )
    private List<Utility> utilities = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseStore baseStore = (BaseStore) o;
        return Objects.equals(storeId, baseStore.storeId) && Objects.equals(storeName, baseStore.storeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, storeName);
    }

    @Override
    public String toString() {
        return "BaseStore{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", isStarted=" + isStarted +
                ", isShutdown=" + isShutdown +
                ", autoOpenSetting=" + autoOpenSetting +
                ", isOpening=" + isOpening +
                ", isHidden=" + isHidden +
                ", createdDate=" + createdDate +
                ", operationStartDate=" + operationStartDate +
                ", operationEndDate=" + operationEndDate +
                ", storeUrl='" + storeUrl + '\'' +
                '}';
    }
}
