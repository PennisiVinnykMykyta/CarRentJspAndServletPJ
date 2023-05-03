<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Change Profile Admin</title>
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


  <input type="hidden" name="command" value="addOrChangeUser"/>
  <input type="hidden" name="request" value="change"/>
  <input type="hidden" name="object" value="normalChange"/>
  <input type="hidden" name="userID" value="${user.id}" />
  <input type="submit" value="Submit" />

</form>

<br>

<form action="user" method="GET">
  <input type="hidden" name="command" value="adminHomepage"/>
  <input type="hidden" name="userID" value="${user.id}"/>
  <input type="submit" value="Go back to HomePage" />
</form>

</body>
</html>
