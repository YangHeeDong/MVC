package com.ll.boundedContext.article;

import lombok.*;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

// Object Mapper에서 아래 Data 없이 했을 때 매핑이 안됨
// Data의 역할은?

@Data // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode 모두 합친 얘
//@AllArgsConstructor // 선언된 모든 변수를 파라미터로 가지는 생성자 / 너 혼자만 선언되면 ObjectMapper가 안돌아가
@Getter
@Setter
// @NoArgsConstructor 파리미터가 없는 디폴트 생성자
// @RequiredArgsConstructor final, notnull로 선언된 변수를 생성자
public class Article {
    private long id;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    //private String writer;
    private String title;
    private String body;

    private boolean isBlind;

}
