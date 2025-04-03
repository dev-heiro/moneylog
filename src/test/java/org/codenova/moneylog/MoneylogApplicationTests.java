package org.codenova.moneylog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class MoneylogApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("\uc724\ud615\ud638");
       // System.out.println("https:\/\/phinf.pstatic.net\/contact\/20240930_259\/1727678794125N1clC_PNG\/image.png");

    }

    @Test
    void compareLocalDateTime() {
        LocalDateTime t1  = LocalDateTime.of(2025, 1, 7, 1, 30);
        LocalDateTime t2  = LocalDateTime.of(2025, 1, 20, 13, 00);
        LocalDateTime t3  = LocalDateTime.of(2025, 2, 20, 00, 00);

        /*
        LocalDateTime 크기비교(과거,미래 비교) 할때 isAfter, isBefore  ==> boolean
          t1.isAfter(t2)      ==> t1 이 t2 이후냐?
          t2.isBefore(t3)    ===> t2 가 t3 전이냐?
         */
        System.out.println(t1.isBefore(t2));    // true
        System.out.println(t3.isAfter(t2)); // true
        System.out.println(t1.isAfter(t3)); // false
    }

    @Test
    void uuidTest() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(uuid.replaceAll("-", ""));

    }



}
