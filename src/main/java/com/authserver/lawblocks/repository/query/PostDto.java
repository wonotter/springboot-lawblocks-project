package com.authserver.lawblocks.repository.query;

public record PostDto(
    Long post_id,
    String title,
    String contents,
    String nickname) {
}
