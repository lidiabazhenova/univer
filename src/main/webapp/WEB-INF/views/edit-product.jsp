<%@include file="../common/form-page-begin.jspf" %>
<%@include file="../common/scriptproduct.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Edit product</H1>

    <div class="container" style="margin-bottom: 70px">

        <form id="form" action="/edit-product.do" method="post" onsubmit="return checkForm(this);">
            <input type="hidden" name="orderId" id="orderId" value="${orderId}"/>
            <h4 class="text-info text-center">
                Edit:
            </h4>
            <c:if test="${errorMessage=='Empty Credentials!!'}">
                <div class="alert alert-success alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>${errorMessage}</strong>
                </div>
            </c:if>

            <fieldset class="form-group">
                <label><H4 style="color: #fff">URL:</H4></label>
                <input type="text" name="productUrl" id="productUrl" class="form-control" value="${product.productUrl}"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Product:</H4></label>
                <input type="text" name="productName" id="productName" class="form-control" value="${product.productName}"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Price:</H4></label>
                <input type="text" name="productPrice" id="productPrice" class="form-control"
                       value='<fmt:formatNumber type="number" maxFractionDigits="2"
                                                      value="${product.productPrice}"/>'/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Quantity:</H4></label>
                <input type="text" name="productQuantity" id="productQuantity" class="form-control"
                       value='<fmt:formatNumber type="number" maxFractionDigits="0"
                                                      value="${product.productQuantity}"/>'/><br/>
            </fieldset>

            <div class="list-add-form">
                <div id="mybutton" class="group add-group">
                    <input type="submit" class="btn btn-success" value="Confirm"/>
                </div>


            </div>
        </form>

    </div>
    </form>
</div>
</div>

<%@include file="../common/form-page-end.jspf" %>