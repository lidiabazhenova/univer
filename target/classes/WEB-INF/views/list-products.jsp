<%@include file="../common/header.jspf" %>

<body>

<%@include file="../common/navigation.jspf" %>

<div class="container-fluid">

    <H1 class="text-center">Welcome, ${username}</H1>

    <div class="container-fluid">

        <div class="row">

            <div class="col-xs-12">

                <div class="table-responsive">

                        <h4 class="text-info text-center">
                            Your purchasing list:
                        </h4>

                    <table class="table-responsive">

                        <thead>
                        <tr>
                            <th>URL</th>
                            <th>Products</th>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        <tbody>
                                                <c:forEach items="${products}" var="product">
                            <tr>
                                <td class=".tbody">${product.productUrl}</td>
                                <td>${product.productName}</td>
                                <td>&nbsp;&nbsp;
                                    <a class="btn btn-danger"
                                       href="/delete-product.do?productId=${product.productId}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>

        <div class="container-fluid">
            <button type="button" class="btn bg-success add-button"><a
                    href="/add-product.do">Add
                New Product</a></button>
        </div>
    </div>
</div>

    <%@include file="../common/footer.jspf" %>

</body>
</html>

