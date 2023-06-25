package com.langdaihoc.langdhcentre.operatorManagement.auth;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
