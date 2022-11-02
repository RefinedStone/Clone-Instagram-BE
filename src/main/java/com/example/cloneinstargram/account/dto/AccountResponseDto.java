package com.example.cloneinstargram.account.dto;

import com.example.cloneinstargram.account.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountResponseDto {

    private String email;
    private String nickname;

    AccountResponseDto(Account account) {
        this.email = account.getEmail();
        this.nickname = account.getNickname();
    }


}
