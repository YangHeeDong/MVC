package com.ll.boundedContext.article;

import com.ll.base.rq.Rq;
import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.Controller;
import com.ll.framwork.annotataion.GetMapping;
import com.ll.framwork.annotataion.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/article/list")
    public void showList(Rq rq){
        List<Article> articleList = articleService.getArticles();

        rq.setAttr("articles",articleList);
        rq.view("article/list");
    }


    @GetMapping("/article/create")
    public void createArticleFrom(Rq rq){
        rq.view("article/write");
    }

    @PostMapping("/article/create")
    public void createArticle(Rq rq){

        String title = rq.getParam("title",null);
        String body = rq.getParam("body",null);
        boolean isBlind = false;
        if(rq.getParam("isBlind","false").equals("true")){
            isBlind = true;
        }

        if(title == null || title.trim().length() == 0){
            rq.historyBack("제목을 입력해 주세요.");
            return;
        }

        if(body == null || body.trim().length() == 0){
            rq.historyBack("내용을 입력해 주세요.");
            return;
        }

        long id = articleService.write(title,body,isBlind);

        // 등록후 상세 내용
        rq.replace("/article/detail/%d".formatted(id), "%d번 게시물이 생성 되었습니다.".formatted(id));
    }

    @GetMapping("/article/detail/{id}")
    public void showDetail(Rq rq){
        long id = rq.getLongParam("id",0);

        if(id == 0){
            rq.historyBack("번호를 입력해 주세요");
            return;
        }

        Article article = articleService.getById(id);

        if(article == null){
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        Article preArticle = articleService.getPreArticle(article.getId());
        Article nextArticle = articleService.getNextArticle(article.getId());

        rq.setAttr("prevArticle", preArticle);
        rq.setAttr("nextArticle", nextArticle);
        rq.setAttr("article",article);

        rq.view("article/detail");

    }

}
