package com.langdaihoc.langdhcentre.operatorManagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.langdaihoc.langdhcentre.util.DateTimeUtil;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author : hoangsang03
 */
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity(name = "operator_account")
@Table(name = "operators_account")
@Slf4j
public class Operator implements UserDetails {

    {
        this.isValid = false;
        this.isDeleted = true;
        this.isActivated = false;
        this.isLocked = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operator_id")
    private Integer operatorId;

    @Column(name = "email_address", nullable = false)
    private String email;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    @JsonFormat(pattern = "YYYY-MM-DD")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "start_date", nullable = false)
    @JsonFormat(pattern = "YYYY-MM-DD")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "expire_date", nullable = false)
    @JsonFormat(pattern = "YYYY-MM-DD")
    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "last_updated_date")
    @UpdateTimestamp
    private Date lastUpdatedDate;



    @Column(name = "social_media_link")
    private String socialMedia;

    @Column(name = "address")
    private String address;

    @Column(name = "isValid")
    private boolean isValid;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @Column(name = "isActivated")
    private boolean isActivated;

    @Column(name = "isLocked")
    private boolean isLocked;
//private String profilePicture;

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // need to refactor it because our operator will have more than one role
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return email;
    }



    /**
     * @return :
     */
    @Override
    public boolean isAccountNonExpired() {
        boolean result = false;
        if (!this.isValid || !this.isDeleted || this.expireDate.before(DateTimeUtil.now())) {
            log.debug("isAccountNonExpired : true");
            result = true;
        } else {
            log.debug("isAccountNonExpired : false");
        }
        return result;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked;
    }

    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        // need to double-check
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.isActivated;
    }


}
