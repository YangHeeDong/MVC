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
                    <h6 class="card-subtitle mb-2 text-muted">작성자</h6>
                </div>
                <div class="my-3">
                    <p class="card-text">${article.body}</p>
                </div>
                <div class="text-end">
                    <span href="#" class="card-link">${article.createDate}</span>
                </div>
            </div>
        </div>

        <div class="card mt-4">
          <ul class="list-group list-group-flush">
            <a href="#" class="list-group-item"><i class="fa-solid fa-sort-up"></i> <span class="ml-2">이전글</span> </a>
            <a href="#" class="list-group-item"><i class="fa-solid fa-sort-down"></i> <span class="ml-2">다음글</span> </a>
          </ul>
        </div>

    </div>
</section>

<%@ include file="/jsp/common/footer.jsp"%>