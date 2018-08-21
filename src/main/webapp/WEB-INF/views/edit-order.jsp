<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../common/form-page-begin.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Редактирование "${order.orderTitle}":</H1>

    <div class="container" style="margin-bottom: 70px">

        <form id="form" action="/edit-order.do" method="post" onsubmit="return checkForm(this);">

            <c:if test="${errorMessage=='Заполните поля!!'}">
                <div class="alert alert-success alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>${errorMessage}</strong>
                </div>
            </c:if>

            <fieldset class="form-group">
                <label><H4 style="color: #fff">Вы можете изменить название заказа:</H4></label>
                <input type="text" name="orderTitle" id="orderTitle" class="form-control"
                       value="${order.orderTitle}"/><br/>
            </fieldset>

            <div class="list-add-form">
                <div id="mybutton" class="group add-group">
                    <input type="submit" class="button add-button" value="Сохранить изменения"/>
                </div>
            </div>
        </form>

    </div>
    </form>
</div>
</div>

<%@include file="../common/form-page-end.jspf" %>