<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.fullstackpj.entities.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page session = "true" %>

<html>
<head>
    <title>Admin Homepage</title>
</head>
<body>
<h3>Welcome admin: ${user.getFirstName()} ${user.getLastName()}</h3>


<form action="user" method="GET">
    <input type="hidden" name="command" value="adminProfile" />
    <input type="hidden" name="id" value="${user.getId()}" />
    <input type="submit" value="View Your Profile">
</form>

<form action="user" method="POST">
    <input type="hidden" name="command" value="addUserPage" />
    <input type="hidden" name="adminID" value="${user.getId()}" />
    <input type="submit" value="Register New User">
</form>

<h4>List of the Users:</h4>

<c:forEach items="${userList}" var="userVar">
    <hr><hr>
    <h4>User ID: ${userVar.getId()}</h4>
    <br>
    User Email: ${userVar.getEmail()}
    <br>
    User FirstName: ${userVar.getFirstName()}
    <br>
    User LastName: ${userVar.getLastName()}
    <br>
    User Password: ${userVar.getPassword()}
    <br>
    User BirthDate: ${userVar.getBirthDate()}
    <br>
    <c:forEach items="${userVar.getBookings()}" var="book">
        <hr>
        <h4>Booking number ${book.getId()}</h4>
        Car: ${book.getCar().getBrand()} ${book.getCar().getModel()}
        <br>
        Color: ${book.getCar().getColor()}
        <br>
        Starting Date: ${book.getStartDate()}
        <br>
        Drop-off Date: ${book.getEndDate()}
        <br>
        <input type = "submit" value = "Modify Booking"> <input type = "submit" value = "Cancel Booking">
        <hr>
    </c:forEach>
    Options for this User:
    <br>

    <form action="user" method="POST">
        <input type="hidden" name="command" value="addUserView" />
        <input type="hidden" name="adminID" value="${user.getId()}" />
        <input type="hidden" name="id" value="${userVar.getId()}">
        <input type="submit" value="Modify User">
    </form>

    <form action="user" method="POST">
    <input type="hidden" name="command" value="delete" />
    <input type="hidden" name="adminID" value="${user.getId()}" />
    <input type="hidden" name="deleteID" value="${userVar.getId()}">
    <input type="submit" value="Delete User">
    </form>
    <hr><hr>
</c:forEach>

<!-- Customer list // for each one I can modify or cancel their profile and can also see their bookings
based on the bookings list admin should be able to approve/disapprove of the booking made by customer
add customer // allows to add new customers -->

<!-- from the admin homepage we should be able to access: List of Cars, List of Pending Booking,-->

</body>
</html>


