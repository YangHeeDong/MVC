package com.ll.boundedContext.article;

import com.ll.base.rq.Rq;
import com.ll.framwork.annotataion.Controller;
import com.ll.framwork.annotataion.GetMapping;
import com.ll.framwork.annotataion.PostMapping;

import java.time.LocalDateTime;

@Controller
public class ArticleController {

    @GetMapping("/article/create")
    public void createArticleFrom(Rq rq){
        rq.view("/article/write");
    }

    @PostMapping("/article/create")
    public void createArticle(Rq rq){

        String title = rq.getParam("title",null);
        String body = rq.getParam("body",null);
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("제목 : " + title);
        System.out.println("내용 : " + body);
        System.out.println("시간 : " + dateTime);
        
    }

}
