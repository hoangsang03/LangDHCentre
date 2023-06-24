package com.langdaihoc.langdhcentre.storeManagement.repository.impl;

import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.storeManagement.repository.BaseStoreRepoCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BaseStoreRepoCustomImpl implements BaseStoreRepoCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BaseStore getStoreById(String tableName, long storeId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Class type;
        try {
            type = Class.forName(tableName);
        } catch (ClassNotFoundException e) {
            log.error("BaseStoreRepoCustomImpl - getStoreById ", e);
            throw new RuntimeException(e);
        }
        CriteriaQuery query = cb.createQuery(type);

        Root<BaseStore> storeRoot = query.from(BaseStore.class);

        Path<String> id = storeRoot.get("storeId");
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(cb.equal(id, storeId));

        query.select(storeRoot)
                .where(cb.or(predicateList.toArray(new Predicate[predicateList.size()])));

        return (BaseStore) entityManager.createQuery(query).getSingleResult();
    }
}
