package com.example.cloneinstargram.feed.repository;

import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FeedRepository extends JpaRepository <Feed, Long> {
    Optional<Feed> findByIdAndAccount(Long aLong, Account account);
}
