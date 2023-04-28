<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Car List</title>
</head>
<body>
Accessed by: ${user.firstName} ${user.lastName}

<form action="car" method="POST">
    <input type="hidden" name="command" value="addCarPage" />
    <input type="hidden" name="adminID" value="${user.id}" />
    <input type="submit" value="Register New Car">
</form>

List of all the cars present in the parking lot:

</body>
</html>
