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

    public long write(String title, String body,long memberId) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("INSERT INTO article")
                .append("SET createDate=now(),")
                .append("modifiedDate=now(),")
                .append("title=?,",title)
                .append("body=?,",body)
                .append("memberId=?,",memberId)
                .append("hit=0");


        return sql.insert();
    }

    // category별
    // 제목/내용, 제목, 내용, 글쓴이 를 나눠야 하는데
    public List<Article> getArticles(String category,String keyword,long startArticleCount,long endArticleCount) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT A.*, M.loginId AS memberLoginId  FROM article AS A")
                .append("LEFT JOIN `member` AS M")
                .append("ON A.memberId  = M.id")
                .append("ORDER BY A.id DESC");

        return sql.selectRows(Article.class);
    }


    public Article getById(long id) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT A.*, M.loginId AS memberLoginId FROM article AS A")
                .append("LEFT JOIN `member` AS M")
                .append("ON A.memberId  = M.id")
                .append("WHERE A.id =?",id);

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

    public void modify(long articleId, String title, String body) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("UPDATE article ")
                .append("SET title = ?,",title)
                .append("body = ?,",body)
                .append("modifiedDate = now()")
                .append("WHERE id = ?",articleId);
        sql.insert();
    }

    public void delete(long articleId) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("DELETE FROM article ")
                .append("WHERE id = ?",articleId);
        sql.insert();
    }

    public void updateHit(long id, int hit) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("UPDATE article")
                .append("SET hit = ?",hit)
                .append("where id = ?",id);

        sql.insert();
    }

    public ArticleCounts getArticleCounts(String category, String keyword) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT Count(*) as counts FROM article as a")
                .append("left join `member` as m")
                .append("on A.memberId = m.id")
                .append("where ? = ?",category,"%"+keyword+"%");

        return sql.selectRow(ArticleCounts.class);
    }

    public ArticleCounts getArticleCountsByTotal(String keyword) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT Count(*) as counts FROM article as a")
                .append("left join `member` as m")
                .append("on A.memberId = m.id")
                .append("WHERE title like ? OR ","%"+keyword+"%")
                .append("body like ? OR ","%"+keyword+"%")
                .append("loginId like ?","%"+keyword+"%");

        return sql.selectRow(ArticleCounts.class);
    }
}
