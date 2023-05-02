package com.langdaihoc.langdhcentre.repository;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore_;
import com.langdaihoc.langdhcentre.entity.mainEntity.FoodStore;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaseStoreRepo extends JpaRepository<BaseStore, Long>,
        JpaSpecificationExecutor<BaseStore> {
    interface BaseStoreSpecification{
        static Specification<BaseStore> likeName(String storeName){
            return  ((root, query, criteriaBuilder) ->
                    (criteriaBuilder.like((root.get(BaseStore_.storeName)),storeName)));
        }

        static Specification<BaseStore> byId(long storeId){
            return (root, query, criteriaBuilder) ->
                    (criteriaBuilder.equal(root.get(BaseStore_.storeId),storeId));
        }
    }
    /**
     * 1. storeId : long		-> equals
     * 2. storeName : String 	-> LIKE
     * 3
     * 1.isStarted : boolean  -> equals
     * 2.isShutdown
     * 3.isAutoOpenSetting
     * 4.isOpening
     * 5.isHidden
     * <p>
     * 4.getStoresThatAreOpening(openingTime, closingTime) :
     * 5.getStoresThatWasCreatedFromDateToDate(createdDate)
     * 6.getStoresThatHasStartedOperationFromDate(operationStartDate)
     * 7.getStoresThatHasEndedOperationBeforeDate(operationEndDate)
     * 8.getStoreUrlWithGivenStoreId(storeUrl)
     * 9.getStoresWithGivenOwnerId(ownerId)
     * 1.getStoreWithGivenAddress(Address)
     * 2.getStoresWithGivenAreaId(areaId)
     */

    /**
     * @param storeType   : storeType of stores
     * @param storeId : primary key of stores
     * @return : BaseStore with given dtype and store_id
     */
    @Query(value = "select * from stores s where s.store_type = :storeType and s.store_id = :storeId"
    ,nativeQuery = true)
    BaseStore getStoreWithStoreTypeAndStoreId(@Param("storeType") int storeType,
                                          @Param("storeId") long storeId);

    @Query(value = "select * from stores s where s.store_id = :storeId"
            , nativeQuery = true)
    Optional<BaseStore> getBaseStoreWithStoreId(@Param("storeId") long storeId);

    @Query(value ="select * from stores s where s.store_name like %:name%",
    nativeQuery = true)
    List<BaseStore> getBaseStoreLikeName(@Param("name") String storeName);





}
