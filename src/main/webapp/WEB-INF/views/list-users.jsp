<%@include file="../common/form-page-begin.jspf" %>

<div class="container margin-header-center">

    <H1 class="text-center header-center">Welcome, Admin</H1>

    <div class="container-fluid table-container">

        <div class="row">

            <div class="col-xs-12" style="margin-bottom: 15px">

                <div class="table-responsive"
                     style="box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);">

                    <h4 class="text-info text-center">
                        List of users:
                    </h4>

                    <table class="table-responsive">

                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Users</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.login}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>
                                    <a class="btn btn-danger"
                                       href="/edit-user.do?userId=${user.userId}">Edit</a>
                                    <a class="btn btn-danger"
                                       href="/delete-user.do?userId=${user.userId}">Delete</a>
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
                    href="/add-user.do">Add
                New User</a></button>
        </div>


    </div>

    <%@include file="../common/form-page-end" %>

