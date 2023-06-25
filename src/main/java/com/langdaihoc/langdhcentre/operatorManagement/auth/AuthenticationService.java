package com.langdaihoc.langdhcentre.operatorManagement.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.langdaihoc.langdhcentre.operatorManagement.config.JwtService;
import com.langdaihoc.langdhcentre.operatorManagement.model.Operator;
import com.langdaihoc.langdhcentre.operatorManagement.model.Role;
import com.langdaihoc.langdhcentre.operatorManagement.repository.IOperatorRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IOperatorRepository operatorRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var operator = Operator.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .startDate(request.getStartDate())
                .expireDate(request.getExpireDate())
                .build();
        operatorRepository.save(operator);
        var jwtToken = jwtService.generateToken(operator);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var operator = operatorRepository.findByEmail(request.getEmail()).orElseThrow(

        );
        var jwtToken = jwtService.generateToken(operator);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
