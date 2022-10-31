package com.example.cloneinstargram.like.entity;

import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.feed.entity.Feed;
import com.example.cloneinstargram.like.dto.LikeResDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nickname", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "content", nullable = false)
    private Feed feed;

    public Like(Account account, Feed feed) {
        this.account = account;
        this.feed = feed;
    }
}
