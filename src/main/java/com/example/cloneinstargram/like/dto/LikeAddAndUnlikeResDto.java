package com.example.cloneinstargram.like.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeAddAndUnlikeResDto {
    private String msg;
    private boolean liked;

    public LikeAddAndUnlikeResDto(String msg, boolean liked) {

        this.msg = msg;
        this.liked = liked;
    }
}
