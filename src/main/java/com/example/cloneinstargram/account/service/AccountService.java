package com.example.cloneinstargram.account.service;

import com.example.cloneinstargram.account.dto.AccountReqDto;
import com.example.cloneinstargram.account.dto.LoginReqDto;
import com.example.cloneinstargram.account.entity.Account;
import com.example.cloneinstargram.account.entity.RefreshToken;
import com.example.cloneinstargram.account.repository.AccountRepository;
import com.example.cloneinstargram.account.repository.RefreshTokenRepository;
import com.example.cloneinstargram.feed.dto.FeedoneResDto;
import com.example.cloneinstargram.feed.dto.FeedsResDto;
import com.example.cloneinstargram.feed.entity.Feed;
import com.example.cloneinstargram.feed.entity.S3image;
import com.example.cloneinstargram.feed.repository.FeedRepository;
import com.example.cloneinstargram.global.dto.GlobalResDto;
import com.example.cloneinstargram.global.dto.ResponseDto;
import com.example.cloneinstargram.jwt.dto.TokenDto;
import com.example.cloneinstargram.jwt.util.JwtUtil;
import com.example.cloneinstargram.like.repository.LikeRepository;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final FeedRepository feedRepository;
    private final LikeRepository likeRepository;

    @Value("${bucket.pathName}")
    private String s3Path;

    @Autowired
    public AccountService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AccountRepository accountRepository,
                          RefreshTokenRepository refreshTokenRepository, FeedRepository feedRepository,
                          LikeRepository likeRepository){
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.feedRepository = feedRepository;
        this.likeRepository = likeRepository;
    }

    @Transactional
    public GlobalResDto signup(AccountReqDto accountReqDto) {
        // email 중복 검사
        if (accountRepository.findByEmail(accountReqDto.getEmail()).isPresent()) {
            System.out.println("id overlap");
            throw new RuntimeException("Overlap Check");
        }
        accountReqDto.setEncodePwd(passwordEncoder.encode(accountReqDto.getPassword()));
        Account account = new Account(accountReqDto);
        System.out.println(account.getEmail() + " signup success");
        accountRepository.save(account);

        return new GlobalResDto("Success signup", HttpStatus.OK.value());
    }

    @Transactional
    public ResponseDto<?> login(LoginReqDto loginReqDto, HttpServletResponse response) {

        Account account = accountRepository.findByEmail(loginReqDto.getEmail()).orElseThrow(
                () -> new RuntimeException("Not found Account")
        );

        if (!passwordEncoder.matches(loginReqDto.getPassword(), account.getPassword())) {
            throw new RuntimeException("Not matches Password");
        }

        TokenDto tokenDto = jwtUtil.createAllToken(loginReqDto.getEmail());

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountEmail(loginReqDto.getEmail());

        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        } else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginReqDto.getEmail());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);
        System.out.println(account.getNickname() +"님 로그인 하셨습니다");
        return ResponseDto.success(account.getNickname());

    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

    // myPage 내 정보 가져오기
    public StringBuilder getMyInfo(UserDetailsImpl userDetails) {
        // 추가 될 내용을 위해 StringBuilder 생성
        var myInfo = new StringBuilder();
        myInfo
                /*.append()*/
                .append(userDetails.getAccount().getNickname());
        System.out.println("myinfo: " + myInfo.toString());
        return myInfo;
    }

    //logout 기능
    @Transactional
    public ResponseDto<?> logout(String email) throws Exception {
        var refreshToken = refreshTokenRepository.findByAccountEmail(email).orElseThrow(RuntimeException::new);
        refreshTokenRepository.delete(refreshToken);
        System.out.println(email + " : logout Success");
        return ResponseDto.success("Refresh Delete Success");
    }


    //내 포스트 가져오기

    public ResponseDto<?> getMyPost(Account account) {
        System.out.println(account.getNickname() + " 님의 feed를 가져옵니다");
        // var feedoneResDtoList = feedRepository.findAllByAccount(account).stream().map(FeedoneResDto::new).collect(Collectors.toList());
        List<Feed> feeds = feedRepository.findAllByAccountOrderByCreatedAtDesc(account);
        List<FeedoneResDto> feedoneResDtos = new LinkedList<>();
        for (Feed feed : feeds) {
            List<String> image = new LinkedList<>();
            FeedoneResDto feedoneResDto = new FeedoneResDto(feed, likeRepository.existsByAccountIdAndFeedId(account.getId(), feed.getId()));
            for (S3image s3image : feed.getImages())
                image.add(s3Path + s3image.getImage());
            feedoneResDto.setImg(image);
            feedoneResDtos.add(feedoneResDto);
        }
        return ResponseDto.success(new FeedsResDto(feedoneResDtos,account));
    }
}
