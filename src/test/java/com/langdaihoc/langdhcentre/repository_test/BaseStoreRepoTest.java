package com.langdaihoc.langdhcentre.repository_test;

import com.langdaihoc.langdhcentre.common.StoreTypeConstant;
import com.langdaihoc.langdhcentre.entity.mainEntity.*;
import com.langdaihoc.langdhcentre.entity.subEntity.*;
import com.langdaihoc.langdhcentre.repository.BaseStoreRepo;
import com.langdaihoc.langdhcentre.repository.CoffeeShopRepo;
import com.langdaihoc.langdhcentre.repository.FoodStoreRepo;
import com.langdaihoc.langdhcentre.repository.common.Filter;
import com.langdaihoc.langdhcentre.repository.common.QueryOperator;
import com.langdaihoc.langdhcentre.util.DateTimeUtil;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.AssertionFailure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.langdaihoc.langdhcentre.repository.BaseStoreRepo.BaseStoreSpecification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

//@SpringBootTest
@DataJpaTest
// don't use in memory database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// use real database
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Slf4j
public class BaseStoreRepoTest {
    private static final String CLASS_NAME = "BaseStoreRepoTest";
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
    @DisplayName("createNewBaseStore test")
    public void createNewDefaultBaseStore() {
        Owner newOwner2 = Owner.builder()
                .ownerName("admin")
                .build();
        BaseStore newEmptyBaseStore = BaseStore.builder()
                .owner(newOwner2)
                .build();
        BaseStore savedBaseStore = this.baseStoreRepo.save(newEmptyBaseStore);
    }

    @Test
    @DisplayName("createNewCoffeeShop test")
    public void createNewDefaultCoffeeShop() {
        Owner newOwner2 = Owner.builder()
                .ownerName("admin")
                .build();
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.setOwner(newOwner2);

        CoffeeShop savedBaseStore = this.coffeeShopRepo.save(coffeeShop);
    }

    @Test
    @DisplayName("createNewFoodStoreWithoutSettingAnyValues test")
    public void createNewDefaultFoodStore() {
        Owner newOwner = Owner.builder()
                .ownerName("admin")
                .build();
        FoodStore newEmptyFoodStore = new FoodStore();
        newEmptyFoodStore.setOwner(newOwner);
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
    @DisplayName("createFoodStoreWithAllAttributes")
    public void createFoodStoreWithAllAttributes() {
        /**
         * storeId
         * storeName
         * Start Attributes have default value:
         * isStarted
         * isShutdown
         * isAutoOpenSetting
         * isOpening
         * isHidden
         * End
         * openingTime
         * closingTime
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
        String storeName = "storeName2";
        LocalTime openingTime = LocalTime.now();
        LocalTime closingTime = openingTime.plusHours(5);
        LocalDate operationStartDate = LocalDate.now();
        LocalDate operationEndDate = LocalDate.now().plusMonths(3);
        String storeUrl = "storeUrl";
        FoodStore newFoodStore = new FoodStore();
        newFoodStore.setStoreName(storeName);
        newFoodStore.setOpeningTime(openingTime);
        newFoodStore.setClosingTime(closingTime);
        newFoodStore.setOperationStartDate(DateTimeUtil.convertToDateViaInstant(operationStartDate));
        newFoodStore.setOperationEndDate(DateTimeUtil.convertToDateViaInstant(operationEndDate));
        newFoodStore.setStoreUrl(storeUrl);

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
        assert foodStore != null;


        if (foodStore != null) {
            System.out.println(foodStore);
        }
        System.out.println("end!");


    }


    @Test
    @DisplayName("test basic syntax")
    public void testBasicSyntax() {
        long storeId = 3;
        Optional<FoodStore> foodStoreRepoById = this.foodStoreRepo.findById(storeId);
        FoodStore foodStore = (FoodStore) foodStoreRepoById.orElse(null);
        LocalTime openingTime1 = foodStore.getOpeningTime();


        TimeZone aDefault = TimeZone.getDefault();
        Arrays.stream(TimeZone.getAvailableIDs()).forEach(System.out::println);
        ZoneId zoneId = ZoneId.of("Asia/Saigon");
        zoneId.toString();
        /**
         *  test below
         */


    }

    @Test
    public void findByStoreIdTest() {
        long storeId = 3;
        Optional<FoodStore> foodStoreOptional = this.foodStoreRepo.getFoodStoreByStoreId(storeId);

        FoodStore foodStore = foodStoreOptional.orElse(null);
        if (foodStore != null) {
            LocalTime openingTime = foodStore.getOpeningTime();
            System.out.println("openingTime: " + openingTime.getClass());

            LocalTime closingTime = foodStore.getClosingTime();
            System.out.println("closingTime: " + closingTime.getClass());

            Date createdDate = foodStore.getCreatedDate();
            System.out.println("createdDate: " + createdDate.getClass());

            Date operationStartDate = foodStore.getOperationStartDate();
            System.out.println("operationStartDate: " + operationStartDate.getClass());

            Date operationEndDate = foodStore.getOperationEndDate();
            System.out.println("operationEndDate: " + operationEndDate.getClass());

        }
    }

    @Test
    @DisplayName("find FoodStore Has Name That Contain Given String")
    public void findFoodStoreByNameTest() {
        String storeName = "";
        List<FoodStore> storeNameList = this.foodStoreRepo.findFoodStoreByByName2(storeName);
        System.out.println("condition storeName Like " + storeName);
        System.out.println("storeNameList.size() : " + storeNameList.size());
        storeNameList.forEach(s -> System.out.println(s.getStoreName()));

        storeName = "2";
        List<FoodStore> storeNameList2 = this.foodStoreRepo.findFoodStoreByByName2(storeName);
        System.out.println("condition storeName Like " + storeName);
        System.out.println("storeNameList.size() : " + storeNameList2.size());
        storeNameList2.forEach(s -> System.out.println(s.getStoreName()));

        storeName = "storeName";
        storeNameList = this.foodStoreRepo.findFoodStoreByByName2(storeName);
        System.out.println("condition storeName Like " + storeName);
        System.out.println("storeNameList.size() : " + storeNameList.size());
        storeNameList.forEach(s -> System.out.println(s.getStoreName()));

        storeName = "storeName2";
        storeNameList = this.foodStoreRepo.findFoodStoreByByName2(storeName);
        System.out.println("condition storeName Like " + storeName);
        System.out.println("storeNameList.size() : " + storeNameList.size());
        storeNameList.forEach(s -> System.out.println(s.getStoreName()));
    }

    @Test
    @DisplayName("getStoreWithStoreTypeAndStoreId Test")
    public void getStoreWithStoreTypeAndStoreIdTest() {
        log.info("getStoreWithDtypeAndStoreIdTest start");
        String dtype = "FoodStore";
        long storeId = 3;
        try {
            BaseStore foodStore = this.baseStoreRepo.getStoreWithStoreTypeAndStoreId(
                    StoreTypeConstant.FOOD_STORE, storeId);
            System.out.println("foodStore: " + foodStore);
            log.debug("foodStore: " + foodStore);
        } catch (Exception e) {
            log.error("getStoreWithDtypeAndStoreIdTest has errors", e);
        }

        try {
            CoffeeShop coffeeShop = (CoffeeShop) this.baseStoreRepo.getStoreWithStoreTypeAndStoreId(
                    StoreTypeConstant.COFFEE_SHOP, storeId);

            System.out.println("coffeeShop: " + coffeeShop);
            log.debug("coffeeShop: " + coffeeShop);
        } catch (Exception e) {
            log.error("getStoreWithDtypeAndStoreIdTest has errors", e);
        }
    }

    @Test
    @DisplayName("getBaseStoreWithStoreId Test")
    public void getBaseStoreWithStoreIdTest() {
        long storeId = 3;
        Optional<BaseStore> baseStoreOptional = this.baseStoreRepo.getBaseStoreWithStoreId(storeId);
        BaseStore baseStore;
        if (baseStoreOptional.isPresent()) {
            baseStore = baseStoreOptional.get();
            log.debug("storeType: " + baseStore.getStoreType());
            if (baseStore instanceof FoodStore) {
                FoodStore foodStore = (FoodStore) baseStore;
                log.debug("baseStore is instance of FoodStore");
                log.debug("foodStore: " + foodStore);
            } else if (baseStore instanceof CoffeeShop) {
                CoffeeShop coffeeShop = (CoffeeShop) baseStore;
                log.debug("baseStore is instance of CoffeeShop");
                log.debug("foodStore: " + coffeeShop);
            } else {
                log.debug("baseStore is instance of BaseStore");
            }
        } else {
            log.debug("getBaseStoreWithStoreId return null with storeId : " + storeId);
        }

    }

    @Test
    @DisplayName("getBaseStoreLikeName test")
    public void getBaseStoreLikeNameTest() {
        String storeName = "";
        try {
            List<BaseStore> baseStoreLikeName = this.baseStoreRepo.getBaseStoreLikeName(storeName);
            log.debug("baseStoreLikeName.size(): " + baseStoreLikeName.size());
            baseStoreLikeName.forEach(s -> log.debug(s.getClass().getSimpleName()));
        } catch (Exception e) {
            log.error("getBaseStoreLikeNameTest", e);
        }
    }

    @Test
    @DisplayName("BaseStoreCustom: likeName & byId & wasStarted test")
    public void specLikeNameByIdWasStartedTest() {
        String storeName = "%store%";
        long storeId = 4;
        boolean isStarted = false;
        try {
            List<BaseStore> baseStoreLikeName = this.baseStoreRepo.findAll(
                    BaseStoreRepo.BaseStoreSpecification.likeName(storeName)
                            .and(BaseStoreRepo.BaseStoreSpecification.byId(storeId))
                            .and(BaseStoreRepo.BaseStoreSpecification.wasStarted(isStarted)));
            log.debug("baseStoreLikeName.size(): " + baseStoreLikeName.size());
            baseStoreLikeName.forEach(s -> {
                        log.debug(s.getClass().getSimpleName());
                        log.debug(s.toString());
                    }
            );
        } catch (Exception e) {
            log.error("getBaseStoreLikeNameTest", e);
        }
    }

    @Test
    @DisplayName("BaseStoreCustom: areOpeningAccordingToOpeningAndClosingTime test")
    public void specAreOpeningAccordingToOpeningAndClosingTimeTest() {
        try {
            List<BaseStore> baseStoreLikeName = this.baseStoreRepo.findAll(
                    BaseStoreRepo.BaseStoreSpecification.areOpeningAccordingToOpeningAndClosingTime()
            );
            log.debug("baseStoreLikeName.size(): " + baseStoreLikeName.size());
            baseStoreLikeName.forEach(s -> {
                        log.debug(s.getClass().getSimpleName());
                        log.debug("store_id: " + s.getStoreId());
                        log.debug("opening_time: " + s.getOpeningTime());
                        log.debug("closing_time: " + s.getClosingTime());
                    }
            );
        } catch (Exception e) {
            log.error("specAreOpeningAccordingToOpeningAndClosingTimeTest has error", e);
        }

    }

    @Test
    @DisplayName("storesThatWasCreatedFromDateToDate test")
    public void storesThatWasCreatedFromDateToDateTest() {
        Date fromDate = new Date();
        Date toDate = new Date();
        BaseStoreSpecification.storesThatWasCreatedFromDateToDate(fromDate,toDate);
    }
    @Test
    @DisplayName("createSpecificationWithFilter test")
    public void createSpecificationWithFilterTest() {
        List<Filter> filters = new ArrayList<>();
        Filter hasOpened = Filter.builder()
                .field("openingTime")
                .operator(QueryOperator.LESS_THAN)
                .value(String.valueOf(LocalTime.now()))
                .build();
        filters.add(hasOpened);

        Filter hasNotClosed = Filter.builder()
                .field("closingTime")
                .operator(QueryOperator.GREATER_THAN)
                .value(String.valueOf(LocalTime.now()))
                .build();
        filters.add(hasNotClosed);
        filters.forEach( f -> {
            log.debug("s " + f.toString());
        });
        try {
            Specification<BaseStore> specificationFromFilters = BaseStoreSpecification.getSpecificationFromFilters(filters);
            List<BaseStore> storeList = this.baseStoreRepo.findAll(specificationFromFilters);
            log.debug("storeList.size(): " + storeList.size());
            storeList.forEach(s -> {
                        log.debug(s.getClass().getSimpleName());
                        log.debug("store_id: " + s.getStoreId());
                        log.debug("opening_time: " + s.getOpeningTime());
                        log.debug("closing_time: " + s.getClosingTime());
                    }
            );
        } catch (Exception ex){
            log.error("BaseStoreTest createSpecificationWithFilterTest", ex);
        }

    }

    @Test
    public void getAllBaseStoreWithLimitAndOffset(){
        try{
            Specification<BaseStore> spec = null;
        } catch (Exception ex){
            Assertions.fail("getAllBaseStoreWithLimitAndOffset : Unexpected exception has raised!");
        }
    }


}
