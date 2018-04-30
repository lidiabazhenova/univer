<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title>Test</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
        <div class="login-form">
            <form class="sign-in-htm" action="/login.do" method="post">
                <div class="login">
                    <input type="text" name="name" placeholder="Username" id="username">
                    <input type="password" name="password" placeholder="password" id="password">
                    <a href="#" class="forgot">forgot password?</a>
                    <input type="submit" value="Sign In">
                </div>
                <div class="shadow"></div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
