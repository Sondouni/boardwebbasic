<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/res/css/board/regmod.css">
<div>
    <form class="form1" action="/board/regmod" method="post">
        <h1 class="modH">Write</h1>
        <div><label><input type="text" name="title" placeholder="title"></label></div>
        <div><label><textarea name="ctnt" placeholder="content" rows="10"></textarea></label></div>
        <div>
            <input type="submit" value="write">
            <input type="reset" value="reset">
        </div>
    </form>
</div>