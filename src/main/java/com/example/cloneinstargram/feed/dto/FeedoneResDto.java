package com.example.cloneinstargram.feed.dto;

import com.example.cloneinstargram.feed.entity.Awsurl;
import com.example.cloneinstargram.feed.entity.Feed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedoneResDto {
    private Long id;
    private String content;
    private String nickname;
    private String img;

    public FeedoneResDto(Feed feed, Awsurl awsurl){
        this.id = feed.getId();
        this.content = feed.getContent();
        this.nickname = feed.getAccount().getNickname();
        this.img = awsurl.getUrl() + feed.getImg();
    }
}
