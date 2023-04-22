package com.langdaihoc.langdhcentre.repository_test;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.entity.mainEntity.Customer;
import com.langdaihoc.langdhcentre.entity.mainEntity.FoodStore;
import com.langdaihoc.langdhcentre.entity.mainEntity.Owner;
import com.langdaihoc.langdhcentre.entity.subEntity.*;
import com.langdaihoc.langdhcentre.repository.BaseStoreRepo;
import com.langdaihoc.langdhcentre.repository.CoffeeShopRepo;
import com.langdaihoc.langdhcentre.repository.FoodStoreRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
@DataJpaTest
// don't use in memory database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// use real database
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BaseStoreRepoTest {
    private final BaseStoreRepo baseStoreRepo;
    private final FoodStoreRepo foodStoreRepo;
    private final CoffeeShopRepo coffeeShopRepo;


    @Autowired
    public BaseStoreRepoTest(BaseStoreRepo baseStoreRepo, FoodStoreRepo foodStoreRepo, CoffeeShopRepo coffeeShopRepo) {
        this.baseStoreRepo = baseStoreRepo;
        this.foodStoreRepo = foodStoreRepo;
        this.coffeeShopRepo = coffeeShopRepo;
    }

    /**
     *
     */
    @Test
    @DisplayName("create new store without setting any values")
    public void createNewStoreWithoutSettingAnyValues() {
        Owner newOwner = Owner.builder()
                .ownerName("admin")
                .build();
        FoodStore newEmptyFoodStore = FoodStore.builder()
                .owner(newOwner)
                .build();
        FoodStore savedFoodStore = this.foodStoreRepo.save(newEmptyFoodStore);

        /**
         * isStarted = false
         * isShutdown = false
         * autoOpenSetting = true
         * isOpening = false
         * isHidden = false
         */
        Assertions.assertAll(
                "Checking all default value attributes of FoodStore",
                () -> Assertions.assertTrue(savedFoodStore.isAutoOpenSetting()),
                () -> Assertions.assertFalse(savedFoodStore.isStarted()),
                () -> Assertions.assertFalse(savedFoodStore.isShutdown()),
                () -> Assertions.assertFalse(savedFoodStore.isOpening()),
                () -> Assertions.assertFalse(savedFoodStore.isHidden())
        );
    }

    @Test
    @DisplayName("create new store with all attributes")
    public void createNewStoreWithAllAttributes() {
        /**
         * storeId
         * storeName
         * isStarted
         * isShutdown
         * openingTime
         * closingTime
         * isAutoOpenSetting
         * isOpening
         * isHidden
         * createdDate
         * operationStartDate
         * operationEndDate
         * storeUrl
         * owner
         * address
         * area
         * verifications
         * revenues
         * rentalFee
         * ratings
         * storeImages
         * menu
         * categories
         * utilities
         */
        String storeName = "storeName";
        LocalTime openingTime = LocalTime.now();
        LocalTime closingTime = LocalTime.now().plusHours(1000);
        LocalDate operationStartDate = LocalDate.now();
        LocalDate operationEndDate = LocalDate.now().plusMonths(3);
        String storeUrl = "storeUrl";
        FoodStore newFoodStore = FoodStore.builder()
                .storeName(storeName)
                .openingTime(openingTime)
                .closingTime(closingTime)
                .operationStartDate(operationStartDate)
                .operationEndDate(operationEndDate)
                .storeUrl(storeUrl)
                .build();

        Owner newOwner = Owner.builder().ownerName("ownerName").build();
        newFoodStore.setOwner(newOwner);

        Address newAddress = Address.builder().city("HCM").ward("7").build();
        newFoodStore.setAddress(newAddress);

        Area newArea = Area.builder().nameArea("LangDH").build();
        newFoodStore.setArea(newArea);

        List<Verification> verificationList = new ArrayList<>();
        Verification newVerification = Verification.builder().verificationName("verificationName").build();
        verificationList.add(newVerification);
        Verification newVerification2 = Verification.builder().verificationName("verificationName2").build();
        verificationList.add(newVerification2);
        newFoodStore.setVerifications(verificationList);

        List<Revenue> revenueList = new ArrayList<>();
        Revenue newRevenue = Revenue.builder().totalMonthRevenue(BigDecimal.valueOf(1_000_000_000)).build();
        revenueList.add(newRevenue);
        Revenue newRevenue2 = Revenue.builder().totalMonthRevenue(BigDecimal.valueOf(1_000_000_002)).build();
        revenueList.add(newRevenue2);
        newFoodStore.setRevenues(revenueList);

        List<RentalFee> rentalFeeList = new ArrayList<>();
        RentalFee newRentalFee = RentalFee.builder().month(LocalDate.now().getMonthValue()).build();
        rentalFeeList.add(newRentalFee);
        RentalFee newRentalFee2 = RentalFee.builder().month(LocalDate.now().plusMonths(1).getMonthValue()).build();
        rentalFeeList.add(newRentalFee2);
        newFoodStore.setRentalFees(rentalFeeList);

        List<Rating> ratingList = new ArrayList<>();
        Customer customer = Customer.builder().customerName("customerName1").build();
        Rating newRating = Rating.builder().ratingScore(7).customer(customer).store(newFoodStore).build();
        ratingList.add(newRating);

        Customer customer2 = Customer.builder().customerName("customerName2").build();
        Rating newRating2 = Rating.builder().ratingScore(8).store(newFoodStore).customer(customer2).build();
        ratingList.add(newRating2);
        newFoodStore.setRatings(ratingList);

        List<StoreImage> storeImageList = new ArrayList<>();
        StoreImage storeImage = StoreImage.builder().imageUrl("url/image/1").build();
        storeImageList.add(storeImage);
        StoreImage storeImage2 = StoreImage.builder().imageUrl("url/image/2").build();
        storeImageList.add(storeImage2);
        newFoodStore.setStoreImages(storeImageList);

        // need to add food, beverage, otherService into menu
        Menu menu = Menu.builder().build();
        newFoodStore.setMenu(menu);

        List<Category> categoryList = new ArrayList<>();
        Category category = Category.builder().categoryName("categoryName").build();
        categoryList.add(category);
        Category category2 = Category.builder().categoryName("categoryName2").build();
        categoryList.add(category2);
        newFoodStore.setCategories(categoryList);

        List<Utility> utilityList = new ArrayList<>();
        Utility newUtility = Utility.builder().utilityName("utilityName").build();
        utilityList.add(newUtility);
        Utility newUtility2 = Utility.builder().utilityName("utilityName2").build();
        utilityList.add(newUtility2);
        newFoodStore.setUtilities(utilityList);

        FoodStore savedFoodStore = this.foodStoreRepo.save(newFoodStore);
        System.out.println("StoreId: " + savedFoodStore.getStoreId());


    }
}
