<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Change Book</title>
</head>
<body>
<form action="book" method="POST">

  Start Date: <input type="date" name="startDate"  value="${book.startDate}" min="2023-05-10" max="2024-05-10">
  <br><br>
  Drop-Off Date: <input type="date" name="endDate" value="${book.endDate}" min="2023-05-10" max="2024-05-10">
  <br><br>
  <label for="cars">Choose a car:</label>  ${book.car.brand} ${book.car.model} ${book.car.color}
  <select id="cars">
    <option value=""></option>
  </select>
  <br><br>
  User Email: "${book.user.email}"
  <br><br>
  <input type="hidden" name="command" value="addBook"/>
  <input type="hidden" name="bookID" value="${book.id}" />
  <input type="hidden" name="userID" value="${user.id}" />
  <input type="submit" value="Submit" />
</form>
<br>
<form action="user" method="GET"> //tronare all apgina  di admin o user
  <input type="hidden" name="command" value="admin" />
  <input type="hidden" name="adminID" value="${admin.id}" />
  <input type="submit" value="Go Back" />
</form>

</body>
</html>
