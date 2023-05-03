<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Change Car</title>
</head>
<body>
<form action="car" method="POST">

    Model: <input type="text" name="model"  value="${car.model}">
    <br>
    Brand: <input type="text" name="brand" value="${car.brand}">
    <br>
    Color: <input type="text" name="color" value="${car.color}">
    <br>
    Plate Number: <input type="text" name="plate" value="${car.numberPlate}">
    <br>
    <input type="hidden" name="command" value="addCar"/>
    <input type="hidden" name="carID" value="${car.id}" />
    <input type="hidden" name="userID" value="${user.id}" />
    <input type="submit" value="Submit" />
</form>
<br>
<form action="user" method="GET">
    <input type="hidden" name="command" value="adminHomepage" />
    <input type="hidden" name="userID" value="${user.id}" />
    <input type="submit" value="Go Back" />
</form>

</body>
</html>
