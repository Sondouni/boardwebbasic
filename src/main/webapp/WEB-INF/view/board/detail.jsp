<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/res/css/board/detail.css">
<div class="article">
    <h1>${requestScope.data.title}</h1>
    <div class="article">
        <textarea rows="10" readonly>${requestScope.data.ctnt}</textarea>
        <input type="text" value="${requestScope.data.rdt ne requestScope.data.mdt?requestScope.data.mdt:requestScope.data.rdt}" readonly>
        <input type="text" value="${requestScope.data.writerNm}" readonly>
        <input type="text" value="${requestScope.data.hit}" readonly>
    </div>
    <div>
        <a href="/board/list"><input type="button" value="back"></a>
        <c:if test="${sessionScope.loginUser.iuser==requestScope.data.writer}">
            <a href="/board/regmod?iboard=${requestScope.data.iboard}"><input type="button" value="change"></a>
            <a href="/board/delete?iboard=${requestScope.data.iboard}"><input type="button" value="delete"></a>
        </c:if>
    </div>
</div>