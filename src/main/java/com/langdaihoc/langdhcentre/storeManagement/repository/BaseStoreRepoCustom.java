package com.langdaihoc.langdhcentre.storeManagement.repository;

import com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity.BaseStore;

public interface BaseStoreRepoCustom {
    BaseStore getStoreById(String tableName, long storeId);
}
