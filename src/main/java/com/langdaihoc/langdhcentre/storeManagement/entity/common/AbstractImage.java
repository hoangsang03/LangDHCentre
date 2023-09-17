package com.langdaihoc.langdhcentre.storeManagement.entity.common;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
public class AbstractImage {
    @CreationTimestamp
    public Date CreationTime;
    @UpdateTimestamp
    public Date LastModificationTime;
    public boolean isDeleted = false;
    public boolean isValid = true;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private String fileSize;

}
