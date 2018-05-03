<%@include file="../common/header.jspf"%>

<%@include file="../common/navigation.jspf"%>

<div class="container">
    <H1>Welcome, ${username}</H1>

    <table class="table table-striped">
        <caption>Your purchasing list:</caption>
        <thead>
        <th>Description</th>
        <th>Product</th>
        <th>Actions</th>
        </thead>
        <tbody>
        <c:forEach items="${todos}" var="todo">
            <tr>
                <td>${todo.name}</td>
                <td>${todo.product}</td>
                <td><a class="btn btn-danger"
                       href="/delete-todo.do?todo=${todo.name}&product=${todo.product}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="error-msg">${errorMessage}</div>
    <a class="btn bg-success" href="/add-todo.do?todo=${todo.name}">Add New Todo</a>
</div>

<%@include file="../common/footer.jspf"%>