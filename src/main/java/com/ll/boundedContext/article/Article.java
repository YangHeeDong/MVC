package com.ll.boundedContext.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Article {
    private long id;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    //private String writer;
    private String title;
    private String body;

    private boolean isBlind;

}
