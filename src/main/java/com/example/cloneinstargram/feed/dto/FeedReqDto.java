package com.example.cloneinstargram.feed.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FeedReqDto {
    private String content;
    private String nickname;
    private String img;
}
