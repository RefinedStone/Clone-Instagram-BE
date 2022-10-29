package com.example.cloneinstargram.feed.controller;

import com.example.cloneinstargram.feed.dto.FeedReqDto;
import com.example.cloneinstargram.feed.dto.FeedoneResDto;
import com.example.cloneinstargram.feed.dto.FeedsResDto;
import com.example.cloneinstargram.feed.service.FeedService;
import com.example.cloneinstargram.global.dto.GlobalResDto;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class FeedController {
    private final FeedService feedService;

    @PatchMapping("/feed/{feedId}")
    public ResponseEntity<?> updateFeed(@PathVariable Long id, @RequestParam(value="dto")FeedReqDto feedReqDto)
            throws IOException {
        try {
            return ResponseEntity.ok(feedService.updateFeed(id, feedReqDto));
        } catch (NullPointerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(404));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(403));
        }
    }

    @DeleteMapping("/feed/{feedId}")
    public ResponseEntity<?> deleteFeed(@PathVariable Long id) {
        try {
            feedService.deleteFeed(id);
            return ResponseEntity.ok(id);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(404));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(403));
        }
    }

    @PostMapping("/feed")
    public GlobalResDto addFeed(@RequestPart(required = false, value= "image") MultipartFile image,
                                @RequestParam(required = false, value= "content") String content,
                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("이미지: " + image);
        System.out.println("content: " + content);
        return feedService.addFeed(image, content, userDetails);
    }

    @GetMapping("/feed/show")
    public FeedsResDto showFeeds(){
        return feedService.showFeeds();
    }

    @GetMapping("/feed/show/{feedId}")
    public FeedoneResDto showFeed(@PathVariable Long feedId){
        return feedService.showFeed(feedId);
    }
}
