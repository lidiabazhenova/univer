<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <style>
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 60px;
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-default">
    <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="/todo.do">Todos</a></li>
        <li><a href="http://www.in28minutes.com">In28Minutes</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/login.do">Login</a></li>
    </ul>
</nav>

<div class="container">
    <H1>Welcome, ${username}</H1>
    <div>Your purchasing list:</div>
    <ol>
        <c:forEach items="${todos}" var="todo">
            <li>${todo.name}
                <a href="/delete-todo.do?todo=${todo.name}">Delete</a>
            </li>
        </c:forEach>
    </ol>
    <form action="/add-todo.do" method="post">
        <input type="text" name="todo"/>
        <input type="submit" value="Add"/></form>
</div>

<footer class="footer">
    <p>footer content</p>
</footer>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
