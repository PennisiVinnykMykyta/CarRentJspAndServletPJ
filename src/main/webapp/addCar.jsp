<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Car</title>
</head>
<body>
<h3>Compile the form below to add a new car to the list:</h3>

<form action="car" method="POST">
    <input type="hidden" name="command" value="addCar" />
    <input type="hidden" name="userID" value="${user.id}">
    <input type="hidden" name="carID" >

    Model: <input type = "text" name="model">
    <br><br>
    Brand: <input type = "text" name="brand">
    <br><br>
    Color: <input type = "text" name="color">
    <br><br>
    NumberPlate: <input type = "text" name="plate">
    <br><br>
    <input type="submit" value="Add New Car"/>

</form>

<form action="user" method="GET">
  <input type="hidden" name="command" value="adminHomepage" />
  <input type="hidden" name="userID" value="${user.id}">
  <input type="submit" value="Go back to the homepage.">
</form>

</body>
</html>
