<%@include file="../common/header.jspf"%>

<body>

<%@include file="../common/navigation.jspf"%>

<div class="container">

    <form action="/add-product.do" method="post">
        Your new action:
        <c:if test="${errorMessage=='Empty Credentials!!'}">
            <div class="alert alert-success alert-dismissible">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>${errorMessage}</strong>
            </div>
        </c:if>

        <fieldset class="form-group">
            <label>URL</label>
            <input type="text" name="productUrl" class="form-control"/><br/>
        </fieldset>
        <fieldset class="form-group">
            <label>Product:</label>
            <input type="text" name="productName" class="form-control"/><br/>
        </fieldset>
            <input type="submit" class="btn btn-success" value="Add"/>
    </form>
</div>

<%@include file="../common/footer.jspf"%>

</body>

</html>
