<%@include file="../common/form-page-begin.jspf" %>

<div class="container margin-header-center">

    <div class="container-fluid table-container">

        <div class="white-list">

            <div class="col-xs-12" style="margin-bottom: 15px">

                <div class="table-responsive">

                    <h4 class="text-info text-center">
                        Your purchasing list "${order.orderTitle}"
                    </h4>

                    <table class="table-responsive">

                        <thead>
                        <tr>
                            <th>URL</th>
                            <th>Products</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td>${product.productUrl}</td>
                                <td>${product.productName}</td>
                                <td>
                                    <fmt:formatNumber type="number" maxFractionDigits="2"
                                                      value="${product.productPrice}"/>
                                </td>
                                <td>
                                    <fmt:formatNumber type="number" maxFractionDigits="0"
                                                      value="${product.productQuantity}"/>
                                </td>
                                <td>
                                    <a class="btn btn-danger"
                                       href="/edit-product.do?productId=${product.productId}&orderId=${order.orderId}">Edit</a>
                                    <a class="btn btn-danger"
                                       href="/delete-product.do?productId=${product.productId}&orderId=${order.orderId}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>

    </div>

    <div class="list-add-form">
        <div id="mybutton" class="group add-group">
            <button class="button add-button"><a href="/add-product.do?orderId=${order.orderId}">Add New Product</a>
            </button>
        </div>
    </div>

<%@include file="../common/form-page-end" %>