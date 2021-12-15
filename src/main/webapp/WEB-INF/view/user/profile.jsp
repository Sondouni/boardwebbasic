<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
    <c:set var="pImg" value="defualtProfile.png"/>
    <c:if test="${requestScope.data.profileImg !=null}">
        <c:set var="pImg" value="profile/${sessionScope.loginUser.iuser}/${requestScope.data.profileImg}"/>
    </c:if>
    <div><img class="profileImg" src="/res/img/${pImg}"></div>
    <div>
        <div>ID&nbsp;:&nbsp;${requestScope.data.uid}</div>
        <div>Name&nbsp;:&nbsp;${requestScope.data.nm}</div>
        <div>sex&nbsp;:&nbsp;${requestScope.data.gender==1?"Male":"female"}</div>
        <div>Join Date&nbsp;:&nbsp;${requestScope.data.rdt}</div>
    </div>
    <div>
        <form action="/user/profile" method="post" enctype="multipart/form-data">
            <div><label>image : <input type="file" name="profileImg" accept="image/*"></label></div>
            <div><input type="submit" value="upLoad profile image"></div>
        </form>
    </div>
</div>