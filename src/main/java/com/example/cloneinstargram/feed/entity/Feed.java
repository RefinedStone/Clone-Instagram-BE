package com.example.cloneinstargram.feed.entity;

import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.comment.entity.Comment;
import com.example.cloneinstargram.global.Timestamped;
import com.example.cloneinstargram.like.dto.LikeResDto;
import com.example.cloneinstargram.like.entity.Like;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Feed extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;

    @Column(nullable = false)
    private String content;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "feed")
    @Column(nullable = false)
    private List<S3image> images;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "feed")
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    public Feed(Account account, String content) {
        this.account = account;
        this.content = content;
    }
    public LikeResDto toDto() {
        int likesSize = this.likes.size();
        return new LikeResDto(this.id, this.content, likesSize);
    }
}
