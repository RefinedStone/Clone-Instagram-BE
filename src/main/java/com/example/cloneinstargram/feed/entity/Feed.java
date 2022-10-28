package com.example.cloneinstargram.feed.entity;

import com.example.cloneinstargram.account.entity.Account;
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

    @ManyToOne
    @JoinColumn(name = "nickname", nullable = false)
    private Account account;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String img;

    public Feed(Account account, String content, String img) {
        this.account = account;
        this.content = content;
        this.img = img;
    }

    public Feed(FeedReqDto feedReqDto) {
        this.account = account;
        this.content = feedReqDto.getContent();
        this.img = feedReqDto.getImg();
    }

    public void update(FeedReqDto feedReqDto) {
        this.account = account;
        this.content = feedReqDto.getContent();
//        this.img = s3FileName;
    }
}
