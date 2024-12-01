package com.authserver.lawblocks.controller;

import com.authserver.lawblocks.dto.*;
import com.authserver.lawblocks.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService authService;

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequestDto requestBody) {
        authService.signUp(requestBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signIn")
    public ResponseEntity<LoginResponseDto> signIn(@RequestBody @Valid LoginRequestDto requestBody) {
        LoginResponseDto response = authService.signIn(requestBody);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/emailCertification")
    public ResponseEntity<Void> emailCertification(@RequestBody @Valid EmailCertificationRequestDto requestBody) {
        authService.emailCertification(requestBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/findId")
    public ResponseEntity<String> findId(@RequestBody @Valid FindIdRequestDto requestBody) {
        return ResponseEntity.ok().body(authService.findId(requestBody));
    }
}
