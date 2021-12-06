<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/res/css/board/regmod.css">
<div>
    <form class="form1" action="/board/regmod" method="post">
        <input type="hidden" name="iboard" value="${param.iboard}">
        <h1 class="modH">${requestScope.title}</h1>
        <div><label><input type="text" name="title" placeholder="title" value="${requestScope.data.title}"></label></div>
        <div><label><textarea name="ctnt" placeholder="content" rows="10">${requestScope.data.ctnt}</textarea></label></div>
        <div>
            <input type="submit" name="submit" value="${requestScope.title}">
            <input type="reset" value="reset">
        </div>
    </form>
</div>

