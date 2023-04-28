<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<form action="user" method="POST">
  First Name: ${user.firstName}
  <br><br>
  Last Name: ${user.lastName}
  <br><br>
  Email: ${user.email}
  <br><br>
  Password: ${user.password}
  <br><br>
  Birthday: ${user.birthDate}
  <br><br>
</form>

<form action="user" method="POST">
  <input type="hidden" name="command" value="addUserView" />
  <input type="hidden" name="id" value="${user.id}" />
  <input type="hidden" name="type" value="${user.type}" />
  <input type="submit" value="Change Profile" />
</form>

<form action="user" method="GET">
  <input type="hidden" name="command" value="customer" />
  <input type="hidden" name="id" value="${user.id}" />
  <input type="submit" value="Go Back" />
</form>
</body>
</html>
