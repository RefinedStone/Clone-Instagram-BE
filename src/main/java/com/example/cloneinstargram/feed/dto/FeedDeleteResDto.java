package com.example.cloneinstargram.feed.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public class FeedDeleteResDto {
    private String msg;
    private int statusCode;

    public FeedDeleteResDto (String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
