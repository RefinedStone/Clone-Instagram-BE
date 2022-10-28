package com.example.cloneinstargram.account.controller;

import com.example.cloneinstargram.account.dto.AccountReqDto;
import com.example.cloneinstargram.account.dto.LoginReqDto;
import com.example.cloneinstargram.account.service.AccountService;
import com.example.cloneinstargram.global.dto.GlobalResDto;
import com.example.cloneinstargram.jwt.util.JwtUtil;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final JwtUtil jwtUtil;
    private final AccountService accountService;

    @PostMapping("/account/signup")
    public GlobalResDto signup(@RequestBody @Valid AccountReqDto accountReqDto) {
        return accountService.signup(accountReqDto);
    }

    @PostMapping("/account/login")
    public GlobalResDto login(@RequestBody @Valid LoginReqDto loginReqDto, HttpServletResponse response) {
        return accountService.login(loginReqDto, response);
    }

    @GetMapping("/issue/token")
    public GlobalResDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, jwtUtil.createToken(userDetails.getAccount().getEmail(), "Access"));
        return new GlobalResDto("Success IssuedToken", HttpStatus.OK.value());
    }

    // myPage 내 정보 가져오기
    @GetMapping("/account")
    public String getMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return accountService.getMyInfo(userDetails).toString();
    }

}
