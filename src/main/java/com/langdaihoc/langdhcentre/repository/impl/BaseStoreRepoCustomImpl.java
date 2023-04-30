package com.langdaihoc.langdhcentre.repository.impl;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;
import com.langdaihoc.langdhcentre.repository.BaseStoreRepoCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class BaseStoreRepoCustomImpl implements BaseStoreRepoCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public BaseStore getStoreById(String tableName, long storeId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BaseStore> query = cb.createQuery(BaseStore.class);
        Root<BaseStore> store = query.from(BaseStore.class);

        Path<String> id = store.get("storeId");
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(cb.equal(id,storeId));

        query.select(store)
                .where(cb.or(predicateList.toArray(new Predicate[predicateList.size()])));

        return entityManager.createQuery(query).getSingleResult();
    }
}
