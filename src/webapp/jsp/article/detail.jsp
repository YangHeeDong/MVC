<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/jsp/common/header.jsp"%>

<section>
    <div class="container mt-3">

        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${article.title}</h5>
                <div class="text-end">
                    <span>${article.memberLoginId}</span>
                </div>

                <div class="my-3">
                    <p class="card-text">${article.body}</p>
                </div>
                <div class="text-end">
                   <div>
                       <fmt:parseDate value="${article.createDate}" var="createDate" pattern="yyyy-MM-dd'T'HH:mm:SS"/>
                       <span> 작성일 :
                           <fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd" />
                       </span>
                   </div>
                    <c:if test="${article.createDate != article.modifiedDate}">
                         <div>
                               <fmt:parseDate value="${article.modifiedDate}" var="modifiedDate" pattern="yyyy-MM-dd'T'HH:mm:SS"/>
                               <span> 수정일 :
                                   <fmt:formatDate value="${modifiedDate}" pattern="yyyy-MM-dd" />
                               </span>
                           </div>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="text-end mt-2">
            <c:if test="${loginedMemberId == article.memberId}">
                <a type="button" href="/article/modify/${article.id}" class="btn btn-sm btn-success">수정</a>
                <a type="button" href="/article/delete/${article.id}" class="btn btn-sm btn-danger">삭제</a>
            </c:if>
        </div>

        <div class="card mt-4">
            <ul class="list-group list-group-flush">
                <c:if test="${not empty nextArticle}">
                    <a href="/article/detail/${nextArticle.id}" class="list-group-item">
                        <div class="row  text-center">
                            <div class="col-1">
                                <i class="fa-solid fa-chevron-up"></i>
                            </div>
                            <div class="col-10">
                                <span>${nextArticle.title}</span>
                            </div>
                        </div>
                    </a>
                </c:if>
                <c:if test="${not empty prevArticle}">
                    <a href="/article/detail/${prevArticle.id}" class="list-group-item">
                        <div class="row  text-center">
                            <div class="col-1">
                                <i class="fa-solid fa-chevron-down"></i>
                            </div>
                            <div class="col-10">
                                <span>${prevArticle.title}</span>
                            </div>
                        </div>
                    </a>
                </c:if>
          </ul>
        </div>

        <div class="mt-4">
          댓글
        </div>
        <div class="mt-2 row">
            <div>
                <textarea type="text" class="form-control" name="title" placeholder="제목을 입력하세요"></textarea>
            </div>
            <div class="text-end mt-2">
                <a type="button" href="/article/create" class="btn btn-sm btn-primary">댓글 등록</a>
            </div>
        </div>

    </div>
</section>

<%@ include file="/jsp/common/footer.jsp"%>