package com.authserver.lawblocks.repository;

import com.authserver.lawblocks.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPostRepository {
    Optional<Post> findById(Long postId);
    void deleteById(Long postId);
}
