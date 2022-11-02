package com.example.cloneinstargram.feed.service;

import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.comment.dto.response.CommentResponseDto;
import com.example.cloneinstargram.comment.entity.Comment;
import com.example.cloneinstargram.feed.dto.FeedUpdateResDto;
import com.example.cloneinstargram.feed.dto.FeedoneResDto;
import com.example.cloneinstargram.feed.dto.FeedsResDto;
import com.example.cloneinstargram.feed.entity.Feed;
import com.example.cloneinstargram.feed.entity.S3image;
import com.example.cloneinstargram.feed.repository.FeedRepository;
import com.example.cloneinstargram.feed.repository.S3imageRepository;
import com.example.cloneinstargram.global.dto.GlobalResDto;
import com.example.cloneinstargram.like.repository.LikeRepository;
import com.example.cloneinstargram.s3utils.StorageUtil;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@Service
public class FeedService {
    @Value("${bucket.pathName}")
    private String s3Path;
    private final FeedRepository feedRepository;
    private final S3imageRepository s3imageRepository;
    private final LikeRepository likeRepository;
    private final StorageUtil storageUtil;

    @Autowired
    public FeedService(FeedRepository feedRepository, S3imageRepository s3imageRepository,
                       StorageUtil storageUtil, LikeRepository likeRepository){
        this.feedRepository = feedRepository;
        this.s3imageRepository = s3imageRepository;
        this.storageUtil = storageUtil;
        this.likeRepository = likeRepository;
    }

    public FeedUpdateResDto updateFeed(String content, UserDetailsImpl userDetails, Long feedId) throws IOException {
        Feed feed = feedRepository.findByIdAndAccount(feedId,userDetails.getAccount())
                .orElseThrow(() -> new NullPointerException("해당 피드가 존재하지 않거나 수정 권한이 없습니다."));

        feed.setContent(content);
        System.out.println("받은 수정 content내용: "+ content);
        feedRepository.save(feed);
        return new FeedUpdateResDto("Success updateFeed", HttpStatus.OK.value(),content);
    }

    public GlobalResDto deleteFeed(Long feedId, UserDetailsImpl userDetails) {
        Feed feed = feedRepository.findByIdAndAccount(feedId,userDetails.getAccount())
                .orElseThrow(() -> new NullPointerException("해당 피드가 존재하지 않거나 삭제 권한이 없습니다."));

        for(S3image img:feed.getImages())   storageUtil.deleteFile(img.getImage());
        feedRepository.delete(feed);
        return new GlobalResDto("Success delete", 200);
    }

    @Transactional
    public GlobalResDto addFeed(List<MultipartFile> images,
                                String content,
                                UserDetailsImpl userDetails){
        System.out.println(s3Path);
        Account account = userDetails.getAccount();
        Feed feed = new Feed(account, content);
        List<S3image> s3images = new LinkedList<>();
        for(MultipartFile image: images)    s3images.add(s3imageRepository.save(new S3image(storageUtil.uploadFile(image), feed)));
        feed.setImages(s3images);
        feedRepository.save(feed);
        return new GlobalResDto("Success addFeed", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public FeedsResDto showFeeds(Account account) {
        List<Feed> feeds = feedRepository.findAllByOrderByCreatedAtDesc();
        List<FeedoneResDto> feedoneResDtos = new LinkedList<>();
        for(Feed feed: feeds)   {
            System.out.println("feed의 id: " + feed.getId());
            List<String> image = new LinkedList<>();
            FeedoneResDto feedoneResDto = new FeedoneResDto(feed, likeRepository.existsByAccountIdAndFeedId(account.getId(), feed.getId()));
            for(S3image s3image: feed.getImages())
                image.add(s3Path+s3image.getImage());
            feedoneResDto.setImg(image);
            feedoneResDtos.add(feedoneResDto);
        }
        return new FeedsResDto(feedoneResDtos);
    }

    @Transactional(readOnly = true)
    public FeedoneResDto showFeed(Long feedId, Account account) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(
                () -> new RuntimeException("찾으시는 포스터가 없습니다.")
        );

        List<Comment> comments = feed.getComments();
        List<CommentResponseDto> commentResponseDtos = new LinkedList<>();
        FeedoneResDto feedoneResDto = new FeedoneResDto(feed, likeRepository.existsByAccountIdAndFeedId(account.getId(), feed.getId()));
        List<String> image = new LinkedList<>();
        for(S3image s3image: feed.getImages())  image.add(s3Path+s3image.getImage());
        for(Comment comment: comments)  commentResponseDtos.add(new CommentResponseDto(comment));
        feedoneResDto.setImg(image);
        feedoneResDto.setComments(commentResponseDtos);
        return feedoneResDto;
    }
}
