package com.example.cloneinstargram.feed.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FeedUpdateResDto {
    private String msg;
    private int statusCode;
    private String content;
    private String nickname;
    private String img;

    public FeedUpdateResDto(String msg, int statusCode, String content, String nickname) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.content = content;
        this.nickname = nickname;
    }
}
