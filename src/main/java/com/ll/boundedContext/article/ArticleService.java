package com.ll.boundedContext.article;

import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public long write(String title, String body, boolean isBlind) {
        return articleRepository.write(title,body,isBlind);
    }

    public List<Article> getArticles() {
        return articleRepository.getArticles();
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
}
