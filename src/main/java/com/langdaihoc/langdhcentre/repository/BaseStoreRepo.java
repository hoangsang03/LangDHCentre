package com.langdaihoc.langdhcentre.repository;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore_;
import com.langdaihoc.langdhcentre.repository.common.Filter;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;


@Repository
public interface BaseStoreRepo extends JpaRepository<BaseStore, Long>,
        JpaSpecificationExecutor<BaseStore> {



    interface BaseStoreSpecification {
        @Slf4j
        final class LogHolder {
        }

        static Specification<BaseStore> likeName(String storeName) {
            return ((root, query, criteriaBuilder) ->
                    (criteriaBuilder.like((root.get(BaseStore_.storeName)), storeName)));
        }

        /**
         * @param storeId : id of store
         * @return : return specification for base store has given storeId
         */
        static Specification<BaseStore> byId(long storeId) {
            return (root, query, criteriaBuilder) ->
                    (criteriaBuilder.equal(root.get(BaseStore_.storeId), storeId));
        }

        //<editor-fold desc="specification for boolean attributes">
        static Specification<BaseStore> wasStarted(boolean isStarted) {
            return ((root, query, criteriaBuilder) ->
                    (criteriaBuilder.equal(root.get(BaseStore_.isStarted), isStarted)));
        }

        static Specification<BaseStore> wasShutdown(boolean isShutdown) {
            return ((root, query, criteriaBuilder) ->
                    (criteriaBuilder.equal(root.get(BaseStore_.isShutdown), isShutdown)));
        }

        static Specification<BaseStore> wasAutoOpeningSetting(boolean isAutoOpeningSetting) {
            return ((root, query, criteriaBuilder) ->
                    (criteriaBuilder.equal(root.get(BaseStore_.isShutdown), isAutoOpeningSetting)));
        }

        static Specification<BaseStore> wasOpening(boolean isOpening) {
            return ((root, query, criteriaBuilder) ->
                    (criteriaBuilder.equal(root.get(BaseStore_.isShutdown), isOpening)));
        }

        static Specification<BaseStore> wasHidden(boolean isHidden) {
            return ((root, query, criteriaBuilder) ->
                    (criteriaBuilder.equal(root.get(BaseStore_.isShutdown), isHidden)));
        }
        //</editor-fold>

        /**
         * condition: where BaseStore_.openingTime < LocalTime.now() and LocalTime.now() < BaseStore_.closingTime
         *
         * @return : return specification for base store according to condition
         */
        static Specification<BaseStore> areOpeningAccordingToOpeningAndClosingTime() {
            LocalTime timeNow = LocalTime.now();

            System.out.println("timeNow: " + timeNow);
            return (root, query, criteriaBuilder) -> (
                    criteriaBuilder.and(
                            criteriaBuilder.lessThan(root.get(BaseStore_.openingTime), timeNow),
                            criteriaBuilder.greaterThan(root.get(BaseStore_.closingTime), timeNow)
                    )
            );
        }

        /**
         * @param fromDate : the beginning date
         * @param toDate   : the ending date
         * @return : return specification for base stores that was created from fromDate to toDate
         */
        static Specification<BaseStore> storesThatWasCreatedFromDateToDate(Date fromDate, Date toDate) {
            return (root, query, criteriaBuilder) -> (
                    criteriaBuilder.between(root.get(BaseStore_.createdDate), fromDate, toDate)
            );
        }

        /**
         * @param date : a date has year,month,date
         * @return : specification for base store has started operation from date
         */
        static Specification<BaseStore> hasStartedOperationAfterDate(Date date) {
            return (root, query, criteriaBuilder) ->
                    (criteriaBuilder.greaterThanOrEqualTo(root.get(BaseStore_.operationStartDate),
                            date));
        }

        /**
         * @param date : a date has year,month,date
         * @return : specification for base store has started operation from date
         */
        static Specification<BaseStore> hasStartedOperationBeforeDate(Date date) {
            return (root, query, criteriaBuilder) ->
                    (criteriaBuilder.lessThanOrEqualTo(root.get(BaseStore_.operationStartDate),
                            date));
        }

        /**
         * @param date : a date has year,month,date
         * @return : specification for base store has ended operation from date
         */
        static Specification<BaseStore> hasEndedOperationAfterDate(Date date) {
            return (root, query, criteriaBuilder) ->
                    (criteriaBuilder.greaterThanOrEqualTo(root.get(BaseStore_.operationEndDate),
                            date));
        }

        /**
         * @param date : a date has year,month,date
         * @return : specification for base store has ended operation from date
         */
        static Specification<BaseStore> hasEndedOperationBeforeDate(Date date) {
            return (root, query, criteriaBuilder) ->
                    (criteriaBuilder.lessThanOrEqualTo(root.get(BaseStore_.operationEndDate),
                            date));
        }

        /**
         * @param ownerId : ownerId of base store
         * @return : store is belong to owner that has ownerId
         */
        static Specification<BaseStore> hasOwnerId(long ownerId) {
            long storeId = ownerId;
            return byId(ownerId);
        }

        /**
         * @param areaId : areaId of base store
         * @return : store is belong to area that has areaId
         */
        static Specification<BaseStore> hasAreaId(long areaId) {
            return ((root, query, criteriaBuilder) ->
                    (criteriaBuilder.equal(root.get(BaseStore_.area), areaId)));
        }

        static Specification<BaseStore> getSpecificationFromFilters(List<Filter> filter) {
            Specification<BaseStore> specification = where(createSpecificationWithFilter(filter.remove(0)));
            System.out.println("specification " + specification.toString());
            for (Filter input : filter) {
                specification = specification.and(createSpecificationWithFilter(input));
            }
            return specification;
        }

        static Specification<BaseStore> createSpecificationWithFilter(Filter input) {
            switch (input.getOperator()) {
                case EQUALS:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.equal(root.get(input.getField()),
                                    castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
                case NOT_EQ:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.notEqual(root.get(input.getField()),
                                    castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
                case GREATER_THAN:
                    return (root, query, criteriaBuilder) -> {
                        LogHolder.log.debug("root.get(input.getField()) : " + root.get(input.getField()));
                        Class<?> fieldType = root.get(input.getField()).getJavaType();
                        LogHolder.log.debug("root.get(input.getField()).getJavaType() : " + fieldType);
                        Predicate greaterThan;
                        if (fieldType.isAssignableFrom(Number.class) || fieldType.isAssignableFrom(Enum.class)) {
                            greaterThan = criteriaBuilder.gt(root.get(input.getField()),
                                    (Number) castToRequiredType(fieldType, input.getValue()));
                        } else {
                            greaterThan = criteriaBuilder.greaterThan(root.get(input.getField()),
                                    (String) castToRequiredType(fieldType, input.getValue()));
                        }

                        return greaterThan;
                    };

                case LESS_THAN:
                    return (root, query, criteriaBuilder) -> {
                        LogHolder.log.debug("root.get(input.getField()) : " + root.get(input.getField()));
                        Class<?> fieldType = root.get(input.getField()).getJavaType();
                        LogHolder.log.debug("root.get(input.getField()).getJavaType() : " + fieldType);
                        Predicate lessThan;
                        if (fieldType.isAssignableFrom(Number.class) || fieldType.isAssignableFrom(Enum.class)) {
                            lessThan = criteriaBuilder.lt(root.get(input.getField()),
                                    (Number) castToRequiredType(fieldType, input.getValue()));
                        } else {
                            lessThan = criteriaBuilder.lessThan(root.get(input.getField()),
                                    (String) castToRequiredType(fieldType, input.getValue()));
                        }

                        return lessThan;
                    };
                case LIKE:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(root.get(input.getField()), "%" + input.getValue() + "%");
                case IN:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.in(root.get(input.getField()))
                                    .value(castToRequiredType(root.get(input.getField()).getJavaType(), input.getValues()));
                default:
                    LogHolder.log.error("BaseStoreSpecification.createSpecificationWithFilter - Operation not supported yet");
                    throw new RuntimeException("Operation not supported yet");
            }
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        static Object castToRequiredType(final Class<?> fieldType, String value) {
            if (fieldType.isAssignableFrom(Double.class)) {
                return Double.valueOf(value);
            } else if (fieldType.isAssignableFrom(Integer.class)) {
                return Integer.valueOf(value);
            } else if (fieldType.isAssignableFrom(Long.class)) {
                return Long.valueOf(value);
            } else if (Enum.class.isAssignableFrom(fieldType)) {
                //            Class<? extends Enum> enumType  = (Class<? extends Enum>) fieldType;
                return Enum.valueOf((Class) fieldType, value);
            }
            LogHolder.log.debug("castToRequiredType return String when fieldType: " + fieldType + " ,value = " + value);
            return value;
        }

        static List<Object> castToRequiredType(Class<?> fieldType, List<String> value) {
            List<Object> lists = new ArrayList<>();
            for (String s : value) {
                lists.add(castToRequiredType(fieldType, s));
            }
            return lists;
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
     * <br>
     * 4.getStoresThatAreOpening(openingTime, closingTime) : done
     * 5.getStoresThatWasCreatedFromDateToDate(createdDate) : done
     * 6.getStoresThatHasStartedOperationFromDate(operationStartDate)
     * 7.getStoresThatHasEndedOperationBeforeDate(operationEndDate)
     * 9.getStoresWithGivenOwnerId(ownerId) : done
     * 1.getStoreWithGivenAddressId(AddressId) : should be in address repo, store can have no address -> need to change
     * 2.getStoresWithGivenAreaId(areaId)
     */

    /**
     * @param storeType : storeType of stores
     * @param storeId   : primary key of stores
     * @return : BaseStore with given dtype and store_id
     */
    @Query(value = "select * from stores s where s.store_type = :storeType and s.store_id = :storeId"
            , nativeQuery = true)
    BaseStore getStoreWithStoreTypeAndStoreId(@Param("storeType") int storeType,
                                              @Param("storeId") long storeId);

    @Query(value = "select * from stores s where s.store_id = :storeId"
            , nativeQuery = true)
    Optional<BaseStore> getBaseStoreWithStoreId(@Param("storeId") long storeId);

    @Query(value = "select * from stores s where s.store_name like %:name%",
            nativeQuery = true)
    List<BaseStore> getBaseStoreLikeName(@Param("name") String storeName);
    @Query(value = "select * from stores s LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<BaseStore> getAllBaseStore(@Param("limit") int limit, @Param("offset") int offset);




}
