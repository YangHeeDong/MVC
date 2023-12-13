package com.ll.boundedContext.article;

import com.ll.App;
import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.Repository;
import com.ll.myMap.MyMap;
import com.ll.myMap.SecSql;

import java.util.List;

@Repository
public class ArticleRepository {

    @Autowired
    private MyMap myMap;

    public long write(String title, String body, boolean isBlind) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("INSERT INTO article")
                .append("SET createDate=now(),")
                .append("modifiedDate=now(),")
                .append("title=?,",title)
                .append("body=?,",body)
                .append("isBlind=?",isBlind);

        return sql.insert();
    }

    public List<Article> getArticles() {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT * FROM article")
                .append("WHERE isBlind=false");

        return sql.selectRows(Article.class);
    }
}
