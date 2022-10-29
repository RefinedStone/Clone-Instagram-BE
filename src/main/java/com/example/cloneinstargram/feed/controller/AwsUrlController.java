package com.example.cloneinstargram.feed.controller;

import com.example.cloneinstargram.feed.entity.Awsurl;
import com.example.cloneinstargram.feed.repository.AwsurlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AwsUrlController {
    private final AwsurlRepository awsurlRepository;
    private static final Long bucket_cnt = 1L;

    @PostMapping("/bucket")
    public ResponseEntity<Object> bucketupdate(@RequestBody Map<String, String> request){
        String isurl = request.get("isurl");
        try {
            if (isurl.charAt(isurl.length() - 1) == '/') {
                Optional<Awsurl> awsUrl = awsurlRepository.findById(bucket_cnt);
                if (awsUrl.isEmpty()) {
                    Awsurl newUrl = new Awsurl(isurl);
                    return ResponseEntity.ok(awsurlRepository.save(newUrl));
                } else {
                    awsUrl.get().setUrl(isurl);
                    return ResponseEntity.ok(awsurlRepository.save(awsUrl.get()));
                }
            } else throw new RuntimeException("주소가 잘못되었습니다.");
        } catch (RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
