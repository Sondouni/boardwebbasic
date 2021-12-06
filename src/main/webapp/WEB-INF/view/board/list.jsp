<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/res/css/board/list.css">
<div>
    <table id="boardTable">
        <tr>
            <th>no</th>
            <th>title</th>
            <th>hits</th>
            <th>writer</th>
            <th>reg-datetime</th>
        </tr>
        <c:forEach items="${requestScope.list}" var = "item">
        <tr class="record" onclick="moveToDetail(${item.iboard},${item.writer})";>
            <td><c:out value="${item.iboard}"/></td>
            <td><c:out value="${item.title}"/></td>
            <td><c:out value="${item.hit}"/></td>
            <td><c:out value="${item.writerNm}"/></td>
            <td><c:out value="${item.rdt ne item.mdt? item.mdt:item.rdt}"/></td>
        </tr>
        </c:forEach>
    </table>
</div>
<script src="/res/js/board/list.js"></script>