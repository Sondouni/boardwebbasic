<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/res/css/board/detail.css?ver=3">
<div class="article">
    <h1>${requestScope.data.title}</h1>
    <div class="article">
        <textarea rows="10" readonly>${requestScope.data.ctnt}</textarea>
        <input type="text" value="${requestScope.data.rdt ne requestScope.data.mdt?requestScope.data.mdt:requestScope.data.rdt}" readonly>
        <input type="text" value="${requestScope.data.writerNm}" readonly>
        <input type="text" value="${requestScope.data.hit}" readonly>
        <div>
            <i class="fas fa-heart" style="color: #e3bba4"></i>
            x&nbsp;${requestScope.data.heartNum}
        </div>
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
        <hr style="width: 500px">
    </div>
    <!-- todo <i class="far fa-thumbs-down"></i> 싫어요-->
    <c:if test="${sessionScope.loginUser!=null}">
        <div class="fav">
            <c:choose>
                <c:when test="${requestScope.isHeart==1}">
                    <a href="/board/heart?proc=2&iboard=${requestScope.data.iboard}"><i class="fas fa-thumbs-up"></i></a>
                </c:when>
                <c:otherwise>
                    <a href="/board/heart?proc=1&iboard=${requestScope.data.iboard}"><i class="far fa-thumbs-up"></i></a>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>

    <c:if test="${sessionScope.loginUser!=null}">
        <form action="/board/cmt/reg" method="post">
            <strong>comment</strong><input type="hidden" name="iboard" value="${requestScope.data.iboard}" >
            <input type="text" name="ctnt">
            <input type="submit" value="write">
        </form>
    </c:if>



    <!-- ajax 통신 -->

    <div id="cmtListContainer" style="display: flex; flex-direction:row" data-iboard="${requestScope.data.iboard}">
        <div id="container" style="display: flex; flex-direction:column">

        </div>
    </div>






</div>
<div class="cmtModContainer">
    <div class="cmtModBody">
        <form action="/board/cmt/reg" method="post" id="cmtModFrm">
            <input type="hidden" name="icmt">
            <input type="hidden" name="iboard" value="${requestScope.data.iboard}">
            <div>
                <input type="text" name="ctnt" value="" placeholder="content">
            </div>
            <div>
            <input type="submit" value="change">
            <input type="button" value="cancel" id="btnCancel">
            </div>
        </form>
    </div>

</div>
<script src="/res/js/board/detail2.js?ver=2"></script>