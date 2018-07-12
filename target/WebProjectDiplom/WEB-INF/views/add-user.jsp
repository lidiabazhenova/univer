<%@include file="../common/form-page-begin.jspf" %>

<script type="text/javascript">

    function checkForm(form) {
        if (form.login.value == "") {
            alert("Error: Login cannot be blank!");
            form.login.focus();
            return false;
        }
        re = /^\w+$/;
        if (!re.test(form.login.value)) {
            alert("Error: Login must contain only letters, numbers and underscores!");
            form.login.focus();
            return false;
        }

        if (form.password.value != "" && form.password.value == form.repeatPassword.value) {
            if (form.password.value.length < 6) {
                alert("Error: Password must contain at least six characters!");
                form.password.focus();
                return false;
            }
            if (form.password.value == form.login.value) {
                alert("Error: Password must be different from Login!");
                form.password.focus();
                return false;
            }
            re = /[0-9]/;
            if (!re.test(form.password.value)) {
                alert("Error: password must contain at least one number (0-9)!");
                form.password.focus();
                return false;
            }
            re = /[a-z]/;
            if (!re.test(form.password.value)) {
                alert("Error: password must contain at least one lowercase letter (a-z)!");
                form.password.focus();
                return false;
            }
            re = /[A-Z]/;
            if (!re.test(form.password.value)) {
                alert("Error: password must contain at least one uppercase letter (A-Z)!");
                form.password.focus();
                return false;
            }
        } else {
            alert("Error: Please check that you've entered and confirmed your password!");
            form.password.focus();
            return false;
        }

        alert("You entered a valid password: " + form.password.value);
        return true;
    }

</script>

<div class="container margin-header-center">

    <H1 class="text-center header-center" style="margin-top: 70px">Addition new user</H1>

    <div class="container" style="margin-bottom: 70px">

        <form id="form" action="/add-user.do" method="post" onsubmit="return checkForm(this);">
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
                <input type="text" name="password" id="password" class="form-control"/><br/>
            </fieldset>
            <fieldset class="form-group">
                <label><H4 style="color: #fff">Repeat password:</H4></label>
                <input type="text" name="repeatPassword" id="repeatPassword" class="form-control"/><br/>
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