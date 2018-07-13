<%@include file="../common/form-page-begin.jspf" %>
<%@include file="../common/scriptuser.jspf" %>


<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Addition new user</H1>

    <div class="container" style="margin-bottom: 70px">

        <form id="form" action="/add-user.do" method="post" onsubmit="return checkForm(this);">
            <h4 class="text-info text-center">
                Your new user:
            </h4>

            <fieldset class="form-group">
                <label><H4 style="color: #fff">Login:</H4></label>
                <input type="text" name="login" id="login" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">First name:</H4></label>
                <input type="text" name="firstName" id="firstName" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Last name:</H4></label>
                <input type="text" name="lastName" id="lastName" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Password:</H4></label>
                <input type="password" name="password" id="password" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Repeat password:</H4></label>
                <input type="password" name="repeatPassword" id="repeatPassword" class="form-control"/><br/>
            </fieldset>
            <div class="list-add-form">
                <div id="mybutton" class="group add-group">
                    <input type="submit" class="btn btn-success" value="Add"/>
                </div>

            </div>
        </form>
    </div>
</div>

<%@include file="../common/form-page-end" %>