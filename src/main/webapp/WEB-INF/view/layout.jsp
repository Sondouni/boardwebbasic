<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${requestScope.title}</title>
    <link rel="stylesheet" href="/res/css/common.css">
</head>
<body>
    <div class="container">
        <div class="header">

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
</body>
</html>