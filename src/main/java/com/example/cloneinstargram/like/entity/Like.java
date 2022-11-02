package com.example.cloneinstargram.like.entity;

import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.feed.entity.Feed;
import com.example.cloneinstargram.global.Timestamped;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Like extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    @JsonIgnore
    private Feed feed;

    public Like(Account account, Feed feed) {
        this.account = account;
        this.feed = feed;
    }
}
