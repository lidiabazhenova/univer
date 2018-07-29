<%@include file="../common/form-page-begin.jspf" %>
<%@include file="../common/scriptproduct.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Addition new product in list</H1>

    <div class="container" style="margin-bottom: 70px">

        <form id="formproduct" action="/add-product.do" method="post" onsubmit="return checkForm(this);">
            <input type="hidden" name="orderId" id="orderId" value="${order.orderId}"/>

            <h4 class="text-info text-center">
                Your new product to order : ${order.orderTitle}
            </h4>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">URL:</H4></label>
                <input type="text" name="productUrl" id="productUrl" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Product:</H4></label>
                <input type="text" name="productName" id="productName" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Price:</H4></label>
                <input type="text" name="productPrice" id="productPrice" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Quantity:</H4></label>
                <input type="text" name="productQuantity" id="productQuantity" class="form-control"/><br/>
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

<%@include file="../common/form-page-end" %>