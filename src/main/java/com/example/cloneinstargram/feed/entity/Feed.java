package com.example.cloneinstargram.feed.entity;

import com.example.cloneinstargram.feed.dto.FeedReqDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String img;

    public Feed(FeedReqDto feedReqDto) {
        this.nickname = feedReqDto.getNickname();
        this.content = feedReqDto.getContent();
        this.img = feedReqDto.getImg();
    }

    public void update(FeedReqDto feedReqDto) {
        this.nickname = feedReqDto.getNickname();
        this.content = feedReqDto.getContent();
//        this.img = s3FileName;
    }
}
