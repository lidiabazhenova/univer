<%@include file="../common/header.jspf"%>

<%@include file="../common/navigation.jspf"%>

<div class="container">
    <H1>Welcome, ${username}</H1>

    <table class="table table-striped">
        <caption>Your purchasing list:</caption>
        <thead>
        <th>URL</th>
        <th>Products</th>
        <th>Actions</th>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.productUrl}</td>
                <td>${product.productName}</td>
                <td><a class="btn btn-danger"
                       href="/delete-product.do?product=${product.productUrl}&productName=${product.productName}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="error-msg">${errorMessage}</div>
    <a class="btn bg-success" href="/add-product.do">Add New Product</a>
</div>

<%@include file="../common/footer.jspf"%>
