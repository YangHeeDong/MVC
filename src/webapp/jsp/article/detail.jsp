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
                        조회수 : ${article.hit}
                    </div>
                    <fmt:parseDate value="${article.modifiedDate}" var="modifiedDate" pattern="yyyy-MM-dd"/>
                    <fmt:parseDate value="${article.createDate}" var="createDate" pattern="yyyy-MM-dd"/>
                    <div>
                       <span> 작성일 :
                           <fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd" />
                       </span>
                    </div>
                    <c:if test="${createDate != modifiedDate}">
                         <div>
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

        <div class="card mt-3">
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
        <c:if test="${loginedMemberId != -1}">
            <div class="mt-2 row">
                <form action="/reply/create/${article.id}" method="post">
                    <div>
                        <textarea type="text" class="form-control" name="body" placeholder="댓글을 입력해주세요!"></textarea>
                    </div>
                    <div class="text-end mt-2">
                        <button type="submit" class="btn btn-primary btn-sm px-3">등록</button>
                    </div>
                </form>
            </div>
        </c:if>

        <c:forEach items="${replies}" var="reply">
            <div class="card my-1">
                <div class="card-body">
                    <div>
                        <p class="card-text">${reply.memberLoginId} : ${reply.body}</p>
                    </div>

                    <div class="text-end">
                        <fmt:parseDate value="${reply.modifiedDate}" var="modifiedDate" pattern="yyyy-MM-dd"/>
                        <fmt:parseDate value="${reply.createDate}" var="createDate" pattern="yyyy-MM-dd"/>
                        <div>
                           <span> 작성일 :
                               <fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd" />
                           </span>
                        </div>
                        <c:if test="${createDate != modifiedDate}">
                             <div>
                                   <span> 수정일 :
                                       <fmt:formatDate value="${modifiedDate}" pattern="yyyy-MM-dd" />
                                   </span>
                               </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="text-end mt-1">
                <c:if test="${loginedMemberId == reply.memberId}">
                    <a type="button" href="/reply/modify/${reply.id}" class="btn btn-sm btn-success">수정</a>
                    <a type="button" href="/reply/delete/${reply.id}" class="btn btn-sm btn-danger">삭제</a>
                </c:if>
            </div>
        </c:forEach>

    </div>
</section>

<%@ include file="/jsp/common/footer.jsp"%>