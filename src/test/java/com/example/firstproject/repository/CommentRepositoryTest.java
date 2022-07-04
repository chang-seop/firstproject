package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "park", "굳 윌 헌팅");
            Comment b = new Comment(2L, article, "kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "Choi", "쇼생크의 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }
        /* Case 2: 1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음!");
        }
        /* Case 3: 9번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 9L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "9번 글의 모든 댓글을 출력!");
        }
        /* Case 4: 9999번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 9999L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "9번 글의 모든 댓글을 출력!");
        }
        /* Case 5: -1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = -1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "9번 글의 모든 댓글을 출력!");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: "park"의 모든 댓글 조회 */
        {
            // 입력 데이터를 준비
            String nickname = "park";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            Article a_article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
            Article b_article = new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ");
            Article c_article = new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ");

            Comment a_comment = new Comment(1L, a_article, "park", "굳 윌 헌팅");
            Comment b_comment = new Comment(4L, b_article, "park", "치킨");
            Comment c_comment = new Comment(7L, c_article, "park", "조깅");

            List<Comment> expected = Arrays.asList(a_comment, b_comment, c_comment);

            // 검증
            assertEquals(expected.toString(), comments.toString());
        }
        /* Case 2: "kim"의 모든 댓글 조회 */
        {
            // 입력 데이터를 준비
            String nickname = "kim";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            Article a_article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
            Article b_article = new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ");
            Article c_article = new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ");

            Comment a_comment = new Comment(2L, a_article, "kim", "아이 엠 샘");
            Comment b_comment = new Comment(5L, b_article, "kim", "샤브샤브");
            Comment c_comment = new Comment(8L, c_article, "kim", "유튜브");

            List<Comment> expected = Arrays.asList(a_comment, b_comment, c_comment);

            // 검증
            assertEquals(expected.toString(), comments.toString());
        }
        /* Case 3: null 의 모든 댓글 조회 */
        {
            // 입력 데이터를 준비
            String nickname = null;
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString());
        }
        /* Case 4: ""의 모든 댓글 조회 */
        {
            // 입력 데이터를 준비
            String nickname = "";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString());
        }
        /* Case 5: "i"의 모든 댓글 조회 */
        {
            // 입력 데이터를 준비
            String nickname = "i";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString());
        }
    }
}