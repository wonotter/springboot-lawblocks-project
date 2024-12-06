package com.authserver.lawblocks.dto;

import jakarta.validation.constraints.NotBlank;

public record PostBoardRequestDto(
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        String title,
        @NotBlank(message = "내용은 필수 입력 값입니다.")
        String contents,
        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
        String nickname,
        @NotBlank(message = "카테고리는 필수 입력 값입니다.")
        String categoryName
) {
}
