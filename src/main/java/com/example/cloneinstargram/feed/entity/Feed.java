package com.example.cloneinstargram.feed.entity;

import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.feed.dto.FeedReqDto;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "feed")
    private List<Comments> comments;

    public Feed(Account account, String content, String img) {
        this.account = account;
        this.content = content;
        this.img = img;
    }

    public void update(String content) {
        this.content = content;
    }
}
