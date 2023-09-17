package com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.langdaihoc.langdhcentre.storeManagement.common.StoreTypeConstant;
import com.langdaihoc.langdhcentre.storeManagement.entity.address.*;
import com.langdaihoc.langdhcentre.storeManagement.entity.auth.Owner;
import com.langdaihoc.langdhcentre.storeManagement.entity.common.AbstractEntity;
import com.langdaihoc.langdhcentre.storeManagement.entity.store.StoreCategory;
import com.langdaihoc.langdhcentre.storeManagement.entity.store.StoreImage;
import com.langdaihoc.langdhcentre.storeManagement.entity.store.StoreRating;
import com.langdaihoc.langdhcentre.storeManagement.entity.subEntity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalTime;
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
@DiscriminatorColumn(name = "store_type",
        discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "stores")
public class BaseStore extends AbstractEntity {
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
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false)
    @Builder.Default
    protected int type = StoreTypeConstant.BASE_STORE;

    @Column(name = "name")
    private String name;

    /**
     * if isStarted is true, it means this store has started business
     * default it is false when initialize
     */
    @Column(name = "is_started")
    @Builder.Default
    private boolean isStarted = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "province_code", referencedColumnName = "code")
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "district_code", referencedColumnName = "code")
    private District district;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ward_code", referencedColumnName = "code")
    private Ward ward;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "street_code", referencedColumnName = "code")
    private Street street;

    @Column(name = "fullAddress")
    private String fullAddress;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;


    @Column(name = "website")
    private String website;

    @Column(name = "description")
    private String description;


    @Column(name = "logo")
    private String logo;

    @Column(name = "top_store_order")
    private int topStoreOrder;

    @Column(name = "is_force_open")
    private boolean isForceOpen;

    @Column(name = "is_force_close")
    private boolean isForceClose;

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
    @JsonFormat(pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
    private LocalTime openingTime;

    @Column(name = "closing_time")
    @JsonFormat(pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
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
    @JsonFormat(pattern = "YYYY-MM-DD")
    @Temporal(TemporalType.DATE)
    private Date operationStartDate;

    @Column(name = "operation_end_date")
    @JsonFormat(pattern = "YYYY-MM-DD")
    @Temporal(TemporalType.DATE)
    private Date operationEndDate;


    @Column(name = "store_url")
    private String storeUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = false, referencedColumnName = "id")
    private Owner owner;

    /**
     * document for @LazyToOne: <a href="https://vladmihalcea.com/hibernate-lazytoone-annotation/">@LazyToOne</a>
     */
//    @OneToOne(mappedBy = "store", optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    @LazyToOne(LazyToOneOption.NO_PROXY)
//    @NotNull
//    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "store")
    @Builder.Default
    private List<Verification> verifications = new ArrayList<>();

//    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Builder.Default
//    private List<Revenue> revenues = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<RentalFee> rentalFees = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<StoreRating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<StoreImage> storeImages = new ArrayList<>();

    /**
     * document for @LazyToOne: <a href="https://vladmihalcea.com/hibernate-lazytoone-annotation/">@LazyToOne</a>
     */
//    @OneToOne(mappedBy = "store", optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @LazyToOne(LazyToOneOption.NO_PROXY)
//    @PrimaryKeyJoinColumn
//    private Menu menu;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "store_category",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Builder.Default
    private List<StoreCategory> storeCategories = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
        return isStarted == baseStore.isStarted && isShutdown == baseStore.isShutdown && isAutoOpenSetting == baseStore.isAutoOpenSetting && isOpening == baseStore.isOpening && isHidden == baseStore.isHidden && id.equals(baseStore.id) && name.equals(baseStore.name) && Objects.equals(openingTime, baseStore.openingTime) && Objects.equals(closingTime, baseStore.closingTime) && Objects.equals(createdDate, baseStore.createdDate) && Objects.equals(operationStartDate, baseStore.operationStartDate) && Objects.equals(operationEndDate, baseStore.operationEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isStarted, isShutdown, openingTime, closingTime, isAutoOpenSetting, isOpening, isHidden, createdDate, operationStartDate, operationEndDate);
    }

    //<editor-fold desc="addObjectIntoList">
    public boolean addVerification(Verification verificationInput) {
        verificationInput.setStore(this);
        if (this.verifications == null) {
            this.verifications = new ArrayList<>();
        }
        this.verifications.add(verificationInput);
        return true;
    }

//    public boolean addRevenue(Revenue revenueInput) {
//        revenueInput.setStore(this);
//        if (this.revenues == null) {
//            this.revenues = new ArrayList<>();
//        }
//        this.revenues.add(revenueInput);
//        return true;
//    }

    public boolean addRentalFee(RentalFee rentalFeeInput) {
        rentalFeeInput.setStore(this);
        if (this.rentalFees == null) {
            this.rentalFees = new ArrayList<>();
        }
        this.rentalFees.add(rentalFeeInput);
        return true;
    }

    public boolean addRating(StoreRating ratingInput) {
        ratingInput.setStore(this);
        if (this.ratings == null) {
            this.ratings = new ArrayList<>();
        }
        this.ratings.add(ratingInput);
        return true;
    }

    public boolean addStoreImage(StoreImage storeImageInput) {
        storeImageInput.setStore(this);
        if (this.storeImages == null) {
            this.storeImages = new ArrayList<>();
        }
        this.storeImages.add(storeImageInput);
        return true;
    }

    public boolean addUtility(Utility utilityInput) {
        if (this.utilities == null) {
            this.utilities = new ArrayList<>();
        }
        this.utilities.add(utilityInput);
        return true;
    }

    public boolean addCategory(StoreCategory categoryInput) {
        if (this.storeCategories == null) {
            this.storeCategories = new ArrayList<>();
        }
        this.storeCategories.add(categoryInput);
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="UpdateAddress&Menu">
//    public Address updateAddress(@NotNull Address addressInput) {
//        addressInput.setStore(this);
//        this.address = addressInput;
//        return this.address;
//    }

//    public Menu updateMenu(@NotNull Menu menuInput) {
//        menuInput.setStore(this);
//        this.menu = menuInput;
//        return this.menu;
//    }
    //</editor-fold>

    //<editor-fold desc="SetListObject">
    public boolean setVerifications(List<Verification> verificationsInput) {
        verificationsInput.forEach(v -> {
            v.setStore(this);
        });
        this.verifications = new ArrayList<>(verificationsInput);
        return this.verifications != verificationsInput;
    }

//    public boolean setRevenues(List<Revenue> revenuesInput) {
//        revenuesInput.forEach(r -> {
//            r.setStore(this);
//        });
//        this.revenues = new ArrayList<>(revenuesInput);
//        return this.revenues != revenuesInput;
//    }

    public boolean setRentalFees(List<RentalFee> rentalFeesInput) {
        rentalFeesInput.forEach(r -> {
            r.setStore(this);
        });
        this.rentalFees = new ArrayList<>(rentalFeesInput);
        return this.rentalFees != rentalFeesInput;
    }

    public boolean setRatings(List<StoreRating> ratingsInput) {
        ratingsInput.forEach(r -> {
            r.setStore(this);
        });
        this.ratings = new ArrayList<>(ratingsInput);
        return this.ratings != ratingsInput;
    }

    public boolean setStoreImages(List<StoreImage> storeImagesInput) {
        storeImagesInput.forEach(s -> {
            s.setStore(this);
        });
        this.storeImages = new ArrayList<>(storeImagesInput);
        return this.storeImages != storeImagesInput;
    }


    //</editor-fold>


    @Override
    public String toString() {
        return "BaseStore{" +
                "storeId=" + id +
                ", storeName='" + name + '\'' +
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
}
