package com.example.cloneinstargram.feed.repository;

import com.example.cloneinstargram.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedRepository extends JpaRepository <Feed, Long> {
}
