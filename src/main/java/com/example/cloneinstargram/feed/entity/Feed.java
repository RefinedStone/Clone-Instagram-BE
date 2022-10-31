package com.example.cloneinstargram.feed.entity;

import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.comment.entity.Comment;
import com.example.cloneinstargram.global.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Feed extends Timestamped {
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "feed")
    private List<Comment> comments;

    public Feed(Account account, String content, String img) {
        this.account = account;
        this.content = content;
        this.img = img;
    }
}
