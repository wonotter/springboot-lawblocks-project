package com.authserver.lawblocks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record FindIdRequestDto(
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이 올바르지 않습니다.")
        @NotBlank(message = "이메일 입력은 필수입니다.")
        String email,

        @NotBlank(message = "인증 코드 입력은 필수입니다.")
        String code
) {
}
