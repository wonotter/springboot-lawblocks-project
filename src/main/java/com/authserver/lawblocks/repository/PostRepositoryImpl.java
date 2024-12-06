package com.authserver.lawblocks.repository;

import com.authserver.lawblocks.entity.Post;
import com.authserver.lawblocks.repository.query.CategoryPostDto;
import com.authserver.lawblocks.repository.query.PostDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.authserver.lawblocks.entity.QPost.post;
import static com.authserver.lawblocks.entity.QUser.user;

@RequiredArgsConstructor
public class PostRepositoryImpl implements QuerydslPostRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Post> getBoard(Long postId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(post)
                        .where(post.id.eq(postId))
                        .fetchOne()
        );
    }

    @Override
    public boolean isOwner(Long postId, String nickname) {
        return queryFactory
                .selectOne()
                .from(post)
                .where(post.id.eq(postId)
                    .and(post.user.nickname.eq(nickname)))
                .fetchFirst() != null;
    }

    @Override
    public List<PostDto> selectLatestBoardList(LocalDateTime beforeWeek) {
        return queryFactory
                .select(Projections.constructor(PostDto.class,
                        post.id,
                        post.title,
                        post.contents,
                        post.user.nickname,
                        post.category.id,
                        post.category.name
                ))
                .from(post)
                .join(post.user, user)
                .where(post.updatedDate.goe(beforeWeek))
                .orderBy(post.updatedDate.desc())
                .fetch();
    }

    @Override
    public List<PostDto> selectSearchBoardList(String searchWord) {
        return queryFactory
                .select(Projections.constructor(PostDto.class,
                        post.id,
                        post.title,
                        post.contents,
                        post.user.nickname,
                        post.category.id,
                        post.category.name
                ))
                .from(post)
                .join(post.user, user)
                .where(
                        post.title.containsIgnoreCase(searchWord)
                                .or(post.contents.containsIgnoreCase(searchWord))
                                .or(post.user.nickname.containsIgnoreCase(searchWord))
                )
                .orderBy(post.updatedDate.desc())
                .fetch();
    }

    @Override
    public List<PostDto> selectUserBoardList(String nickname) {
        return queryFactory
                .select(Projections.constructor(PostDto.class,
                        post.id,
                        post.title,
                        post.contents,
                        post.user.nickname,
                        post.category.id,
                        post.category.name
                ))
                .from(post)
                .join(post.user, user)
                .where(post.user.nickname.eq(nickname))
                .orderBy(post.updatedDate.desc())
                .fetch();
    }

    @Override
    public List<CategoryPostDto> selectCategoryList(Long category_id) {
        return queryFactory
                .select(Projections.constructor(CategoryPostDto.class,
                        post.id,
                        post.title,
                        post.contents,
                        post.user.nickname,
                        post.category.id,
                        post.category.name
                ))
                .from(post)
                .join(post.category)
                .where(category_id != null ? post.category.id.eq(category_id) : post.category.id.isNull())
                .orderBy(post.updatedDate.desc())
                .fetch();
    }
}
