package com.example.cloneinstargram.feed.entity;

import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.feed.dto.FeedReqDto;
import com.example.cloneinstargram.feed.dto.FeedResDto;
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

    @ManyToOne
    @JoinColumn(name = "nickname", nullable = false)
    private Account nickname;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String img;

    public Feed(Account nickname, String content, String img) {
        this.nickname = nickname;
        this.content = content;
        this.img = img;
    }

    public void update(Account nickname, FeedReqDto feedReqDto) {
        this.nickname = nickname;
        this.content = feedReqDto.getContent();
//        this.img = s3FileName;
    }
}
