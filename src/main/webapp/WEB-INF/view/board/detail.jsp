<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/res/css/board/detail.css?ver=2">
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
    <div>
        <br>
        <hr>
    </div>
    <c:if test="${sessionScope.loginUser!=null}">
        <form action="/board/cmt/reg" method="post">
            <strong>comment</strong><input type="hidden" name="iboard" value="${requestScope.data.iboard}" >
            <input type="text" name="ctnt">
            <input type="submit" value="write">
        </form>
    </c:if>
    <!--todo : 수정, 시간표시 <개념정리 : 데이터셋, dom>-->
    <c:forEach items="${requestScope.cmtList}" var="item">
        <div class="changeCtnt" id="commentBox">
            <nav>${item.writerNm}</nav>
            <nav
                    id="navId_${item.icmt}"
                class="changeCtnt"
                data-ctnt="${item.ctnt}"
                data-iboard="${requestScope.data.iboard}"
                data-icmt="${item.icmt}"
                data-writer="${item.writer}"
            >
                    ${item.ctnt}
            </nav>
            <nav id="rdt">${item.rdt}</nav>
            <input type="text" id="time">
            <c:if test="${item.writer==sessionScope.loginUser.iuser}">
                <input type="button" value="change" class="change" >
                <input type="button" value="delete" onclick="isDel(${item.icmt},${requestScope.data.iboard})">
            </c:if>
        </div>
    </c:forEach>
    <div style="font-size: 10px">number of comment : ${requestScope.cmtList.size()}</div>
</div>
<script src="/res/js/board/detail.js"></script>