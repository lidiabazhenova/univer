<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../common/form-page-begin.jspf" %>
<%@include file="../common/scriptproduct.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Добавление нового продукта</H1>

    <div class="container" style="margin-bottom: 70px">

        <form id="formproduct" action="/add-product.do" method="post" onsubmit="return checkForm(this);">
            <input type="hidden" name="orderId" id="orderId" value="${order.orderId}"/>

            <h4 class="text-info text-center">
                Ваш новый продукт в заказе "${order.orderTitle}"
            </h4>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">URL-продукта:</H4></label>
                <input type="text" name="productUrl" id="productUrl" class="form-control" autofocus/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Название продукта:</H4></label>
                <input type="text" name="productName" id="productName" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Цена:</H4></label>
                <input type="text" name="productPrice" id="productPrice" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Количество:</H4></label>
                <input type="text" name="productQuantity" id="productQuantity" class="form-control"/><br/>
            </fieldset>

            <div class="list-add-form">
                <div id="mybutton" class="group add-group">
                    <input type="submit" class="button add-button" value="Добавить продукт"/>
                </div>
            </div>
        </form>
        <div><img src="/img/animation.gif"></div>
    </div>
</div>

<%@include file="../common/form-page-end.jspf" %>