<%@include file="../common/form-page-begin.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center">Welcome, ${username}</H1>

    <div class="container table-container">

        <div class="row">

            <div class="col-xs-12" style="margin-bottom: 15px">

                <div class="table-responsive" style="box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);">

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

    </div>

    <div class="container" style="min-height: 130px">
        <div id="mybutton">
            <button class="add-button"><a
                    href="/add-product.do">Add
                New Product</a></button>
        </div>

    </div>

</div>

<%@include file="../common/form-page-end.jspf" %>

