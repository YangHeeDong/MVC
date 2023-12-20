<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/jsp/common/header.jsp"%>

<script>
    function searchFunction(obj) {
        var category =  $('#category').val();
        var keyword = $('#keyword').val();
        location.href = "/article/list/"+category+"/"+keyword;
    }

</script>

<section>
    <div class="container mt-3 text-center">
        <div class="my-2 d-flex  justify-content-end">
            <div class="col-2 mx-1">
                <select class="form-select" id="category" aria-label="Default select example">
                  <option value="total" selected>전체</option>
                  <option value="title">제목</option>
                  <option value="body">내용</option>
                  <option value="writer">글쓴이</option>
                </select>
            </div>
            <div class="col-2 mx-1">
                <input class="form-control" id="keyword" type="text"/>
            </div>
            <div class="col-1">
                <a type="button" href="javascript:void(0);" onclick="searchFunction();" class="btn btn-primary w-100">검색</a>
            </div>
        </div>

        <table style="box-sizing: border-box" class="table table-hover table-bordered">
            <thead class="table-dark">
                <tr class="row">
                    <th class="col-1" scope="col">Id</th>
                    <th class="col-7">Title</th>
                    <th class="col-1">Writer</th>
                    <th class="col-1">Hit</th>
                    <th class="col-2">createDate</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${articles}" var="article">
                    <tr class="row">
                        <th class="col-1" scope="row">
                            <span>${article.id}</span>
                        </th>
                        <td class="col-7 text-start">
                            <a href="/article/detail/${article.id}" class="flex p-2 group">${article.title}<a/>
                        </td>
                        <td class="col-1">
                            <span>${article.memberLoginId}</span>
                        </td>
                        <td class="col-1">
                            <span>${article.hit}</span>
                        </td>
                        <td class="col-2">
                            <fmt:parseDate value="${article.createDate}" var="createDate" pattern="yyyy-MM-dd'T'HH:mm:SS"/>
                            <fmt:formatDate value="${createDate}" pattern="yy/MM/dd" />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-end">
            <a type="button" href="/article/create" class="btn btn-sm btn-primary">글 등록</a>
        </div>
    </div>
</section>

<%@ include file="/jsp/common/footer.jsp"%>