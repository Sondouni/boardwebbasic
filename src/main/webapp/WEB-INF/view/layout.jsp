<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${requestScope.title}</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/res/css/common.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Hello!</h1>
            <nav>
                <ul class="topMenu">
                        <li><a href="/board/list">board</a></li>
                        <li><a href="/board/rank?type=1">RankHits</a></li>
                        <li><a href="/board/rank?type=2">RankHearts</a></li>
                        <li><a href="/board/rank?type=3">RankComments</a></li>

                    <c:if test="${sessionScope.loginUser!=null}">
                        <li><a href="/board/regmod">write</a></li>
                        <li><a href="/user/profile">myPage</a></li>
                        <!-- 서브페이지를 가게된다면 그 서브페이지를 include하고있는 jsp까지 같이 가게됨-->
                        <li><a href="/user/logout">logOut</a></li>
                        <li>${sessionScope.loginUser.uid}님 환영합니다!</li>
                    </c:if>
                    <c:if test="${sessionScope.loginUser==null}">
                        <li><a href="/user/login">login</a> </li>
                        <li><a href="/user/join">join</a> </li>
                    </c:if>
                </ul>
            </nav>
        </div>
        <div class="body">
            <div class="box">
                <jsp:include page="/WEB-INF/view/${requestScope.page}.jsp"></jsp:include>
            </div>
        </div>
        <div class="footer">
            CopyRight 2021. handMade all right reserved.
        </div>
    </div>
    <c:if test = "${err ne null}">
        <script>
            var body= document.querySelector('body');
            body.onload = function (){
                setTimeout(function (){
                    alert('<c:out value="${err}"/>')
                },300);
            };
        </script>
    </c:if>
</body>
</html>