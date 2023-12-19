<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/jsp/common/header.jsp"%>

<section>
    <div class="container mt-3">
        <form action="/member/login" method="post">
            <div class="mb-3">
                <label class="form-label">Login Id</label>
                <input type="text" class="form-control" name="loginId" placeholder="아이디를 입력하세요">
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" class="form-control" name="password" placeholder="비밀번호를 입력하세요">
            </div>
            <div class="float-end">
                <button type="submit" class="btn btn-primary btn-sm px-3">로그인</button>
            </div>
        </form>
    </div>
</section>
<%@ include file="/jsp/common/footer.jsp"%>