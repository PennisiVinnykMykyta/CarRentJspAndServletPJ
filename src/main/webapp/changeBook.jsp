<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Change Book Admin</title>
</head>
<body>
<h3>${message}</h3>

<form action="book" method="POST">
  Start Date: <input type="date" name="startDate" value="${book.startDate}">
  <br><br>
  Drop-Off Date: <input type="date" name="endDate" value="${book.endDate}">
  <br><br>

  <input type="hidden" name="command" value="checkBook" />
  <input type="hidden" name="userID" value="${user.id}">
  <input type="hidden" name="bookID" value="${book.id}">
  <input type="hidden" name="object" value="modify">
  <input type="submit" value="Continue"/>

</form>

<form action="user" method="GET">
  <input type="hidden" name="command" value="customerHomepage"/>
  <input type="hidden" name="userID" value="${user.id}"/>
  <input type="submit" value="Cancel"/>
</form>


</body>
</html>
