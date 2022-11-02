package com.example.cloneinstargram.like.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeResDto {
    private String content;
    private int likesCount;
    private Boolean liked;

    public LikeResDto(String content, int likesCount, boolean liked) {

        this.content = content;
        this.likesCount = likesCount;
        this.liked = liked;
    }
}
