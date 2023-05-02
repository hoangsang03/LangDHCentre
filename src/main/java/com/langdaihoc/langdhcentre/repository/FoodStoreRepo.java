package com.langdaihoc.langdhcentre.repository;

import com.langdaihoc.langdhcentre.entity.mainEntity.FoodStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodStoreRepo extends JpaRepository<FoodStore, Long> {

    /**
     * Get food store by id
     *
     * @param storeId : store's ID of the store that want to get from database
     * @return Store with given store's id or null
     */
    @Query("select s from FoodStore s where s.storeId = ?1")
    Optional<FoodStore> getFoodStoreByStoreId(long storeId);

    /**
     *
     * @param name : store name
     * @return
     */

    @Query("select s from FoodStore s where s.storeName like %:name%")
    List<FoodStore> getFoodStoreLikeName(@Param(value = "name") String storeName);

    @Query("select s from FoodStore s where s.storeName like %?1%")
    List<FoodStore> findFoodStoreByByName2(String storeName);
/**
 * 1. storeId : long		-> equals     -> Optional<FoodStore> getFoodStoreByStoreId(long storeId);
 * 	2. storeName : String 	-> LIKE
 * 	3
 * 		1.isStarted : boolean  -> equals
 * 		2.isShutdown
 * 		3.isAutoOpenSetting
 * 		4.isOpening
 * 		5.isHidden
 *
 * 	4.getStoresThatAreOpening(openingTime, closingTime) :
 * 	5.getStoresThatWasCreatedFromDateToDate(createdDate)
 *     6.getStoresThatHasStartedOperationFromDate(operationStartDate)
 * 	7.getStoresThatHasEndedOperationBeforeDate(operationEndDate)
 * 	8.getStoreUrlWithGivenStoreId(storeUrl)
 * 	9.getStoresWithGivenOwnerId(ownerId)
 *     1.getStoreWithGivenAddress(Address)
 *     2.getStoresWithGivenAreaId(areaId)
 */


}
