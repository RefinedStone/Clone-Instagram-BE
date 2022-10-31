package com.example.cloneinstargram.feed.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedUpdateResDto {
    private String msg;
    private int status;
    private String content;

    public FeedUpdateResDto (String msg, int status, String content) {
        this.msg = msg;
        this.status = status;
        this.content = content;
    }
}
