package com.example.cloneinstargram.feed.dto;

import com.example.cloneinstargram.feed.entity.Awsurl;
import com.example.cloneinstargram.feed.entity.Feed;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FeedResDto {
    private String msg;
    private int statusCode;
    private String content;
    private String nickname;
    private String img;

    public FeedResDto(String msg, int statusCode, String content, String nickname, String img) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.content = content;
        this.nickname = nickname;
        this.img = img;
    }
}
