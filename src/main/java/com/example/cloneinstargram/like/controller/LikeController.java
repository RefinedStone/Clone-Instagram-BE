package com.example.cloneinstargram.like.controller;

import com.example.cloneinstargram.global.dto.GlobalResDto;
import com.example.cloneinstargram.like.dto.LikeAddAndUnlikeResDto;
import com.example.cloneinstargram.like.dto.LikeResDto;
import com.example.cloneinstargram.like.service.LikeService;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/like/{feedId}")
    public LikeAddAndUnlikeResDto addAndUnLike(@PathVariable Long feedId, @AuthenticationPrincipal UserDetailsImpl userDetails)
            throws SQLException {
        System.out.println("==========컨트롤러 지나는중==========");
        return likeService.addAndUnLike(feedId, userDetails);
    }

    @GetMapping("/like/{feedId}")
    public LikeResDto getFeedWithLikes(@PathVariable Long feedId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("==========컨트롤러 지나는중==========");
        return likeService.getFeedWithLikes(feedId, userDetails);
    }
}
