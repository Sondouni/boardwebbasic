<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1>
        Welcome!
    </h1>
    <form action="/user/login" method="post" id="frm">
        <div><input type="text" name="uid" placeholder="id" value="ezicland"></div>
        <div><input type="password" name="upw" placeholder="password" value="12345"></div>
        <div>
            <input type="submit" value="login">
            <a href="/user/join"><input type="button" value="join"></a>
        </div>
        <div><input type="button" value="show password" id="btnHide" onclick="btnclick()"></div>
    </form>
</div>
<script src="/res/js/user/login.js"></script>