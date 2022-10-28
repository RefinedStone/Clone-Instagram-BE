package com.example.cloneinstargram.feed.controller;

import com.example.cloneinstargram.feed.dto.FeedReqDto;
import com.example.cloneinstargram.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
