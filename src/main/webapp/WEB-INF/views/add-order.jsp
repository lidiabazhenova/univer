<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../common/form-page-begin.jspf" %>
<%@include file="../common/scriptproduct.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Addition new order</H1>

    <div class="container" style="margin-bottom: 70px">

        <form id="formproduct" action="/add-order.do" method="post" onsubmit="return checkForm(this);">

            <h4 class="text-info text-center">
                Your new order:
            </h4>

            <fieldset class="form-group">
                <label><H4 style="color: #fff">Order name:</H4></label>
                <input type="text" name="orderTitle" id="orderTitle" class="form-control"/><br/>
            </fieldset>

            <div class="list-add-form">
                <div id="mybutton" class="group add-group">
                    <input type="submit" class="btn btn-success" value="Add"/>
                </div>
            </div>
        </form>
        <div><img src="/img/animation.gif"></div>
    </div>
</div>

<%@include file="../common/form-page-end.jspf" %>