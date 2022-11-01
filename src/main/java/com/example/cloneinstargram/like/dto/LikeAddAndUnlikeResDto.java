package com.example.cloneinstargram.like.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeAddAndUnlikeResDto {
    private String msg;
    private Boolean liked;

    public LikeAddAndUnlikeResDto(String msg, Boolean liked) {

        this.msg = msg;
        this.liked = liked;
    }
}
