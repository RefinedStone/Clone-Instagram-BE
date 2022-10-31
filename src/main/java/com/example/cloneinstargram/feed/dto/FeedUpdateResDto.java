package com.example.cloneinstargram.feed.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedUpdateResDto {
    private String msg;
    private int statusCode;
    private String content;

    public FeedUpdateResDto (String msg, int statusCode, String content) {
        this.msg = msg;
        this.statusCode= statusCode;
        this.content = content;
    }
}
