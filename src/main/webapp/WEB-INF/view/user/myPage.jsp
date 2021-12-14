<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
