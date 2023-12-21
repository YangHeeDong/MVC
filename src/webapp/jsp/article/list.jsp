<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/jsp/common/header.jsp"%>

<script>
    function searchFunction(obj) {
        var keyword = encodeURI($('#keyword').val(),"UTF-8");
        location.href = "/article/list/1/"+keyword;
    }

    $(document).ready(function(){

        var totalPageNum = ${totalPageCounts};
        var currentPageNum = ${currentPage};

        var code= "";

        if(totalPageNum <= 5){
            for( var i = 1 ; i <= totalPageNum ; i++){
                        if(i == currentPageNum){
                            code = code + '<li class="page-item"><a class="page-link active" pageNum='+i+' href="javascript:void(0)">'+i+'</a></li>'
                        }else{
                            code = code + '<li class="page-item"><a class="page-link" pageNum='+i+' href="javascript:void(0)">'+i+'</a></li>'
                        }
                    }
        }else if(currentPageNum <= 2){
            for( var i = 1 ; i <= 5 ; i++){
                if(i == currentPageNum){
                    code = code + '<li class="page-item"><a class="page-link active" pageNum='+i+' href="javascript:void(0)">'+i+'</a></li>'
                }else{
                    code = code + '<li class="page-item"><a class="page-link" pageNum='+i+' href="javascript:void(0)">'+i+'</a></li>'
                }
            }

            code = code + '<li class="page-item"><a class="page-link" pageNum='+(currentPageNum+1)+' href="#">Next</a></li>'

        }else if(totalPageNum - currentPageNum <= 1){

            code = code + '<li class="page-item"><a class="page-link" pageNum='+(currentPageNum-1)+' href="#">Previous</a></li>'

            for( var i = totalPageNum-5 ; i <= totalPageNum ; i++){

                if(i == currentPageNum){
                    code = code + '<li class="page-item"><a class="page-link active" pageNum='+i+' href="javascript:void(0)">'+i+'</a></li>'
                }else{
                    code = code + '<li class="page-item"><a class="page-link" pageNum='+i+' href="javascript:void(0)">'+i+'</a></li>'
                }
            }
        }else{
            code = code + '<li class="page-item"><a class="page-link" pageNum='+(currentPageNum-1)+' href="#">Previous</a></li>'

            for( var i = currentPageNum-2 ; i <= currentPageNum+2 ; i++){
                if(i == currentPageNum){
                    code = code + '<li class="page-item"><a class="page-link active" pageNum='+i+' href="javascript:void(0)">'+i+'</a></li>'
                }else{
                    code = code + '<li class="page-item"><a class="page-link" pageNum='+i+' href="javascript:void(0)">'+i+'</a></li>'
                }
            }

            code = code + '<li class="page-item"><a class="page-link" pageNum='+(currentPageNum+1)+' href="#">Next</a></li>'
        }

        $('.pagination').append(code);

        $('.page-link').on("click",function(){
              var keyword = $('#keyword').val();
              var page = $(this).attr('pageNum');

              location.href = "/article/list/"+page+"/"+keyword;

        })
    });



</script>

<section>
    <div class="container mt-3 text-center">
        <div class="my-2 d-flex  justify-content-end">
            <div class="col-2 mx-1">
                <input class="form-control" id="keyword" type="text" value="${keyword}"/>
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
        <div class="d-flex justify-content-center">
            <nav aria-label="Page navigation example">
              <ul class="pagination">

              </ul>
            </nav>
        </div>

    </div>
</section>

<%@ include file="/jsp/common/footer.jsp"%>