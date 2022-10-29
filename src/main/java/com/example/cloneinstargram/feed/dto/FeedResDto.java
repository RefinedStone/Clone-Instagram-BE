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

    public FeedResDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
