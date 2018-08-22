<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../common/form-page-begin.jspf" %>

<div class="container margin-header-center">

    <div class="container-fluid table-container">

        <div class="white-list">

            <div class="col-xs-12" style="margin-bottom: 15px">

                <div class="table-responsive">

                    <h4 class="text-info text-center">
                        Пользователи приложения:
                    </h4>

                    <table class="table-responsive">

                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Пользователь</th>
                            <th>Имя</th>
                            <th>Фамилия</th>
                            <th>Действия</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.userId}</td>
                                <td>${user.login}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>
                                    <a class="btn btn-danger"
                                       href="/edit-user.do?userId=${user.userId}">Редактировать</a>
                                    <a class="btn btn-danger"
                                       href="/delete-user.do?userId=${user.userId}">Удалить</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>

    </div>

    <form action="/add-user.do" method="get">
    <div class="list-add-form">
        <div id="mybutton" class="group add-group">
            <input type="hidden" name="userId" value="${user.userId}" />
            <input type="submit" class="button add-button" value="Добавить нового пользователя"/>
        </div>
    </div>
    </form>

    <%@include file="../common/form-page-end.jspf" %>

