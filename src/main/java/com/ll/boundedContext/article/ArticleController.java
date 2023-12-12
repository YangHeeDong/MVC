package com.ll.boundedContext.article;

import com.ll.base.rq.Rq;
import com.ll.framwork.annotataion.Controller;
import com.ll.framwork.annotataion.GetMapping;

@Controller
public class ArticleController {

    @GetMapping("/article/create")
    public void createArticle(Rq rq){
        rq.view("/article/write");
    }

}
