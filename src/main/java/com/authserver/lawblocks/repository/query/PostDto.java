package com.authserver.lawblocks.repository.query;

public record PostDto(
    String title,
    String contents,
    String nickname) {
}
