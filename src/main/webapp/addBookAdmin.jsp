<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Add Booking Date</title>
</head>
<body>
<h3>${message}</h3>
<form action="book" method="POST">
  Start Date:
  <input type="date" name="startDate" />
  <br><br>
  End Date:
  <input type="date" name="endDate" />
  <br><br>
  <input type="hidden" name="command" value="checkBook" />
  <input type="hidden" name="userID"  value="${user.id}">
  <input type="hidden" name="object" value="add">
  <input type="submit" value="Continue">
</form>

<form action="user" method="GET">
  <input type="hidden" name="command" value="adminHomepage" />
  <input type="hidden" name="userID"  value="${user.id}">
  <input type="submit" value="Go back to the homepage.">
</form>
</body>
</html>
