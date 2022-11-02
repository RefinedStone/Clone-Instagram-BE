package com.example.cloneinstargram.like.repository;

import com.example.cloneinstargram.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByAccountIdAndFeedId(Long AccountId, Long FeedId);

    void deleteByAccountIdAndFeedId(Long AccountId, Long FeedId);
}
