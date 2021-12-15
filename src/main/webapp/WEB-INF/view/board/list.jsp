<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="/res/css/board/list.css?ver=4">
<div class="list">
    <div>
        <form action="/board/list" method="get" id="searchFrm">
            <div>
                <select name="searchType">
                    <option value="1" ${param.searchType==1?'selected':''}>title</option>
                    <option value="2" ${param.searchType==2?'selected':''}>context</option>
                    <option value="3" ${param.searchType==3?'selected':''}>title+context</option>
                    <option value="4" ${param.searchType==4?'selected':''}>writer</option>
                    <option value="5" ${param.searchType==5?'selected':''}>ALL</option>
                </select>
                <input type="search" name="searchText" value="<c:out value="${param.searchText}"/>"><input type="submit" value="search">
                <select name="rowCnt" id="rowCnt1">
                    <c:forEach var="num" begin="5" end="30" step="5">
                        <option value="${num}" ${param.rowCnt==num?'selected':''}>${num}개</option>
                    </c:forEach>
                </select>
            </div>
        </form>
    </div>
    <c:choose>
        <c:when test="${requestScope.maxPagenum==0}">
            <script>
                alert("there`s no write")
            </script>
        </c:when>
        <c:otherwise>
            <table id="boardTable">
                <tr>
                    <th>no</th>
                    <th>title</th>
                    <th>hits</th>
                    <th>writer</th>
                    <th>reg-datetime</th>
                </tr>
                <c:forEach items="${requestScope.list}" var = "item">

                    <c:set var="eachTitle" value="${fn:replace(fn:replace(item.title,'>','&gt;'),'<','&lt;')}" />
                        <c:if test="${param.searchType==1||param.searchType==3||param.searchType==5||param.searchType==4}">
                        <c:set var="eachTitle" value="${fn:replace(eachTitle,param.searchText,'<mark>'+=param.searchText+='</mark>')}"/>
                        </c:if>
                    <c:set var="eachWriter" value="${fn:replace(fn:replace(item.writerNm,'>','&gt;'),'<','&lt;')}" />
                        <c:if test="${param.searchType==5||param.searchType==4}">
                    <c:set var="eachWriter" value="${fn:replace(eachWriter,param.searchText,'<mark>'+=param.searchText+='</mark>')}"/>
                        </c:if>
                    <tr class="record" onclick="moveToDetail(${item.iboard},${item.writer})";>
                        <td><c:out value="${item.iboard}"/></td>
                        <td>${eachTitle}</td>
                        <td><c:out value="${item.hit}"/></td>
                        <c:set var="pImg" value="defualtProfile.png"/>
                        <c:if test="${item.profileImg !=null}">
                            <c:set var="pImg" value="profile/${sessionScope.loginUser.iuser}/${item.profileImg}"/>
                        </c:if>
                        <td>${eachWriter}&nbsp;&nbsp;<img class="profileImg" src = "/res/img/${pImg}">
                            <img class="profileImg" src = "/res/img/${item.profileImg!=null?"profile/"+=sessionScope.loginUser.iuser+="/"+=item.profileImg:"defualtProfile.png"}">
                        </td>
                        <td><c:out value="${item.rdt ne item.mdt? item.mdt:item.rdt}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <div class="pageContainer">
                <form id="frmList">
                    <input type="hidden" name="searchType" value="${param.searchType}">
                    <input type="hidden" name="searchText" value="${param.searchText}">
                    <select name="rowCnt" id="rowCnt" >
                        <c:forEach var="num" begin="5" end="30" step="5">
                            <option value="${num}" ${param.rowCnt==num?'selected':''}>${num}</option>
                        </c:forEach>
                    </select>
                </form>
                <c:set var = "selectedPage" value="${param.page==null?1:param.page}"/>
                    <c:forEach var = "maxPage" begin="1" end="${requestScope.maxPagenum}">
                        <div><a href="/board/list?page=${maxPage}&searchType=${param.searchType}&searchText=${param.searchText}&rowCnt=${param.rowCnt}"><span class="${selectedPage == maxPage?'selected':''}"><c:out value="${maxPage}"/></span></a></div>
                    </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<script src="/res/js/board/list.js?ver=3">
    let rowCtnVal = document.querySelector('#rowCtn1');
    console.log(rowCtnVal)
    function rowCtnVal1(){
        location.href = "/board/list?page=1&searchType=${param.searchType}&searchText=${param.searchText}&rowCnt="+rowCtnVal.value;
    }
</script>