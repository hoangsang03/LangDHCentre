package com.langdaihoc.langdhcentre.repository.specification;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore_;
import com.langdaihoc.langdhcentre.repository.BaseStoreRepo;
import com.langdaihoc.langdhcentre.repository.common.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@RequiredArgsConstructor
@Component
@Slf4j
public class BaseStoreCustom {
    private final BaseStoreRepo baseStoreRepo;


        public List<BaseStore> getQueryResult(List<Filter> filters){
            if(filters.size()>0) {
                return baseStoreRepo.findAll(getSpecificationFromFilters(filters));
            }else {
                log.info("BaseStoreCustom - getQueryResult - filters.size = 0");
                return Collections.emptyList();
            }
        }

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

}
