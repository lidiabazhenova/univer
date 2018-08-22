<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../common/form-page-begin.jspf" %>
<%@include file="../common/scriptuser.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Редактирование ${user.login}:</H1>

    <div class="container" style="margin-bottom: 70px">

        <form id="form" action="/edit-user.do" method="post" onsubmit="return checkForm(this);">

            <fieldset class="form-group">
                <label><H4 style="color: #fff">Логин:</H4></label>
                <input onfocus="this.select();"  type="text" name="login" id="login" class="form-control" value="${user.login}"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Имя:</H4></label>
                <input type="text" name="firstName" id="firstName" class="form-control" value="${user.firstName}"><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Фамилия:</H4></label>
                <input type="text" name="lastName" id="lastName" class="form-control" value="${user.lastName}"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Пароль:</H4></label>
                <input type="password" name="password" id="password" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Повторить пароль:</H4></label>
                <input type="password" name="repeatPassword" id="repeatPassword" class="form-control"/><br/>
            </fieldset>
            <div class="list-add-form">
                <div id="mybutton" class="group add-group">
                    <input type="submit" class="btn btn-success" value="Сохранить изменения"/>
                </div>

            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    function formfocus() {
        document.getElementById('login').focus();
    }
    window.onload = formfocus;
</script>

<%@include file="../common/form-page-end.jspf" %>