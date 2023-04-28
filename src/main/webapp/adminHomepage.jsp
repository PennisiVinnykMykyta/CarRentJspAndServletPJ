<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.fullstackpj.entities.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page session = "true" %>

<html>
<head>
    <title>Admin Homepage</title>
</head>
<body>
<h3>Welcome admin: ${user.firstName} ${user.lastName}</h3>

<table>
    <tr>
        <td>
            <form action="user" method="GET">
                <input type="hidden" name="command" value="adminProfile" />
                <input type="hidden" name="id" value="${user.id}" />
                <input type="submit" value="View Your Profile">
            </form>
        </td>
        <td>
            <form action="user" method="POST">
                <input type="hidden" name="command" value="addPage" />
                <input type="hidden" name="adminID" value="${user.id}" />
                <input type="hidden" name="object" value="user" />
                <input type="submit" value="Register New User">
            </form>
        </td>
        <td>
            <form action="user" method="POST">
                <input type="hidden" name="command" value="addPage" />
                <input type="hidden" name="adminID" value="${user.id}" />
                <input type="hidden" name="object" value="car" />
                <input type="submit" value="Register New Car">
            </form>
        </td>
        <td>
            <form action="user" method="GET">
                <input type="hidden" name="command" value="userList" />
                <input type="hidden" name="adminID" value="${user.id}" />
                <input type="submit" value="View User List">
            </form>
        </td>
        <td>
            <form action="car" method="GET">
                <input type="hidden" name="command" value="carList" />
                <input type="hidden" name="adminID" value="${user.id}" />
                <input type="submit" value="View Car List">
            </form>
        </td>
    </tr>
</table>

</body>
</html>


