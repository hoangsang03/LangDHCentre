package com.langdaihoc.langdhcentre.repository;

import com.langdaihoc.langdhcentre.entity.mainEntity.BaseStore;

public interface BaseStoreRepoCustom {
    BaseStore getStoreById(String tableName, long storeId);
}
