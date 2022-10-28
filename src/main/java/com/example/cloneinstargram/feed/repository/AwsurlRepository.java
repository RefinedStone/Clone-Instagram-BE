package com.example.cloneinstargram.feed.repository;

import com.example.cloneinstargram.feed.entity.Awsurl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwsurlRepository extends JpaRepository<Awsurl, Long> {
}
