package com.ll.boundedContext.article;

import com.ll.base.rq.Rq;
import com.ll.boundedContext.member.Member;
import com.ll.boundedContext.member.MemberController;
import com.ll.boundedContext.member.MemberService;
import com.ll.boundedContext.reply.Reply;
import com.ll.boundedContext.reply.ReplyController;
import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.Controller;
import com.ll.framwork.annotataion.GetMapping;
import com.ll.framwork.annotataion.PostMapping;

import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MemberController memberController;

    @Autowired
    private ReplyController replyController;

    @GetMapping("/article/list/{page}/{keyword}")
    public void showList(Rq rq){

        long currentPage = Long.parseLong(rq.getParam("page","1"));

        String keyword = rq.getParam("keyword","");
        keyword = URLDecoder.decode(keyword);

        long totalArticleCounts = articleService.getArticleCounts(keyword);
        long totalPageCounts = (long)Math.ceil(totalArticleCounts/(double)10);
        long startArticleCount = ((currentPage-1) * 10);
        long endArticleCount = currentPage * 10;
        List<Article> articleList = articleService.getArticles(keyword,startArticleCount);

        rq.setAttr("articles",articleList);
        rq.setAttr("currentPage",currentPage);
        rq.setAttr("totalPageCounts",totalPageCounts);
        rq.setAttr("keyword",keyword);


        rq.view("article/list");
    }

    @GetMapping("/article/create")
    public void createArticleFrom(Rq rq){
        rq.view("article/write");
    }

    @PostMapping("/article/create")
    public void createArticle(Rq rq){

        Member loginedMember = memberController.getMemberByLoginedMemberId(rq);

        if(loginedMember == null){
            rq.replace("/member/login","로그인 후 이용해주세요");
            return;
        }

        String title = rq.getParam("title",null);
        String body = rq.getParam("body",null);

        if(title == null || title.trim().length() == 0){
            rq.historyBack("제목을 입력해 주세요.");
            return;
        }

        if(body == null || body.trim().length() == 0){
            rq.historyBack("내용을 입력해 주세요.");
            return;
        }

        long id = articleService.write(title,body,loginedMember.getId());

        // 등록후 상세 내용
        rq.replace("/article/detail/%d".formatted(id), "%d번 게시물이 생성 되었습니다.".formatted(id));
    }

    @GetMapping("/article/detail/{id}")
    public void showDetail(Rq rq){
        long id = rq.getLongParam("id",0);

        if(id == 0){
            rq.historyBack("게시글 번호가 없습니다.");
            return;
        }

        Article article = articleService.getById(id);

        if(article == null){
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        article.setHit(article.getHit() + 1);

        articleService.updateHit(article.getId(),article.getHit());

        Article preArticle = articleService.getPreArticle(article.getId());
        Article nextArticle = articleService.getNextArticle(article.getId());

        List<Reply> replies = replyController.getRepliesByArticleId(article.getId());

        rq.setAttr("prevArticle", preArticle);
        rq.setAttr("nextArticle", nextArticle);
        rq.setAttr("article",article);
        rq.setAttr("replies",replies);

        rq.view("article/detail");
    }

    @GetMapping("/article/modify/{id}")
    public void showModifyForm(Rq rq){
        Member loginedMember = memberController.getMemberByLoginedMemberId(rq);
        if(loginedMember == null){
            rq.replace("/member/login","로그인 후 이용해주세요");
            return;
        }

        long id = rq.getLongParam("id",0);

        if(id == 0){
            rq.historyBack("게시글 번호가 없습니다.");
            return;
        }

        Article article = articleService.getById(id);

        if(article == null){
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        if(article.getMemberId() != loginedMember.getId()){
            rq.historyBack("권한이 없습니다.");
            return;
        }

        rq.setAttr("article",article);

        rq.view("article/modify");
    }

    @PostMapping("/article/modify/{id}")
    public void doModifyArticle(Rq rq){
        
        Member loginedMember = memberController.getMemberByLoginedMemberId(rq);
        if(loginedMember == null){
            rq.replace("/member/login","로그인 후 이용해주세요");
            return;
        }
        
        long id = rq.getLongParam("id",0);

        if(id == 0){
            rq.historyBack("게시글 번호가 없습니다.");
            return;
        }

        Article article = articleService.getById(id);

        if(article == null){
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }
        
        if(article.getMemberId() != loginedMember.getId()){
            rq.historyBack("권한이 없습니다.");
            return;
        }
        
        String title = rq.getParam("title",null);
        String body = rq.getParam("body",null);

        if(title == null || title.trim().length() == 0){
            rq.historyBack("제목을 입력해 주세요.");
            return;
        }

        if(body == null || body.trim().length() == 0){
            rq.historyBack("내용을 입력해 주세요.");
            return;
        }

        articleService.modify(article.getId(),title,body);

        // 수정 후 상세 내용
        rq.replace("/article/detail/%d".formatted(article.getId()), "%d번 게시물이 수정 되었습니다.".formatted(article.getId()));
    }

    @GetMapping("/article/delete/{id}")
    public void doDeleteArticle(Rq rq){

        Member loginedMember = memberController.getMemberByLoginedMemberId(rq);
        if(loginedMember == null){
            rq.replace("/member/login","로그인 후 이용해주세요");
            return;
        }

        long id = rq.getLongParam("id",0);

        if(id == 0){
            rq.historyBack("게시글 번호가 없습니다.");
            return;
        }

        Article article = articleService.getById(id);

        if(article == null){
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        if(article.getMemberId() != loginedMember.getId()){
            rq.historyBack("권한이 없습니다.");
            return;
        }


        articleService.delete(article.getId());

        // 수정 후 상세 내용
        rq.replace("/article/list", "%d번 게시물이 삭제 되었습니다.".formatted(article.getId()));
    }

}
