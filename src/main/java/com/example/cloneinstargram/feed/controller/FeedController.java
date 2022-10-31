package com.example.cloneinstargram.feed.controller;

import com.example.cloneinstargram.feed.dto.FeedUpdateResDto;
import com.example.cloneinstargram.feed.dto.FeedoneResDto;
import com.example.cloneinstargram.feed.dto.FeedsResDto;
import com.example.cloneinstargram.feed.service.FeedService;
import com.example.cloneinstargram.global.dto.GlobalResDto;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class FeedController {
    private final FeedService feedService;

    @PatchMapping("/feed/{feedId}")
    public FeedUpdateResDto updateFeed(@RequestParam(required = false, value = "content") String content,
                                       @PathVariable Long feedId,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        System.out.println("==========컨트롤러 지나는중==========");
        System.out.println("content: " + content);
        return feedService.updateFeed(content, userDetails, feedId);
    }

    @DeleteMapping("/feed/{feedId}")
    public GlobalResDto deleteFeed(@PathVariable Long feedId,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        System.out.println("==========컨트롤러 지나는중==========");
        System.out.println("삭제할 feed ID: " + feedId);
        return feedService.deleteFeed(feedId, userDetails);
    }

//    @PostMapping("/feed")
//    public GlobalResDto addFeed(@RequestPart(required = false, value = "image") List<MultipartFile> image,
//                                @RequestParam(required = false, value = "content") String content,
//                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        System.out.println("==========컨트롤러 지나는중==========");
//        System.out.println("이미지의 갯수: " + image.size());
//        System.out.println("content: " + content);
//        return feedService.addFeed(image, content, userDetails);
//    }

    // 2번째 버전
    @PostMapping("/feed")
    public GlobalResDto addFeed(MultipartHttpServletRequest request,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("==========컨트롤러 지나는중==========");
        List<MultipartFile> images = request.getFiles("image");
        String content = request.getParameter("content");
        System.out.println("이미지의 갯수: " + images.size());
        System.out.println("content: " + content);
        return feedService.addFeed(images, content, userDetails);
    }

    @GetMapping("/feed/show")
    public FeedsResDto showFeeds(){
        System.out.println("==========컨트롤러 지나는중==========");
        return feedService.showFeeds();
    }

    @GetMapping("/feed/show/{feedId}")
    public FeedoneResDto showFeed(@PathVariable Long feedId) {
        System.out.println("==========컨트롤러 지나는중==========");
        System.out.println("받은 feedId: " + feedId);
        return feedService.showFeed(feedId);
    }
}
