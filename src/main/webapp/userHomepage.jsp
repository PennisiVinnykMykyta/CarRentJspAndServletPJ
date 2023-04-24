<%@ page import="com.example.fullstackpj.entities.Book" %>
<%@ page import="com.example.fullstackpj.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<c:forEach items="${user.getBookings()}" var="book">
    <hr>
    <h4>Booking number ${book.getId()}</h4>
    Car: ${book.getCar().getBrand()} ${book.getCar().getModel()}<br>
    Starting Date: ${book.getStartDate()}<br>
    Drop-off Date: ${book.getEndDate()}<br>
    <input type = "submit" value = "Modify"> <input type = "submit" value = "Cancel">
    <hr>
</c:forEach>


</body>
</html>
