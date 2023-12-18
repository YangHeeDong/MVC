package com.ll.boundedContext.member;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private long id;
    private String loginId;
    private String password;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}
