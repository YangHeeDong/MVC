package com.ll.boundedContext.reply;

import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.Repository;
import com.ll.myMap.MyMap;
import com.ll.myMap.SecSql;

import java.util.List;

@Repository
public class ReplyRepository {
    @Autowired
    MyMap myMap;

    public List<Reply> getRepliesByArticleId(long articleId) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT R.*,M.loginId AS `memberLoginId` FROM reply AS R")
                .append("LEFT JOIN `member` as M")
                .append("ON R.memberId = M.id")
                .append("WHERE R.articleId = ?", articleId)
                .append("ORDER BY R.id DESC");
        return sql.selectRows(Reply.class);
    }

    public long write(long articleId, long memberId, String body) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("INSERT INTO reply")
                .append("SET memberId = ?,",memberId)
                .append("articleId = ?,",articleId)
                .append("body = ?,",body)
                .append("createDate = now(),")
                .append("modifiedDate = now()");
        return sql.insert();
    }

    public Reply getReplyById(long replyId) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT * FROM `reply`")
                .append("WHERE id = ?",replyId);

        return sql.selectRow(Reply.class);
    }

    public void delete(long replyId) {
        SecSql sql =myMap.genSecSql();
        sql
                .append("DELETE FROM reply")
                .append("WHERE id = ?",replyId);
        sql.insert();
    }

    public void modify(long replyId, String body) {
        SecSql sql =myMap.genSecSql();
        sql
                .append("UPDATE reply")
                .append("SET body = ?",body)
                .append("WHERE id = ?",replyId);
        sql.insert();
    }
}
