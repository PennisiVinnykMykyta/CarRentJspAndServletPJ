<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Booking List</title>
</head>
<body>

<h3>Accessed by: ${user.firstName} ${user.lastName}</h3>


<form action="user" method="GET">
  <input type="hidden" name="command" value="adminHomepage"/>
  <input type="hidden" name="userID" value="${user.id}"/>
  <input type="submit" value="Go Back"/>
</form>

<h4>List of all the bookings:</h4>

<table border="1px">
  <tr>
    <th>
      Booking ID
    </th>
    <th>
      User
    </th>
    <th>
      Car
    </th>
    <th>
      StartDate
    </th>
    <th>
      Drop-Off Date
    </th>
    <th>
      Conformation
    </th>
    <th>
      Options
    </th>
  </tr>

  <c:forEach items="${bookList}" var="book">
    <tr>
      <td>
          ${book.id}
      </td>
      <td>
          ${book.user.email}
      </td>
      <td>
          ${book.car.model} ${book.car.brand} ${book.car.color}
      </td>
      <td>
          ${book.startDate}
      </td>
      <td>
          ${book.endDate}
      </td>
      <td>
          ${book.valid}
      </td>
      <td>
        <form action="book" method="POST">
          <input type="hidden" name="command" value="addOrChangeBooking"/>
          <input type="hidden" name="userID" value="${user.id}"/>
          <input type="hidden" name="bookID" value="${book.id}">
          <input type="submit" value="Modify Booking">
        </form>

        <form action="book" method="POST">
          <input type="hidden" name="command" value="delete"/>
          <input type="hidden" name="userID" value="${user.id}"/>
          <input type="hidden" name="deleteID" value="${book.id}">
          <input type="submit" value="Delete Booking">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
