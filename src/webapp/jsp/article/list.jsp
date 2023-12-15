<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/jsp/common/header.jsp"%>

<section>
    <div class="container mt-3 text-center">

        <table style="box-sizing: border-box" class="table table-hover table-bordered">
            <thead class="table-dark">
                <tr class="row">
                    <th class="col-1" scope="col">Id</th>
                    <th class="col-7">Title</th>
                    <th class="col-2">createDate</th>
                    <th class="col-2">modifiedDate</th>
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
                        <td class="col-2">
                            <fmt:parseDate value="${article.createDate}" var="createDate" pattern="yyyy-MM-dd'T'HH:mm:SS"/>
                            <fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd" />
                        </td>
                        <td class="col-2">
                            <fmt:parseDate value="${article.modifiedDate}" var="modifiedDate" pattern="yyyy-MM-dd'T'HH:mm:SS"/>
                            <fmt:formatDate value="${modifiedDate}" pattern="yyyy-MM-dd" />
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