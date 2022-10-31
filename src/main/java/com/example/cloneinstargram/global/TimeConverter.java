package com.example.cloneinstargram.global;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeConverter {

    public static String convertTime(LocalDateTime createdAt) {
        long interval = ChronoUnit.MINUTES.between (createdAt, LocalDateTime.now ());

        String intervalTime = "";

        if(interval == 0) {
            intervalTime = "방금 전";
        } else if (interval < 60) {
            intervalTime = interval + "분 전";
        } else if (interval < 60 * 24) {
            intervalTime = (interval / 60) + "시간 전";
        } else if (interval <= 60 * 24 * 7) {
            intervalTime = (interval / 60 / 24) + "일 전";
        } else {
            intervalTime = (interval / 60 / 24 / 7) + "주 전";
        }

        return intervalTime;
    }

    public static String customForm(LocalDateTime date) {
        return date.format ( DateTimeFormatter.ofPattern ( "MM월 dd일" ) );
    }
}
