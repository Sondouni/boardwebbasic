<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="/res/css/board/rank.css?ver=4">
<div class="rankContainer">
    <div>
        <h1>${requestScope.title}</h1>
    </div>
    <div>
    <c:choose>
        <c:when test="${fn:length(requestScope.list)==0}">
            <script>
                alert("there`s no write")
            </script>
        </c:when>
        <c:otherwise>
            <table id="boardTable">
                <tr>
                    <th>no</th>
                    <th>title</th>
                    <th>writer</th>
                    <th>reg-datetime</th>
                    <c:choose>
                        <c:when test="${param.type==1}">
                            <th><i class="far fa-eye"></i></th>
                        </c:when>
                        <c:when test="${param.type==2}">
                            <th><i class="far fa-kiss-wink-heart"></i></th>
                        </c:when>
                        <c:when test="${param.type==3}">
                            <th><i class="far fa-comments"></i></th>
                        </c:when>
                    </c:choose>
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
                        <td>${eachWriter}</td>
                        <td><c:out value="${item.rdt ne item.mdt? item.mdt:item.rdt}"/></td>
                        <c:choose>
                            <c:when test="${param.type==1}">
                                <td>${item.hit}</td>
                            </c:when>
                            <c:when test="${param.type==2}">
                                <td>${item.heartNum}</td>
                            </c:when>
                            <c:when test="${param.type==3}">
                                <td>${item.commentsNum}</td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    </div>
</div>
<script src="/res/js/board/list.js">
</script>