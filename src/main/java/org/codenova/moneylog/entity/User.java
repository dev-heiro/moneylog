package org.codenova.moneylog.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public class User {
    private int id;
    private String email;
    private String password;
    private String nickname;
    private String picture;
    private String provider;
    private String providerId;
    private String verified;
    private LocalDateTime createdAt;
}
