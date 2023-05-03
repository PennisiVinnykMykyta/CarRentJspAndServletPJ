<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<h3>Requested by the admin: ${user.firstName} ${user.lastName}</h3>
<br>
<form action="user" method="GET">
    <input type="hidden" name="command" value="adminHomepage" />
    <input type="hidden" name="userID" value="${user.id}" />
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

    <c:forEach items="${userList}" var="userEntity">
        <tr>
            <td>
                ${userEntity.firstName}
            </td>
            <td>
                ${userEntity.lastName}
            </td>
            <td>
                ${userEntity.email}
            </td>
            <td>
                ${userEntity.birthDate}
            </td>
            <td>
                ${userEntity.type}
            </td>
            <td>
                <form action="user" method="POST">
                    <input type="hidden" name="command" value="changeProfileUser" />
                    <input type="hidden" name="object" value="adminRequest" />
                    <input type="hidden" name="userID" value="${user.id}" />
                    <input type="hidden" name="changeID" value="${userEntity.id}">
                    <input type="submit" value="Modify User">
                </form>

                <form action="user" method="POST">
                    <input type="hidden" name="command" value="delete" />
                    <input type="hidden" name="userID" value="${user.id}" />
                    <input type="hidden" name="deleteID" value="${userEntity.id}">
                    <input type="submit" value="Delete User">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
