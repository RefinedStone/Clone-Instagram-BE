package com.example.cloneinstargram.account.repository;


import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
