package com.langdaihoc.langdhcentre.operatorManagement.auth;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
    private String token;
}
