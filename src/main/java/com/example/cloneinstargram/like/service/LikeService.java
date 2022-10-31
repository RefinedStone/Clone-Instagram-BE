package com.example.cloneinstargram.like.service;

import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.account.repository.AccountRepository;
import com.example.cloneinstargram.feed.entity.Feed;
import com.example.cloneinstargram.feed.repository.FeedRepository;
import com.example.cloneinstargram.global.dto.GlobalResDto;
import com.example.cloneinstargram.like.dto.LikeResDto;
import com.example.cloneinstargram.like.entity.Like;
import com.example.cloneinstargram.like.repository.LikeRepository;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final FeedRepository feedRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public GlobalResDto addLike(Long feedId, UserDetailsImpl userDetails) throws SQLException {
        Account account = accountRepository.findById(userDetails.getAccount().getId())
                .orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(()-> new NullPointerException("해당 피드가 없습니다."));

        if(likeRepository.existsByAccountIdAndFeedId(account.getId(),feed.getId())) {
            throw new RuntimeException("좋아요는 한번만 가능합니다");
        }
        Like like = new Like(account, feed);
        likeRepository.save(like);
        return new GlobalResDto("좋아요", 200);
    }

    @Transactional
    public List<LikeResDto> getFeedWithLikes(Long feedId, UserDetailsImpl userDetails) throws SQLException {
        Account account = accountRepository.findById(userDetails.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("유저 권한이 없습니다."));
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new NullPointerException("해당 글이 없습니다."));

        List<Like> likes = likeRepository.findAllByFeedId(feed.getId());
        List<LikeResDto> feeds = likes.stream()
                .map(like -> like.getFeed().toDto()).collect(Collectors.toList());

        return feeds;
    }
}
