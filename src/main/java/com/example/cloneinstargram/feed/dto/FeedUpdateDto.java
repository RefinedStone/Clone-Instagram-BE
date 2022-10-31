package com.example.cloneinstargram.feed.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedUpdateDto {
    private String msg;
    private int statusCode;
    private String content;

    public FeedUpdateDto (String msg, int statusCode, String content) {
        this.msg = msg;
        this.statusCode= statusCode;
        this.content = content;
    }
}
