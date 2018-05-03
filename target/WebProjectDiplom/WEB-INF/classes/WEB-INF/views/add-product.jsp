<%@include file="../common/header.jspf"%>

<%@include file="../common/navigation.jspf"%>

<div class="container">
    Your new action:
    <form action="/add-product.do" method="post">
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


