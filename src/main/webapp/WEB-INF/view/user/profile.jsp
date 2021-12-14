<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div>profile Image</div>
    <div>
        <div>sex : </div>
        <div>name : </div>
    </div>
    <div>
        <form action="/user/profile" method="post" enctype="multipart/form-data">
            <div><label>image : <input type="file" name="profileImg"></label></div>
            <div><input type="submit" value="change profile image"></div>
        </form>
    </div>
</div>