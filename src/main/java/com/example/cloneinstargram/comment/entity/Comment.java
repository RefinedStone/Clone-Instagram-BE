package com.example.cloneinstargram.comment.entity;

import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.comment.dto.request.CommentRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String comment;

    @JoinColumn(name = "account_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Account account;

    @JoinColumn(name = "feed_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Feeds feeds;

    public Comment(CommentRequestDto commentRequestDto, Feeds feeds, Account account) {
        this.comment = commentRequestDto.getComment ();
        this.feeds = feeds;
        this.account = account;
    }
}
