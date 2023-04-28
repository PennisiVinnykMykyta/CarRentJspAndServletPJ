<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<h3>Requested by the admin: ${admin.firstName} ${admin.lastName}</h3>
<br>
<form action="user" method="GET">
    <input type="hidden" name="command" value="admin" />
    <input type="hidden" name="adminID" value="${admin.id}" />
    <input type="submit" value="Go Back" />
</form>
<br><br>
<table border="1px">
    <tr>
        <th>
            First Name
        </th>
        <th>
            Last Name
        </th>
        <th>
            Email
        </th>
        <th>
            BirthDate
        </th>
        <th>
            UserType
        </th>
        <th>
            Options
        </th>
    </tr>

    <c:forEach items="${userList}" var="user">
        <tr>
            <td>
                ${user.firstName}
            </td>
            <td>
                ${user.lastName}
            </td>
            <td>
                ${user.email}
            </td>
            <td>
                ${user.birthDate}
            </td>
            <td>
                ${user.type}
            </td>
            <td>
                <form action="user" method="POST">
                    <input type="hidden" name="command" value="addUserView" />
                    <input type="hidden" name="adminID" value="${admin.id}" />
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Modify User">
                </form>

                <form action="user" method="POST">
                    <input type="hidden" name="command" value="delete" />
                    <input type="hidden" name="adminID" value="${admin.id}" />
                    <input type="hidden" name="deleteID" value="${user.id}">
                    <input type="submit" value="Delete User">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
