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
                .append("body=?",body);

        return sql.insert();
    }

    public List<Article> getArticles() {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT * FROM article")
                .append("ORDER BY id DESC");

        return sql.selectRows(Article.class);
    }

    public Article getById(long id) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT * FROM article")
                .append("WHERE id =?",id);

        return sql.selectRow(Article.class);
    }

    public Article getPreArticle(long id) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT * FROM article")
                .append("WHERE id < ?", id)
                .append("ORDER BY id DESC")
                .append("limit 1");
        return sql.selectRow(Article.class);
    }

    public Article getNextArticle(long id) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT * FROM article")
                .append("WHERE id > ?", id)
                .append("ORDER BY id ASC")
                .append("limit 1");
        return sql.selectRow(Article.class);
    }
}
