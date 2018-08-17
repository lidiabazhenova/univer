<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="login-wrap">
    <div class="login-html">
        <div class="error-msg">${errorMessage}</div>
        <div class="login-form">
            <form class="sign-in-htm" action="/login.do" method="post">
                <div class="group">
                    <label for="user" class="label">Введите имя пользователя</label>
                    <input id="user" type="text" name="name" class="input">
                </div>
                <div class="group">
                    <label for="pass" class="label">Заполните пароль</label>
                    <input id="pass" type="password" name="password" class="input" data-type="password">
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Вход">
                </div>
                <div class="hr"></div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
