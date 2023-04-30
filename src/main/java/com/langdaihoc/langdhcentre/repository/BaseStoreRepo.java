package com.langdaihoc.langdhcentre.repository;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.entity.mainEntity.FoodStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseStoreRepo extends JpaRepository<BaseStore,Long>,BaseStoreRepoCustom {
    /**
     * 1. storeId : long		-> equals
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
    /**
     * Get food store by id
     * @param storeId : store's ID of the store that want to get from database
     * @return  with given store's id or null
     */



}
