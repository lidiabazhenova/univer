<%@include file="../common/form-page-begin.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Addition new user</H1>

    <div class="container" style="margin-bottom: 70px">

        <form action="/add-user.do" method="post">
            <h4 class="text-info text-center">
                Your new user:
            </h4>
            <c:if test="${errorMessage=='Empty Credentials!!'}">
                <div class="alert alert-success alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>${errorMessage}</strong>
                </div>
            </c:if>

            <fieldset class="form-group">
                <label><H4 style="color: #fff">Login:</H4></label>
                <input type="text" name="login" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">First name:</H4></label>
                <input type="text" name="firstName" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Last name:</H4></label>
                <input type="text" name="lastName" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Password:</H4></label>
                <input type="text" name="password" class="form-control"/><br/>
            </fieldset>
            <div class="list-add-form">
                <div id="mybutton" class="group add-group">
                    <input type="submit" class="btn btn-success" value="Add"/>
                </div>


            </div>
        </form>
    </div>
</div>

<%@include file="../common/form-page-end.jspf" %>