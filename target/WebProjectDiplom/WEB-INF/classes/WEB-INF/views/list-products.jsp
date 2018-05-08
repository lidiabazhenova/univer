<%@include file="../common/header.jspf" %>

<body>

<%@include file="../common/navigation.jspf" %>

<div class="container-outer">

    <H1 class="text-center">Welcome, ${username}</H1>

    <div class="container">
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="text-info text-center">
                        Your purchasing list:
                    </h4>
                </div>
                <table class="table table-fixed">
                    <thead>
                    <tr>
                        <th class="col-lg-9">URL</th>
                        <th class="col-lg-2">Products</th>
                        <th class="col-lg-1">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${products}" var="product">
                        <tr>
                            <td class="col-lg-9">${product.productUrl}</td>
                            <td class="col-lg-2">${product.productName}</td>
                            <td style="padding-right: 5px">&nbsp;&nbsp;<a class="btn btn-danger"
                                               href="/delete-product.do?productId=${product.productId}">Delete</a></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="error-msg">${errorMessage}</div>
</div>


<div><button type="button" class="btn bg-success" style="position: absolute; right: 200px"><a href="/add-product.do">Add New Product</a></button></div>

<%@include file="../common/footer.jspf" %>

</body>

</html>