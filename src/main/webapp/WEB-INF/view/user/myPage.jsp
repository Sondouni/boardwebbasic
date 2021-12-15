<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/res/css/user/myPage.css">
<!--
    2차메뉴를 나타나게 하는 jsp
(layout.jsp와 같은 역활활
-->
<div class="myPageBox">

<div class="subContainer">
    <div class="subMenus">
        <ul>
            <div><a href="/user/profile">profile</a></div>
            <hr>
            <div><a href="/user/password">password change</a></div>
        </ul>
    </div>
    <div class="subBody"><jsp:include page="/WEB-INF/view/${requestScope.subPage}.jsp"></jsp:include></div>
</div>
</div>
