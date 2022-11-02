package com.example.cloneinstargram.feed.dto;

import com.example.cloneinstargram.account.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FeedsResDto {
    private String msg;
    private int statusCode;
    private List<FeedoneResDto> feeds;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nickname;

    public FeedsResDto(List<FeedoneResDto> feeds){
        this.msg = "모든 Feeds 불러오기 성공";
        this.statusCode = HttpStatus.OK.value();
        this.feeds = feeds;
    }
    //myPost의 경우에만 씁니다
    public FeedsResDto(List<FeedoneResDto> feeds, Account account){
        this.msg = "모든 Feeds 불러오기 성공";
        this.statusCode = HttpStatus.OK.value();
        this.feeds = feeds;
        this.nickname = account.getNickname();
    }
}
