<%@include file="../common/form-page-begin.jspf" %>

<div class="container margin-header-center">

    <div class="container-fluid table-container">

        <div class="white-list">

            <div class="col-xs-12" style="margin-bottom: 15px">

                <div class="table-responsive">

                    <h4 class="text-info text-center">
                        Your orders list:
                    </h4>

                    <table class="table-responsive">

                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.orderTitle}</td>

                                <td>
                                    <a class="btn btn-danger"
                                       href="/edit-order.do?orderId=${order.orderId}">Edit</a>
                                    <a class="btn btn-danger"
                                       href="/delete-order.do?orderId=${order.orderId}">Delete</a>
                                    <a class="btn btn-danger"
                                       href="/list-products.do?orderId=${order.orderId}">View Details</a>
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
            <button class="button add-button"><a
                    href="/add-order.do">Add
                New Order</a></button>
        </div>


    </div>

<%@include file="../common/form-page-end" %>