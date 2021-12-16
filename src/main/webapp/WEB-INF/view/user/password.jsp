<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
    <form class="chgPassword" action="/user/password" method="post" id="frm">
        <input type="password" name="upw" placeholder="current password" required>
        <input type="password" name="changedupw" placeholder="new password" required>
        <input type="password" name="changedupwComfirm" placeholder="new password comfirm" required>
    </form>
    <div>
        <input type="button" value="submit" id="submitBtn"
            data-curUpw ="${sessionScope.loginUser.upw}"
            data-curIuser ="${sessionScope.loginUser.iuser}"
            data-curUid ="${sessionScope.loginUser.uid}">
        <input type="reset" value="reset" id="resetBtn">
    </div>
</div>
<script src="/res/js/user/password.js"></script>