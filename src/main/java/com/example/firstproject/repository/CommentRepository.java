package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * FROM COMMENT WHERE ARTICLE_ID = :articleId", nativeQuery = true) // :articleId -> 매개변수 이름이 같아야 매칭 가능 (@param으로 매칭)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    // 특정 닉네임의 모든 댓글 조회
    // 네이티브 쿼리 XML
    List<Comment> findByNickname(@Param("nickname") String nickname);
}
