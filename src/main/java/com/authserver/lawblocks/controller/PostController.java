package com.authserver.lawblocks.controller;

import com.authserver.lawblocks.dto.PostBoardRequestDto;
import com.authserver.lawblocks.repository.query.PostDto;
import com.authserver.lawblocks.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService boardService;

    @GetMapping("/details")
    public ResponseEntity<PostDto> getPost(@RequestParam Long post_id) {
        PostDto postDto = boardService.getPost(post_id);
        return ResponseEntity.status(HttpStatus.OK).body(postDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Long> registerPost(@RequestBody @Valid PostBoardRequestDto dto) {
        Long postId = boardService.registerPost(dto);
        return ResponseEntity.ok(postId);
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long post_id, @Param("nickname") String nickname) {
        boardService.deletePost(post_id, nickname);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/latest_list")
    public ResponseEntity<List<PostDto>> getLatestBoardList() {
        List<PostDto> latestBoardList = boardService.getLatestBoardList();
        return ResponseEntity.status(HttpStatus.OK).body(latestBoardList);
    }

    @GetMapping("/Search_list/{search_word}")
    public ResponseEntity<List<PostDto>> getSearchBoardList(@PathVariable String search_word) {
        List<PostDto> searchBoardList = boardService.getSearchBoardList(search_word);
        return ResponseEntity.status(HttpStatus.OK).body(searchBoardList);
    }

    @GetMapping("/user-board-list/{nickname}")
    public ResponseEntity<List<PostDto>> getUserBoardList(@PathVariable("nickname") String nickname) {
        List<PostDto> userBoardList = boardService.getUserBoardList(nickname);
        return ResponseEntity.status(HttpStatus.OK).body(userBoardList);
    }
}
