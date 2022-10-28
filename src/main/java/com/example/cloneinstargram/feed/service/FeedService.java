package com.example.cloneinstargram.feed.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.account.repository.AccountRepository;
import com.example.cloneinstargram.feed.dto.FeedReqDto;
import com.example.cloneinstargram.feed.dto.FeedResDto;
import com.example.cloneinstargram.feed.entity.Feed;
import com.example.cloneinstargram.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedRepository feedRepository;
    private final AmazonS3Client amazonS3Client;
    private final AccountRepository accountRepository;

    public Feed updateFeed(Long id, FeedReqDto feedReqDto) throws IOException {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 피드가 존재하지 않습니다"));
        String image = feed.getImg();

//        String imgPath = amazonS3Client.getUrl(bucket, image).toString();
//        String s3FileName = imgPath;
        feed.update(feedReqDto);
        return feedRepository.save(feed);
    }

    public FeedResDto deleteFeed(Long id) {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 피드가 존재하지 않습니다"));

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저정보가 없습니다"));
        try {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, feed.getImg()));
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
        feedRepository.deleteById(id);
        return new FeedResDto("삭제가 완료되었습니다", 200, feed.getContent(), account.getNickname(), feed.getImg());
    }
}
