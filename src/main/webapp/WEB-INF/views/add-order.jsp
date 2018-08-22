<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../common/form-page-begin.jspf" %>
<%@include file="../common/scriptorder.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Добавление нового заказа</H1>

    <div class="container" style="margin-bottom: 70px">

        <form id="formorder" action="/add-order.do" method="post" onsubmit="return checkForm(this);">

            <fieldset class="form-group">
                <label><H4 style="color: #fff">Название заказа:</H4></label>
                <input type="text" name="orderTitle" id="orderTitle" class="form-control"/><br/>
            </fieldset>

            <div class="list-add-form">
                <div id="mybutton" class="group add-group">
                    <input type="submit" class="button add-button" value="Добавить заказ"/>
                </div>
            </div>
        </form>
        <div><img src="/img/animation.gif"></div>
    </div>
</div>

<%@include file="../common/scriptorderfocus.jspf" %>
<%@include file="../common/form-page-end.jspf" %>