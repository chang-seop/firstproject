package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    @JsonProperty("article_id") //json 에 던져진 key값을 어노테이션을 통해서 지정 할 수 있다.
    private Long articleId;
    private String nickname;
    private String body;

    //static : 클래스 메소드에 선언할때 사용되는 것
    public static CommentDto createCommentDto(Comment comment) { // 생성 메소드
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
