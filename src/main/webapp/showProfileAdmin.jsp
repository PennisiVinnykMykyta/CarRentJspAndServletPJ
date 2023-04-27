<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Admin Profile</title>
</head>
<body>
<form action="user" method="POST">
  First Name: ${user.getFirstName()}
  <br><br>
  Last Name: ${user.getLastName()}
  <br><br>
  Email: ${user.getEmail()}
  <br><br>
  Password: ${user.getPassword()}
  <br><br>
  Birthday: ${user.getBirthDate()}
  <br><br>
</form>

<form action="user" method="POST">
  <input type="hidden" name="command" value="addAdminView" />
  <input type="hidden" name="id" value="${user.getId()}" />
  <input type="hidden" name="type" value="${user.getType()}" />
  <input type="submit" value="Change Profile" />
</form>

<form action="user" method="GET">
  <input type="hidden" name="command" value="admin" />
  <input type="hidden" name="adminID" value="${user.getId()}" />
  <input type="submit" value="Go Back" />

</form>
</body>
</html>
