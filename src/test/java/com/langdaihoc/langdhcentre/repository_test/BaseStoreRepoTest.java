package com.langdaihoc.langdhcentre.repository_test;

import com.langdaihoc.langdhcentre.entity.mainEntity.Customer;
import com.langdaihoc.langdhcentre.entity.mainEntity.FoodStore;
import com.langdaihoc.langdhcentre.entity.mainEntity.Operator;
import com.langdaihoc.langdhcentre.entity.mainEntity.Owner;
import com.langdaihoc.langdhcentre.entity.subEntity.*;
import com.langdaihoc.langdhcentre.repository.BaseStoreRepo;
import com.langdaihoc.langdhcentre.repository.CoffeeShopRepo;
import com.langdaihoc.langdhcentre.repository.FoodStoreRepo;
import com.langdaihoc.langdhcentre.util.DateTimeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

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
        LocalDateTime openingTime = LocalDateTime.now();
        LocalDateTime closingTime = openingTime.plusHours(5);
        LocalDate operationStartDate = LocalDate.now();
        LocalDate operationEndDate = LocalDate.now().plusMonths(3);
        String storeUrl = "storeUrl";
        FoodStore newFoodStore = FoodStore.builder()
                .storeName(storeName)
                .openingTime(DateTimeUtil.convertToDateViaInstant(openingTime))
                .closingTime(DateTimeUtil.convertToDateViaInstant(closingTime))
                .operationStartDate(DateTimeUtil.convertToDateViaInstant(operationStartDate))
                .operationEndDate(DateTimeUtil.convertToDateViaInstant(operationEndDate))
                .storeUrl(storeUrl)
                .build();

        Owner newOwner = Owner.builder().ownerName("ownerName").build();
        newFoodStore.setOwner(newOwner);

        Address newAddress = Address.builder().city("HCM").ward("7").store(newFoodStore).build();
        newFoodStore.setAddress(newAddress);

        Area newArea = Area.builder().nameArea("LangDH").build();
        newFoodStore.setArea(newArea);

        Verification newVerification = Verification.builder().verificationName("verificationName")
                .operator(Operator.builder().operatorName("operator name").build()).build();
        Verification newVerification2 = Verification.builder().verificationName("verificationName2")
                .operator(Operator.builder().operatorName("operator name 2").build()).build();
        newFoodStore.addVerification(newVerification);
        newFoodStore.addVerification(newVerification2);

        Revenue newRevenue = Revenue.builder().totalMonthRevenue(BigDecimal.valueOf(1_000_000_000)).build();
        Revenue newRevenue2 = Revenue.builder().totalMonthRevenue(BigDecimal.valueOf(1_000_000_002)).build();
        newFoodStore.addRevenue(newRevenue);
        newFoodStore.addRevenue(newRevenue2);

        RentalFee newRentalFee = RentalFee.builder().month(LocalDate.now().getMonthValue()).build();
        RentalFee newRentalFee2 = RentalFee.builder().month(LocalDate.now().plusMonths(1).getMonthValue()).build();
        newRentalFee.setCreateByOperator(Operator.builder().operatorName("operator name").build());
        newRentalFee2.setCreateByOperator(Operator.builder().operatorName("operator name 2").build());
        newFoodStore.addRentalFee(newRentalFee);
        newFoodStore.addRentalFee(newRentalFee2);

        Customer customer = Customer.builder().customerName("customerName1").build();
        Customer customer2 = Customer.builder().customerName("customerName2").build();
        Rating newRating = Rating.builder().ratingScore(7).customer(customer).store(newFoodStore).build();
        Rating newRating2 = Rating.builder().ratingScore(8).store(newFoodStore).customer(customer2).build();
        newFoodStore.addRating(newRating);
        newFoodStore.addRating(newRating2);

        StoreImage storeImage = StoreImage.builder().imageUrl("url/image/1").build();
        StoreImage storeImage2 = StoreImage.builder().imageUrl("url/image/2").build();
        newFoodStore.addStoreImage(storeImage);
        newFoodStore.addStoreImage(storeImage2);

        // need to add food, beverage, otherService into menu
        Menu menu = Menu.builder().build();
        Food newFood = Food.builder().serviceName("food name")
                .servicePrice(BigDecimal.valueOf(150_000)).build();
        Food newFood2 = Food.builder().serviceName("food name 2")
                .servicePrice(BigDecimal.valueOf(150_002)).build();
        menu.addFood(newFood);
        menu.addFood(newFood2);
        Beverage newBeverage = Beverage.builder().serviceName("beverage name")
                .servicePrice(BigDecimal.valueOf(60_000)).build();
        Beverage newBeverage2 = Beverage.builder().serviceName("beverage name 2")
                .servicePrice(BigDecimal.valueOf(60_002)).build();
        menu.addBeverage(newBeverage);
        menu.addBeverage(newBeverage2);
        OtherService newOtherService = OtherService.builder().serviceName("OtherService name")
                .servicePrice(BigDecimal.valueOf(5_000)).build();
        OtherService newOtherService2 = OtherService.builder().serviceName("OtherService name 2")
                .servicePrice(BigDecimal.valueOf(5_002)).build();
        menu.addOtherService(newOtherService);
        menu.addOtherService(newOtherService2);
        newFoodStore.updateMenu(menu);

        Category category = Category.builder().categoryName("categoryName").build();
        Category category2 = Category.builder().categoryName("categoryName2").build();
        newFoodStore.addCategory(category);
        newFoodStore.addCategory(category2);

        Utility newUtility = Utility.builder().utilityName("utilityName").build();
        Utility newUtility2 = Utility.builder().utilityName("utilityName2").build();
        newFoodStore.addUtility(newUtility);
        newFoodStore.addUtility(newUtility2);

        FoodStore savedFoodStore = this.foodStoreRepo.save(newFoodStore);
        long storeId = savedFoodStore.getStoreId();
        System.out.println("StoreId: " + storeId);
        Optional<FoodStore> foodStoreRepoById = this.foodStoreRepo.findById(storeId);
        FoodStore foodStore = foodStoreRepoById.orElse(null);
        Date openingTime1 = foodStore.getOpeningTime();


//        DateTimeUtil.convertToLocalDateTimeViaInstant(openingTime1);
//        Date openingTime2 = foodStore.getClosingTime();
//        DateTimeUtil.convertToLocalDateTimeViaInstant(openingTime2);

        if(foodStore != null){
            System.out.println(foodStore);
        }
        System.out.println("end!");


    }



    @Test
    @DisplayName("test basic syntax")
    public void testBasicSyntax(){
        long storeId = 3;
        Optional<FoodStore> foodStoreRepoById = this.foodStoreRepo.findById(storeId);
        FoodStore foodStore = foodStoreRepoById.orElse(null);
        Date openingTime1 = foodStore.getOpeningTime();
        Time time = (Time) openingTime1;

        System.out.println(openingTime1.getClass());
        LocalTime localTime = time.toLocalTime();
        System.out.println(localTime);

        TimeZone aDefault = TimeZone.getDefault();
        Arrays.stream(TimeZone.getAvailableIDs()).forEach(s -> System.out.println(s));
        ZoneId zoneId = ZoneId.of("Asia/Saigon");
        zoneId.toString();
        /**
         *  test below
         */



    }

    @Test
    public void findByStoreIdTest() {
        long storeId = 6;
        Optional<FoodStore> foodStoreOptional = this.foodStoreRepo.findByStoreId(storeId);

        FoodStore foodStore = foodStoreOptional.orElse(null);
        if(foodStore != null){
            Date openingTime = foodStore.getOpeningTime();
            System.out.println("openingTime: " + openingTime.getClass());
//            DateTimeUtil.convertToLocalDateTime(openingTime);
//            DateTimeUtil.convertToLocalDateTimeViaMilisecond(openingTime);
//            DateTimeUtil.convertToLocalDateViaInstant(openingTime);
//            DateTimeUtil.convertToLocalDateTimeViaSqlTimestamp(openingTime);

            Date closingTime = foodStore.getClosingTime();
            System.out.println("closingTime: " + closingTime.getClass());

            Date createdDate = foodStore.getCreatedDate();
            System.out.println("createdDate: " + createdDate.getClass());

            Date operationStartDate = foodStore.getOperationStartDate();
            System.out.println("operationStartDate: " + createdDate.getClass());

            Date operationEndDate = foodStore.getOperationEndDate();
            System.out.println("operationEndDate: " + createdDate.getClass());

        }
    }

    @Test
    public void convertToDateViaSqlTimestampTest() {

        Date date = DateTimeUtil.convertToDateViaInstant(LocalDateTime.now());
        System.out.println("end");
    }
}
