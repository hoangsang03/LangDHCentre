package com.langdaihoc.langdhcentre.repository.specification;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore_;
import com.langdaihoc.langdhcentre.repository.common.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@RequiredArgsConstructor
@Component
public class BaseStoreSpecification {
    private static final Double PREMIUM_PRICE = 1000D;



//        public List<BaseStore> getQueryResult(List<Filter> filters){
//            if(filters.size()>0) {
//                return BaseStoreRepository.findAll(getSpecificationFromFilters(filters));
//            }else {
//                return BaseStoreRepository.findAll();
//            }
//        }

        private Specification<BaseStore> getSpecificationFromFilters(List<Filter> filter) {
            Specification<BaseStore> specification = where(createSpecification(filter.remove(0)));
            for (Filter input : filter) {
                specification = specification.and(createSpecification(input));
            }
            return specification;
        }

        private Specification<BaseStore> createSpecification(Filter input) {
            switch (input.getOperator()){
                case EQUALS:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.equal(root.get(input.getField()),
                            castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
                case NOT_EQ:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.notEqual(root.get(input.getField()),
                            castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
                case GREATER_THAN:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.gt(root.get(input.getField()),
                            (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
                case LESS_THAN:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.lt(root.get(input.getField()),
                            (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
                case LIKE:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(root.get(input.getField()), "%"+input.getValue()+"%");
                case IN:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.in(root.get(input.getField()))
                            .value(castToRequiredType(root.get(input.getField()).getJavaType(), input.getValues()));
                default:
                    throw new RuntimeException("Operation not supported yet");
            }
        }

        @SuppressWarnings({"unchecked","rawtypes"})
        private Object castToRequiredType(final Class<?> fieldType, String value) {
            if(fieldType.isAssignableFrom(Double.class)){
                return Double.valueOf(value);
            }else if(fieldType.isAssignableFrom(Integer.class)){
                return Integer.valueOf(value);
            }else if(fieldType.isAssignableFrom(Long.class)){
                return Long.valueOf(value);
            }else if(Enum.class.isAssignableFrom(fieldType)){
    //            Class<? extends Enum> enumType  = (Class<? extends Enum>) fieldType;
                return Enum.valueOf((Class)fieldType, value);
            }
            return null;
        }

        private List<Object> castToRequiredType(Class<?> fieldType, List<String> value) {
            List<Object> lists = new ArrayList<>();
            for (String s : value) {
                lists.add(castToRequiredType(fieldType, s));
            }
            return lists;
        }

        private Specification<BaseStore> nameLike(String name){
            return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(BaseStore_.storeName), "%"+name+"%");
        }


//        private Specification<BaseStore> pricesAreBetween(Double min, Double max){
//            return (root, query, criteriaBuilder)-> criteriaBuilder.between(root.get(BaseStore_.PRICE), min, max);
//        }

//        private Specification<BaseStore> belongsToCategory(List<Category> categories){
//            return (root, query, criteriaBuilder)-> criteriaBuilder.in(root.get(BaseStore_.CATEGORY)).value(categories);
//        }
//
//        private Specification<BaseStore> isPremium() {
//            return (root, query, criteriaBuilder) ->
//                    criteriaBuilder.and(
//                            criteriaBuilder.equal(root.get(BaseStore_.MANUFACTURING_PLACE).get(Address_.STATE),
//                                    STATE.CALIFORNIA),
//                            criteriaBuilder.greaterThanOrEqualTo(root.get(BaseStore_.PRICE), PREMIUM_PRICE));
//        }
}
