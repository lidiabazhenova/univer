<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../common/form-page-begin.jspf" %>
<%@include file="../common/scriptuser.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Добавление нового пользователя</H1>

    <div class="container" style="margin-bottom: 70px">

        <form id="formuser" action="/add-user.do" method="post" onsubmit="return checkForm(this);">

            <fieldset class="form-group">
                <label><H4 style="color: #fff">Логин:</H4></label>
                <input type="text" name="login" id="login" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Имя:</H4></label>
                <input type="text" name="firstName" id="firstName" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Фамилия:</H4></label>
                <input type="text" name="lastName" id="lastName" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Пароль:</H4></label>
                <input type="password" name="password" id="password" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Повтор пароля:</H4></label>
                <input type="password" name="repeatPassword" id="repeatPassword" class="form-control"/><br/>
            </fieldset>

            <div class="list-add-form">
                <div id="mybutton" class="group add-group">
                    <input type="submit" class="button add-button" value="Добавить пользователя"/>
                </div>
            </div>
        </form>

<%@include file="../common/form-page-end.jspf" %>