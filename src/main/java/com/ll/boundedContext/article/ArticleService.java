package com.ll.boundedContext.article;

import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.Service;

import java.util.List;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public long write(String title, String body, long memberId) {

//        int i = 1;
//        while(i < 100){
//            articleRepository.write(title+i ,body+i,memberId);
//            i = i + 1;
//        }

        return articleRepository.write(title,body,memberId);
    }

    public List<Article> getArticles(String keyword,long startArticleCount) {
        if(startArticleCount == 1){
            startArticleCount = 0;
        }

        return articleRepository.getTotalArticles(keyword,startArticleCount);
    }

    public Article getById(long id) {
        return articleRepository.getById(id);
    }

    public Article getPreArticle(long id) {
        return articleRepository.getPreArticle(id);
    }

    public Article getNextArticle(long id) {
        return articleRepository.getNextArticle(id);
    }

    public void modify(long articleId, String title, String body) {
        articleRepository.modify(articleId,title,body);
    }

    public void delete(long id) {
        articleRepository.delete(id);
    }


    public void updateHit(long id, int hit) {
        articleRepository.updateHit(id,hit);
    }

    public long getArticleCounts( String keyword) {
        return articleRepository.getArticleCountsByTotal(keyword).getCounts();
    }
}
