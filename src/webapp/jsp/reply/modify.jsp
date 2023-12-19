<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/jsp/common/header.jsp"%>

<section>
    <div class="container mt-3">
        <form action="/reply/modify/${reply.id}" method="post">
            <div class="mb-3">
              <label for="replyBody" class="form-label">내용</label>
              <textarea class="form-control" name="body" rows="3" >${reply.body}</textarea>
            </div>
            <div class="float-end">
                <button type="submit" class="btn btn-primary btn-sm px-3">수정</button>
            </div>
        </form>
    </div>
</section>
 <%@ include file="/jsp/common/footer.jsp"%>