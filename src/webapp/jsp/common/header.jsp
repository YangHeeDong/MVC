<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring IoC Container</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<body>

<%
long loginedMemberId = (long)session.getAttribute("loginedMemberId");
String loginedMemberLoginId = (loginedMemberId != -1) ? session.getAttribute("loginedMemberLoginId").toString() : null;
%>

<!-- 네비 시작 -->
<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
    <div class="container">
        <a class="navbar-brand" href="/article/list">Spring IoC Container</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-between" id="navbarText">
            <ul class="navbar-nav me-auto mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/article/list">Article</a>
                </li>
            </ul>
            <ul class="navbar-nav mb-lg-0">
                <c:choose>
                	<c:when test="${loginedMemberId != -1}">
                		<li class="nav-item">
                            <a href="/member/myPage" class="nav-link">
                                ${loginedMemberLoginId}
                           </a>
                        </li>
                        <li class="nav-item">
                            <a href="/member/logout" class="nav-link">
                               로그아웃
                           </a>
                        </li>
                	</c:when>
                	<c:otherwise>
                		<li class="nav-item">
                            <a href="/member/create" class="nav-link">
                               회원가입
                           </a>
                        </li>
                        <li class="nav-item">
                            <a href="/member/login" class="nav-link">
                               로그인
                           </a>
                        </li>
                	</c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
<!-- 네비 끝 -->
