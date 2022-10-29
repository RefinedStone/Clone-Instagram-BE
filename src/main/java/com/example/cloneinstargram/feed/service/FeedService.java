package com.example.cloneinstargram.feed.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.account.repository.AccountRepository;
import com.example.cloneinstargram.feed.dto.FeedReqDto;
import com.example.cloneinstargram.feed.dto.FeedResDto;
import com.example.cloneinstargram.feed.dto.FeedoneResDto;
import com.example.cloneinstargram.feed.dto.FeedsResDto;
import com.example.cloneinstargram.feed.entity.Awsurl;
import com.example.cloneinstargram.feed.entity.Feed;
import com.example.cloneinstargram.feed.repository.AwsurlRepository;
import com.example.cloneinstargram.feed.repository.FeedRepository;
import com.example.cloneinstargram.global.dto.GlobalResDto;
import com.example.cloneinstargram.s3utils.StorageUtil;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedRepository feedRepository;
    private final AccountRepository accountRepository;
    private final AwsurlRepository awsurlRepository;
    private final StorageUtil storageUtil;

    public GlobalResDto updateFeed(String content, UserDetailsImpl userDetails) throws IOException {

        Feed feed = feedRepository.findById(userDetails.getAccount().getId())
                .orElseThrow(() -> new NullPointerException("해당 피드가 존재하지 않습니다."));

        Account account = userDetails.getAccount();

        feed.update(account, content);

        feedRepository.save(feed);
        return new GlobalResDto("Success updateFeed", HttpStatus.OK.value());
    }

    public GlobalResDto deleteFeed(MultipartFile image, UserDetailsImpl userDetails) {

        Account account = userDetails.getAccount();

        Feed feed = feedRepository.findById(account.getId())
                .orElseThrow(() -> new NullPointerException("해당 피드가 존재하지 않습니다"));

        storageUtil.deleteFile(image.getOriginalFilename());

        feedRepository.delete(feed);
        return new GlobalResDto("삭제가 완료되었습니다", 200);
    }


    @Transactional
    public GlobalResDto addFeed(MultipartFile image,
                                String content,
                                UserDetailsImpl userDetails){
        Account account = userDetails.getAccount();
        String fileKey = storageUtil.uploadFile(image);
        feedRepository.save(new Feed(account, content, fileKey));
        return new GlobalResDto("Success addFeed", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public List<FeedoneResDto> showFeeds() {
        Awsurl awsUrl = awsurlRepository.findById(1L).orElseThrow(
                () -> new RuntimeException("S3Url이 비어있어요")
        );
        List<Feed> feeds = feedRepository.findAll();
        List<FeedoneResDto> feedoneResDtos = new LinkedList<>();
        for(Feed feed: feeds)   feedoneResDtos.add(new FeedoneResDto(feed, awsUrl));
        return feedoneResDtos;
    }

    @Transactional(readOnly = true)
    public FeedoneResDto showFeed(Long feedId) {
        Awsurl awsUrl = awsurlRepository.findById(1L).orElseThrow(
                () -> new RuntimeException("S3Url이 비어있어요")
        );
        Feed feed = feedRepository.findById(feedId).orElseThrow(
                () -> new RuntimeException("찾으시는 포스터가 없습니다.")
        );
        return new FeedoneResDto(feed, awsUrl);
    }
}
