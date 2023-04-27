<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Customer Homepage</title>
</head>
<body>
<h2>Welcome customer: ${user.getFirstName()} ${user.getLastName()}</h2>

<form action="bookCar.jsp">
    <input type = "submit" value = "Make a booking">
</form>


<h3>List of you're bookings:</h3>

<c:forEach items="${bookingList}" var="book">
    <hr>
    <h4>Booking number ${book.getId()}</h4>
    Car: ${book.getCar().getBrand()} ${book.getCar().getModel()}<br>
    Color: ${book.getCar().getColor()}<br>
    Starting Date: ${book.getStartDate()}<br>
    Drop-off Date: ${book.getEndDate()}<br>
    <input type = "submit" value = "Modify"> <input type = "submit" value = "Cancel">
    <hr>
</c:forEach>


<form action="user" method="GET">
    <input type="hidden" name="command" value="profile" />
    <input type="hidden" name="id" value="${user.getId()}" />
    <input type="submit" value="View Your Profile">
</form>


</body>
</html>

