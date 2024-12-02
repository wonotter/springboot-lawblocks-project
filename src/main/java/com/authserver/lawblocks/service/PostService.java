package com.authserver.lawblocks.service;

import com.authserver.lawblocks.common.exception.BadRequestException;
import com.authserver.lawblocks.common.exception.ErrorCode;
import com.authserver.lawblocks.dto.PostBoardRequestDto;
import com.authserver.lawblocks.entity.Post;
import com.authserver.lawblocks.entity.User;
import com.authserver.lawblocks.repository.PostRepository;
import com.authserver.lawblocks.repository.UserRepository;
import com.authserver.lawblocks.repository.query.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostDto getPost(Long postId) {
        Post post = postRepository.getBoard(postId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTED_POST));

        return new PostDto(post.getId(), post.getTitle(), post.getContents(), post.getUser().getNickname());
    }

    @Transactional
    public Long registerPost(PostBoardRequestDto dto) {
        User user = userRepository.findByNickname(dto.nickname()).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTED_USER));

        Post post = Post.builder()
                .title(dto.title())
                .contents(dto.contents())
                .user(user)
                .build();

        Post savedPost = postRepository.save(post);
        
        // 저장된 Post의 ID 반환
        return savedPost.getId();
    }

    @Transactional
    public void deletePost(Long postId, String nickname) {
        System.out.println("nickname: " + nickname);
        userRepository.findByNickname(nickname).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTED_USER));

        postRepository.findById(postId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTED_POST));

        if (!postRepository.isOwner(postId, nickname))
            throw new BadRequestException(ErrorCode.NO_PERMISSION);

        postRepository.deleteById(postId);
    }

    public List<PostDto> getLatestBoardList() {
        LocalDateTime beforeWeek = LocalDateTime.now().minusDays(7);
        return postRepository.selectLatestBoardList(beforeWeek);
    }

    public List<PostDto> getSearchBoardList(String searchWord) {
        return postRepository.selectSearchBoardList(searchWord);
    }

    public List<PostDto> getUserBoardList(String nickname) {
        userRepository.findByNickname(nickname).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTED_USER));

        return postRepository.selectUserBoardList(nickname);
    }
}
