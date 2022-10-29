package com.example.cloneinstargram.feed.controller;

import com.example.cloneinstargram.feed.dto.FeedoneResDto;
import com.example.cloneinstargram.feed.service.FeedService;
import com.example.cloneinstargram.global.dto.GlobalResDto;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class FeedController {
    private final FeedService feedService;

    @PatchMapping("/feed/{feedId}")
    public GlobalResDto updateFeed(@RequestParam(required = false, value = "content") String content,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        System.out.println("content: " + content);
        return feedService.updateFeed(content, userDetails);
    }

    @DeleteMapping("/feed/{feedId}")
    public GlobalResDto deleteFeed(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestPart MultipartFile image) {
        System.out.println("==========컨트롤러 지나는중==========");
        return feedService.deleteFeed(image, userDetails);
    }

    @PostMapping("/feed")
    public GlobalResDto addFeed(@RequestPart(required = false, value = "image") MultipartFile image,
                                @RequestParam(required = false, value = "content") String content,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("이미지: " + image);
        System.out.println("content: " + content);
        return feedService.addFeed(image, content, userDetails);
    }

    @GetMapping("/feed/show")
    public List<FeedoneResDto> showFeeds() {
        System.out.println("==========컨트롤러 지나는중==========");
        return feedService.showFeeds();
    }

    @GetMapping("/feed/show/{feedId}")
    public FeedoneResDto showFeed(@PathVariable Long feedId) {
        System.out.println("==========컨트롤러 지나는중==========");
        return feedService.showFeed(feedId);
    }
}
