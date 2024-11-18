package com.authserver.lawblocks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SignUpRequestDto (
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    String userId,

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[#@$!%\\?&])[A-Za-z0-9#@$!%\\?&]{8,13}$", message = "비밀번호는 8~13자 영문 대소문자, 숫자, 특수문자를 사용하세요.")
    String password,

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이 올바르지 않습니다.")
    String email,

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    String nickName,

    @NotBlank(message = "생년월일은 필수 입력 값입니다.")
    String birth,

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    String realName,

    @NotBlank(message = "이메일 인증코드는 필수 입력입니다.")
    String certificationNumber
    )
{
}
