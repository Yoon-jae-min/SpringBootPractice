package com.example.demo.dto;

import com.example.demo.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CommentForm {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentForm createCommentForm(Comment comment){
        return new CommentForm(comment.getId(), comment.getArticle().getId(), comment.getNickname(), comment.getBody());
    }
}
