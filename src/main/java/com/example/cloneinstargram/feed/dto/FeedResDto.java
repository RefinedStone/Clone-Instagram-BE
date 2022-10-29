package com.example.cloneinstargram.feed.dto;

import com.example.cloneinstargram.comment.entity.Comment;
import com.example.cloneinstargram.feed.entity.Awsurl;
import com.example.cloneinstargram.feed.entity.Feed;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FeedResDto {
    private String msg;
    private int statusCode;
    private String content;
    private String nickname;
    private String img;

    private List<Comment> comments;

    public FeedResDto(String msg, int statusCode, String content, String nickname, String img, Feed feed) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.content = content;
        this.nickname = nickname;
        this.img = img;
        this.comments = feed.getComments ();
    }
}
