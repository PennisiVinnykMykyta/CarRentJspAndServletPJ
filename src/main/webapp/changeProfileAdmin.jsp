<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Change Profile Admin</title>
</head>
<body>

<form action="user" method="POST">
  First Name: <input type = "text" name = "firstName" value="${user.getFirstName()}">
  <br><br>
  Last Name: <input type = "text" name = "lastName" value="${user.getLastName()}">
  <br><br>
  Email: <input type = "text" name = "email" value="${user.getEmail()}">
  <br><br>
  Password: <input type="text" name = "password" value="${user.getPassword()}">
  <br><br>
  Birthday(year-month-day): <input type="text" name = "date" value="${user.getBirthDate()}">
  <br><br>


  <input type="hidden" name="command" value="addUser"/>
  <input type="hidden" name="adminID" value="${user.getId()}" />
  <input type="hidden" name="id" value="${user.getId()}" />
  <input type="hidden" name="type" value="${user.getType()}" />
  <input type="submit" value="Submit" />

</form>

<br>

<form action="user" method="GET">
  <input type="hidden" name="command" value="admin"/>
  <input type="hidden" name="adminID" value="${user.getId()}"/>
  <input type="submit" value="Cancel" />
</form>

</body>
</html>
