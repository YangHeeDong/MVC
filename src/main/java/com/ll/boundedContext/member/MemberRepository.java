package com.ll.boundedContext.member;

import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.Repository;
import com.ll.myMap.MyMap;
import com.ll.myMap.SecSql;

@Repository
public class MemberRepository {

    @Autowired
    private MyMap myMap;

    public void create(String loginId, String password, String email) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("INSERT INTO member")
                .append("SET loginId = ?,",loginId)
                .append("password = ?,",password)
                .append("email = ?,",email)
                .append("createDate = now(),")
                .append("modifiedDate = now()");

        sql.insert();
    }

    public Member getMemberByLoginId(String loginId) {
        SecSql sql = myMap.genSecSql();

        sql
                .append("SELECT * FROM `member`")
                .append("WHERE loginId = ?",loginId);

        return sql.selectRow(Member.class);
    }

    public Member getMemberByEmail(String email) {
        SecSql sql = myMap.genSecSql();

        sql
                .append("SELECT * FROM `member`")
                .append("WHERE email = ?",email);

        return sql.selectRow(Member.class);
    }

    public Member getMemberById(long loginedMemberId) {
        SecSql sql = myMap.genSecSql();
            sql
                    .append("SELECT * FROM `member`")
                    .append("WHERE Id = ?",loginedMemberId);
        return sql.selectRow(Member.class);
    }

    public void delete(long loginedMemberId) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("DELETE FROM `member`")
                .append("WHERE id = ?",loginedMemberId);
        sql.insert();
    }

    public void chagePassword(long loginedMemberId, String newPassword) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("UPDATE `member`")
                .append("SET `password` = ?",newPassword)
                .append("WHERE id = ?",loginedMemberId);
        sql.insert();
    }
}
