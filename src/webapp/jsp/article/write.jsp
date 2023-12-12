<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>

<%@ include file="/jsp/common/header.jsp"%>

<div class="container mt-3">
    <form action="/article/create" method="post">
        <div class="mb-3">
          <label for="articleTitle" class="form-label">제목</label>
          <input type="text" class="form-control" name="title" placeholder="제목을 입력하세요">
        </div>
        <div class="mb-3">
          <label for="articleBody" class="form-label">내용</label>
          <textarea class="form-control" name="body" rows="3"></textarea>
        </div>
        <div class="float-end">
            <button type="submit" class="btn btn-primary btn-sm px-3">제출</button>
        </div>
    </form>
</div>

 <%@ include file="/jsp/common/footer.jsp"%>