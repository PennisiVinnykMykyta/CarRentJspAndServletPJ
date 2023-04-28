<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Car</title>
</head>
<body>
<h3>Compile the form below to add a new car to the list:</h3>

<form action="car" method="POST">
    <input type="hidden" name="command" value="addCar" />
    <input type="hidden" name="adminID" value="${admin.id}">
    <input type="hidden" name="carID" >

    Model: <input type = "text" name="model">
    <br>
    Brand: <input type = "text" name="brand">
    <br>
    Color: <input type = "text" name="color">
    <br>
    NumberPlate: <input type = "text" name="plate">
    <br>
    <input type="submit" value="Add New Car"/>

</form>

<form action="user" method="GET">
  <input type="hidden" name="command" value="admin" />
  <input type="hidden" name="adminID" value="${admin.id}">
  <input type="hidden" name="id" >
  <input type="submit" value="Go back to the homepage.">
</form>

</body>
</html>
