package com.authserver.lawblocks.repository.query;

public record CategoryPostDto(
        Long post_id,
        String title,
        String contents,
        String nickname,
        Long category_id,
        String categoryName
) {
}
