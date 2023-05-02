<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Car List</title>
</head>
<body>
<h3>Accessed by: ${admin.firstName} ${admin.lastName}</h3>

<form action="user" method="GET">
    <input type="hidden" name="command" value="admin" />
    <input type="hidden" name="adminID" value="${admin.id}" />
    <input type="submit" value="Go Back" />
</form>

<h4>List of all the cars present in the parking lot:</h4>

<table border="1px">
    <tr>
        <th>
            Car Number
        </th>
        <th>
            Brand
        </th>
        <th>
            Model
        </th>
        <th>
            Color
        </th>
        <th>
            Number Plate
        </th>
        <th>
            Options
        </th>
    </tr>

    <c:forEach items="${carList}" var="car">
           <tr>
               <td>
                   ${car.id}
               </td>
               <td>
                       ${car.brand}
               </td>
               <td>
                       ${car.model}
               </td>
               <td>
                       ${car.color}
               </td>
               <td>
                       ${car.numberPlate}
               </td>
               <td>
                   <form action="car" method="POST">
                       <input type="hidden" name="command" value="addCarView" />
                       <input type="hidden" name="adminID" value="${admin.id}" />
                       <input type="hidden" name="carID" value="${car.id}">
                       <input type="submit" value="Modify Car">
                   </form>

                   <form action="car" method="POST">
                       <input type="hidden" name="command" value="delete" />
                       <input type="hidden" name="adminID" value="${admin.id}" />
                       <input type="hidden" name="deleteID" value="${car.id}">
                       <input type="submit" value="Delete Car">
                   </form>
               </td>
           </tr>
    </c:forEach>

</table>

</body>
</html>
