package com.example.cloneinstargram.feed.repository;

import com.example.cloneinstargram.feed.entity.Feed;
import com.example.cloneinstargram.feed.entity.S3image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface S3imageRepository extends JpaRepository<S3image, Long> {
    List<S3image> findAllByFeed(Feed feed);
}
