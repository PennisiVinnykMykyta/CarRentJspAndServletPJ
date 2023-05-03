<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Change Profile</title>
</head>
<body>
<form action="user" method="POST">
  First Name: <input type = "text" name = "firstName" value="${changeUser.firstName}">
  <br><br>
  Last Name: <input type = "text" name = "lastName" value="${changeUser.lastName}">
  <br><br>
  Email: <input type = "text" name = "email" value="${changeUser.email}">
  <br><br>
  Password: <input type="text" name = "password" value="${changeUser.password}">
  <br><br>
  Birthday(year-month-day): <input type="text" name = "date" value="${changeUser.birthDate}">
  <br><br>


  <input type="hidden" name="command" value="addOrChangeUser"/>
  <input type="hidden" name="userID" value="${user.id}" />
  <input type="hidden" name="changeID" value="${changeUser.id}"/>
  <input type="hidden" name="object" value="adminChange"/>
  <input type="hidden" name="request" value="change"/>
  <input type="submit" value="Submit" />

</form>

<form action="user" method="GET">
  <input type="hidden" name="command" value="adminHomepage"/>
  <input type="hidden" name="userID" value="${user.id}"/>
  <input type="submit" value="Cancel" />
</form>

</body>
</html>

