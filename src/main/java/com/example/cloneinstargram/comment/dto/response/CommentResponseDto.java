package com.example.cloneinstargram.comment.dto.response;

import com.example.cloneinstargram.comment.entity.Comment;
import com.example.cloneinstargram.global.TimeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentid;
    private String nickname;
    private String comment;
    private String createdAt;


    public CommentResponseDto(Comment comment) {
        this.commentid = comment.getCommentId();
        this.nickname = comment.getAccount().getNickname();
        this.comment = comment.getComment();
        this.createdAt = TimeConverter.convertTime ( comment.getCreatedAt () );
    }
}
