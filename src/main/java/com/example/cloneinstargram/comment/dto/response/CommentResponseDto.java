package com.example.cloneinstargram.comment.dto.response;

import com.example.cloneinstargram.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class CommentResponseDto {
    private Long commentId;
    private String nickname;
    private String comment;
    private LocalDateTime createdAt;


    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId ();
        this.nickname = comment.getAccount ().getNickname ();
        this.comment = comment.getComment ();
        this.createdAt = comment.getCreatedAt ();
    }
}
