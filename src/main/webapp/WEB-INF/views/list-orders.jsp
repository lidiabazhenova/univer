<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/form-page-begin.jspf" %>

<div class="container margin-header-center">

    <div class="container-fluid table-container">

        <div class="white-list">

            <div class="col-xs-14" style="margin-bottom: 15px">

                <div class="table-responsive">

                    <h4 class="text-info text-center">
                        Ваши заказы:
                    </h4>

                    <table class="table-responsive">

                        <thead>
                        <tr>
                            <th>Номер</th>
                            <th>Название заказа</th>
                            <th>Действия</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.orderTitle}</td>

                                <td>
                                    <a class="btn btn-danger"
                                       href="/edit-order.do?orderId=${order.orderId}">Редактировать</a>
                                    <a class="btn btn-danger"
                                       href="/delete-order.do?orderId=${order.orderId}">Удалить</a>
                                    <a class="btn btn-danger"
                                       href="/list-products.do?orderId=${order.orderId}">Просмотреть детали</a>
                                    <a class="btn btn-danger"
                                       href="/buy-products.do?orderId=${order.orderId}">Оформить заказ</a>
                                    <a class="btn btn-danger"
                                       href="/history.do?orderId=${order.orderId}">История заказа</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>

    </div>

    <form action="/add-order.do" method="get">
        <div class="list-add-form">
            <div id="mybutton" class="group add-group">
                <input type="submit" class="button add-button" value="Добавить новый заказ"/>
            </div>
        </div>
    </form>

<%@include file="../common/form-page-end.jspf" %>