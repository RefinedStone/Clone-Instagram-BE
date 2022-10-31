package com.example.cloneinstargram.like.repository;

import com.example.cloneinstargram.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByAccountIdAndFeedId(Long AccountId, Long FeedId);
    List<Like> findAllByFeedId(Long FeedId);
}
