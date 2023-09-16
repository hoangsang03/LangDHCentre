package com.langdaihoc.langdhcentre.storeManagement.entity.mainEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    public long Id;

    @CreationTimestamp
    public Date CreationTime;
    @UpdateTimestamp
    public Date LastModificationTime;
    public boolean isDeleted = false;
    public boolean isValid = true;

}
