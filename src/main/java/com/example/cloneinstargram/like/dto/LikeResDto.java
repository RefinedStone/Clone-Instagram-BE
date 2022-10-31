package com.example.cloneinstargram.like.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeResDto {
    private Long id;
    private String content;
    private int likesSize;

    public LikeResDto(Long id, String content, int likesSize) {
        this.id = id;
        this.content = content;
        this.likesSize = likesSize;
    }
}
