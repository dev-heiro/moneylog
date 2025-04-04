package org.codenova.moneylog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class LocalDateTests {

    @Test
    public void test01() {
        LocalDate today = LocalDate.now();

        DayOfWeek dow = today.getDayOfWeek();   // 요일
        System.out.println(dow);
        int value = today.getDayOfWeek().getValue();    // 요일마다 값이 부여되있음 ( 금 : 5
        System.out.println(value);
        System.out.println(today.plusDays(1).getDayOfWeek().getValue());

        System.out.println(today.plusDays(2).getDayOfWeek().getValue());
        System.out.println(today.minusDays(4).getDayOfWeek().getValue());

        System.out.println(today.minusDays(5).getDayOfWeek().getValue());   // 7?

        /*
            월 ~ 일  (1,2,3,4,5,6,7)
            한주의 시작을 일요일로 볼껀지 월요일로 볼껀지 기준 정하기 나름.
         */


        // 우리에게 특정 LocalDate 가 있다고 가정 그 날짜 포함된 주의 시작일과 끝일 구하려면..?
        // getDayOfWeek().getValue()  ... if == 3  ===> +3 (토)  +4(일)

        // getDayOfWeek().getValue()  ... if == 3  ===> -3 (일)  -2(월) //

        // 기준을 확실하게 한주시작을 월~ 일 이라고 갈꺼임
        // dayOfweek ==> 1 --> -0, +6
        // dayOfweek ==> 2 --> -1, +5
        // dayOfweek ==> 3 --> -2, +4
        // dayOfweek ==> 4 --> -3, +3
        // dayOfweek ==> 5 --> -4, +2
        // dayOfWeek ==> 6 --> -5  +1
        // dayOfWeek ==> 7 --> -6  +0
        LocalDate d = LocalDate.of(2025, 1, 24);
        LocalDate firstDayOfWeek = d.minusDays(d.getDayOfWeek().getValue() - 1);
        LocalDate lastDayOfWeek = d.plusDays(7 - d.getDayOfWeek().getValue());
        System.out.println(firstDayOfWeek);
        System.out.println(lastDayOfWeek);
        // 한주의 시작을 일 ~ 토 요일로보고 싶다면..?


    }
}
