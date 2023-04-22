package com.langdaihoc.langdhcentre.entity.mainEntity;

import com.langdaihoc.langdhcentre.entity.subEntity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "stores")
public class BaseStore {
    public BaseStore() {
    }

    {
        // this is an initializer block , it always executes when any constructor has been called
        this.isStarted = false;
        this.isShutdown = false;
        this.isAutoOpenSetting = true;
        this.isOpening = false;
        this.isHidden = false;
    }

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
    @Builder.Default
    private boolean isStarted = false;

    /**
     * if isShutdown is true, it means this store has stopped business
     * -> then customer cannot see it
     * default it is false when initialize
     */
    @Column(name = "is_shutdown")
    @Builder.Default
    private boolean isShutdown = false;

    /**
     * if autoOpenSetting is false, need to check isOpening in order to know
     * is it opening now?
     * default it is true when initialize
     * -> and check is it opening now  by OpenTime
     */


    @Column(name = "opening_time")
    private LocalTime openingTime;

    @Column(name = "closing_time")
    private LocalTime closingTime;

    @Column(name = "is_auto_open_setting")
    @Builder.Default
    private boolean isAutoOpenSetting = true;
    /**
     * if isOpening is true, it means this store is opening at that moment
     * default it is false when initialize
     * -> only need to check it when autoOpenSetting = false;
     */
    @Column(name = "is_opening")
    @Builder.Default
    private boolean isOpening = false;

    /**
     * is isHidden = true: no one can see it on web except authorized operator
     * default: isHidden = false: everyone can see it
     */
    @Column(name = "is_hidden")
    @Builder.Default
    private boolean isHidden = false;

    /**
     * the date the store has been created in database
     */
    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "operation_start_date")
    private LocalDate operationStartDate;

    @Column(name = "operation_end_date")
    private LocalDate operationEndDate;


    @Column(name = "store_url")
    private String storeUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false, referencedColumnName = "owner_id")
    private Owner owner;

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id", referencedColumnName = "area_id")
    private Area area;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "store")
    @Builder.Default
    private List<Verification> verifications = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Revenue> revenues = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<RentalFee> rentalFees = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StoreImage> storeImages = new ArrayList<>();

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
    private Menu menu;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "store_category",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "store_utility",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "utility_id")
    )
    @Builder.Default
    private List<Utility> utilities = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseStore baseStore = (BaseStore) o;
        return isStarted == baseStore.isStarted && isShutdown == baseStore.isShutdown && isAutoOpenSetting == baseStore.isAutoOpenSetting && isOpening == baseStore.isOpening && isHidden == baseStore.isHidden && storeId.equals(baseStore.storeId) && storeName.equals(baseStore.storeName) && Objects.equals(openingTime, baseStore.openingTime) && Objects.equals(closingTime, baseStore.closingTime) && Objects.equals(createdDate, baseStore.createdDate) && Objects.equals(operationStartDate, baseStore.operationStartDate) && Objects.equals(operationEndDate, baseStore.operationEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, storeName, isStarted, isShutdown, openingTime, closingTime, isAutoOpenSetting, isOpening, isHidden, createdDate, operationStartDate, operationEndDate);
    }

    @Override
    public String toString() {
        return "BaseStore{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", isStarted=" + isStarted +
                ", isShutdown=" + isShutdown +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                ", isAutoOpenSetting=" + isAutoOpenSetting +
                ", isOpening=" + isOpening +
                ", isHidden=" + isHidden +
                ", createdDate=" + createdDate +
                ", operationStartDate=" + operationStartDate +
                ", operationEndDate=" + operationEndDate +
                ", storeUrl='" + storeUrl + '\'' +
                '}';
    }

    public void addVerification(Verification verificationInput) {
        verificationInput.setStore(this);
        if(this.verifications == null){
            this.verifications = new ArrayList<>();
        }
        this.verifications.add(verificationInput);
    }

    public void addRevenue(Revenue revenueInput){
        revenueInput.setStore(this);
        if(this.revenues == null){
            this.revenues = new ArrayList<>();
        }
        this.revenues.add(revenueInput);
    }
    public void addRentalFee(RentalFee rentalFeeInput){
        rentalFeeInput.setStore(this);
        if(this.rentalFees == null){
            this.rentalFees = new ArrayList<>();
        }
        this.rentalFees.add(rentalFeeInput);
    }

    public void addRating(Rating ratingInput){
        ratingInput.setStore(this);
        if(this.ratings == null){
            this.ratings = new ArrayList<>();
        }
        this.ratings.add(ratingInput);
    }

    public void addStoreImage(StoreImage storeImageInput){
        storeImageInput.setStore(this);
        if(this.storeImages == null){
            this.storeImages = new ArrayList<>();
        }
        this.storeImages.add(storeImageInput);
    }
    public void addUtility(Utility utilityInput){
        if(this.utilities == null){
            this.utilities = new ArrayList<>();
        }
        this.utilities.add(utilityInput);
    }
    public void addCategory(Category categoryInput){
        if(this.categories == null){
            this.categories = new ArrayList<>();
        }
        this.categories.add(categoryInput);
    }






}
