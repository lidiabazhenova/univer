<%@include file="../common/form-page-begin.jspf" %>

<div class="container margin-header-center">

    <div class="container-fluid table-container">

        <div class="white-list">

            <div class="col-xs-12" style="margin-bottom: 15px">

                <div class="table-responsive">

                    <h4 class="text-info text-center">
                        History for "${order.orderTitle}"
                    </h4>

                    <table class="table-responsive">

                        <thead>
                        <tr>
                            <th>Description</th>
                            <th>Date</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${history}" var="history">
                            <tr>
                                <td>${history.description}</td>
                                <td>${history.date}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>

    </div>

<%@include file="../common/form-page-end" %>