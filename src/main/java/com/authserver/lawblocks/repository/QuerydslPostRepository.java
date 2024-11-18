package com.authserver.lawblocks.repository;

import com.authserver.lawblocks.entity.Post;
import com.authserver.lawblocks.repository.query.PostDto;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QuerydslPostRepository {
    Optional<Post> getBoard(@Param("postId") Long postId);
    boolean isOwner (@Param("postId") Long postId, @Param("nickname") String nickname);
    List<PostDto> selectLatestBoardList(@Param("beforeWeek") LocalDateTime beforeWeek);
    List<PostDto> selectSearchBoardList(@Param("searchWord") String searchWord);
    List<PostDto> selectUserBoardList(@Param("nickname") String nickname);
}
