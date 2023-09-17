package com.langdaihoc.langdhcentre.storeManagement.entity.common;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * CreationTime; LastModificationTime; isDeleted; isValid
 */
@MappedSuperclass
public abstract class AbstractEntity {
    @CreationTimestamp
    @Column(name = "creation_time")
    public Date CreationTime;

    @Column(name = "last_modification_time")
    @UpdateTimestamp
    public Date LastModificationTime;

    @Column(name = "is_deleted")
    public boolean isDeleted = false;

    @Column(name = "is_valid")
    public boolean isValid = true;

}
