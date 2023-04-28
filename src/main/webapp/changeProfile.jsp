<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Change Profile</title>
</head>
<body>
<form action="user" method="POST">
  First Name: <input type = "text" name = "firstName" value="${user.firstName}">
  <br><br>
  Last Name: <input type = "text" name = "lastName" value="${user.lastName}">
  <br><br>
  Email: <input type = "text" name = "email" value="${user.email}">
  <br><br>
  Password: <input type="text" name = "password" value="${user.password}">
  <br><br>
  Birthday(year-month-day): <input type="text" name = "date" value="${user.birthDate}">
  <br><br>


  <input type="hidden" name="command" value="addUser"/>
  <input type="hidden" name="id" value="${user.id}" />
  <input type="hidden" name="type" value="${user.type}" />
  <input type="hidden" name="adminID" value="${adminID}" />
  <input type="submit" value="Submit" />

</form>

<form action="user" method="GET">
  <input type="hidden" name="command" value="customer"/>
  <input type="hidden" name="id" value="${user.id}"/>
  <input type="hidden" name="adminID" value="${adminID}" />
  <input type="submit" value="Cancel" />
</form>

</body>
</html>
