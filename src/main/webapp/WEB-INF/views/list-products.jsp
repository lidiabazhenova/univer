<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../common/form-page-begin.jspf" %>

<div class="container margin-header-center">

    <div class="container-fluid table-container">

        <div class="white-list">

            <div class="col-xs-12" style="margin-bottom: 15px">

                <div class="table-responsive">

                    <h4 class="text-info text-center">
                        Ваш лист покупок "${order.orderTitle}"
                    </h4>

                    <table class="table-responsive">

                        <thead>
                        <tr>
                            <th>URL</th>
                            <th>Продукт</th>
                            <th>Цена</th>
                            <th>Количество</th>
                            <th>Действия</th>
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
                                       href="/edit-product.do?productId=${product.productId}&orderId=${order.orderId}">Редактировать</a>
                                    <a class="btn btn-danger"
                                       href="/delete-product.do?productId=${product.productId}&orderId=${order.orderId}">Удалить</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>

    </div>

    <form action="/add-product.do" method="get">
    <div class="list-add-form">
        <div id="mybutton" class="group add-group">
            <input type="hidden" name="orderId" value="${order.orderId}" />
            <input type="submit" class="button add-button" value="Добавить новый продукт"/>
        </div>
    </div>
    </form>
    </form>

<%@include file="../common/form-page-end.jspf" %>