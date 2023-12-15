<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/jsp/common/header.jsp"%>

<section>
    <div class="container mt-3">
        <form action="/member/create" method="post">
            <div class="mb-3">
                <label class="form-label">Login ID</label>
                <input type="text" class="form-control" name="loginId" placeholder="아이디를 입력하세요">
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" class="form-control" name="password" placeholder="비밀번호를 입력하세요">
            </div>
            <div class="mb-3">
                <label class="form-label">Password Confirm</label>
                <input type="password" class="form-control" name="passwordConfirm" placeholder="비밀번호 확인을 입력하세요">
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" class="form-control" name="email" placeholder="이메일을 입력하세요">
            </div>
            <div class="float-end">
                <button type="submit" class="btn btn-primary btn-sm px-3">회원가입</button>
            </div>
        </form>
    </div>
</section>
<%@ include file="/jsp/common/footer.jsp"%>