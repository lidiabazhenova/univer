<%@include file="../common/form-page-begin.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Addition new project in list</H1>

    <div class="container" style="height: 100%">

        <form action="/add-product.do" method="post">
            <h4 class="text-info text-center">
            Your new product:
            </h4>
            <c:if test="${errorMessage=='Empty Credentials!!'}">
                <div class="alert alert-success alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>${errorMessage}</strong>
                </div>
            </c:if>

            <fieldset class="form-group">
                <label><H4 style="color: #fff">URL:</H4></label>
                <input type="text" name="productUrl" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Product:</H4></label>
                <input type="text" name="productName" class="form-control"/><br/>
            </fieldset>
            <input type="submit" class="btn btn-success" value="Add"/>
        </form>
        <div><img src="/img/animation.gif"></div>
    </div>
</div>

<%@include file="../common/form-page-end.jspf" %>
