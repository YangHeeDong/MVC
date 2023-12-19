package com.ll.boundedContext.reply;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class Reply {
    private long id;
    private long articleId;
    private long memberId;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    private String body;

    private String memberLoginId;
}
