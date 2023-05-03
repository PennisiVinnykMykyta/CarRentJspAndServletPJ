<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Admin Homepage</title>
</head>
<body>
<h3>Welcome admin: ${user.firstName} ${user.lastName}</h3> <a href="login.jsp">Log Out</a>

<br><br>
<form action="user" method="GET">
    <input type="hidden" name="command" value="adminProfile" />
    <input type="hidden" name="userID" value="${user.id}" />
    <input type="submit" value="View Your Profile">
</form>

<form action="user" method="GET">
    <input type="hidden" name="command" value="bookList" />
    <input type="hidden" name="userID" value="${user.id}" />
    <input type="submit" value="View Your Bookings">
</form>

<br>
<hr>
<h3>Admin Functions:</h3>
<table >
    <tr>
        <td>
            <form action="user" method="POST">
                <input type="hidden" name="command" value="addPage" />
                <input type="hidden" name="userID" value="${user.id}" />
                <input type="hidden" name="object" value="user" />
                <input type="submit" value="Register New User">
            </form>
        </td>
        <td>
            <form action="user" method="POST">
                <input type="hidden" name="command" value="addPage" />
                <input type="hidden" name="userID" value="${user.id}" />
                <input type="hidden" name="object" value="car" />
                <input type="submit" value="Register New Car">
            </form>
        </td>
        <td>
            <form action="user" method="POST">
                <input type="hidden" name="command" value="addPage" />
                <input type="hidden" name="userID" value="${user.id}" />
                <input type="hidden" name="object" value="bookAdmin" />
                <input type="submit" value="Make a new Booking">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="user" method="GET">
                <input type="hidden" name="command" value="userList" />
                <input type="hidden" name="userID" value="${user.id}" />
                <input type="submit" value="View User List">
            </form>
        </td>
        <td>
            <form action="car" method="GET">
                <input type="hidden" name="command" value="carList" />
                <input type="hidden" name="userID" value="${user.id}" />
                <input type="submit" value="View Car List">
            </form>
        </td>
        <td>
            <form action="book" method="GET">
                <input type="hidden" name="command" value="bookList" />
                <input type="hidden" name="userID" value="${user.id}" />
                <input type="submit" value="View Booking List">
            </form>
        </td>
    </tr>
</table>

</body>
</html>


