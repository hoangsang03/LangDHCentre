package com.langdaihoc.langdhcentre.storeManagement.entity.auth;

import com.langdaihoc.langdhcentre.storeManagement.entity.auth.BaseUser;
import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id", nullable = false)
    private Long authorityId;

    @Column(name = "authority_name")
    private String authorityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private BaseUser user;

}
