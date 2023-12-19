<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/jsp/common/header.jsp"%>

<script>
    function deleteConfirm() {
        if (confirm("정말 탈퇴 하시겠습니까??") == true){
             location.replace("/member/delete/${loginedMemberId}");
         }else{
             return false;
         }
    }

</script>

<section>
    <div class="container mt-3">
        <form action="/member/changePassword" method="post">
            <div class="mb-3">
                <label class="form-label">Login Id</label>
                <input type="text" disabled class="form-control" name="loginId" value="${loginedMemberLoginId}">
            </div>
            <div class="mb-3">
                <label class="form-label">Old Password</label>
                <input type="password" class="form-control" name="oldPassword" placeholder="현재 비밀번호를 입력하세요">
            </div>
            <div class="mb-3">
                <label class="form-label">New Password</label>
                <input type="password" class="form-control" name="newPassword" placeholder="새 비밀번호를 입력하세요">
            </div>
            <div class="mb-3">
                <label class="form-label">New Password Confirm</label>
                <input type="password" class="form-control" name="newPasswordConfirm" placeholder="새 비밀번호 확인을 입력하세요">
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" disabled class="form-control" name="email" value="${loginedMemberEmail}">
            </div>
            <div class="d-flex justify-content-between">
                <a href="#" onclick="deleteConfirm();" class="btn btn-danger btn-sm px-2">탈퇴</a>
                <button type="submit" class="btn btn-primary btn-sm px-3">비밀번호 수정</button>
            </div>
        </form>
    </div>
</section>
<%@ include file="/jsp/common/footer.jsp"%>