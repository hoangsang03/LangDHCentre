package com.langdaihoc.langdhcentre.operatorManagement.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.langdaihoc.langdhcentre.operatorManagement.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String password;

    @NotNull
    private Role role;

    private String phoneNumber;

    @JsonFormat(pattern = "YYYY-MM-DD")
    @NotNull
    private Date dateOfBirth;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date startDate;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date expireDate;

    private String socialMedia;

    private String address;

//    private boolean isValid;
//
//    private boolean isDeleted;
//
//    private boolean isActivated;
//
//    private boolean isLocked;
}
