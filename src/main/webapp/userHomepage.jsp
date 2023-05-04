<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Customer Homepage</title>
</head>
<body>
<h2>Welcome customer: ${user.getFirstName()} ${user.getLastName()}</h2>

<a href="login.jsp">Log Out</a>

<br><br>

<table>
    <tr>
        <td>
            <form action="user" method="GET">
                <input type="hidden" name="command" value="addPage" />
                <input type="hidden" name="userID" value="${user.id}" />
                <input type="hidden" name="object" value="book" />
                <input type="submit" value="Make a new Booking">
            </form>
        </td>
        <td>
            <form action="user" method="GET">
            <input type="hidden" name="command" value="profile" />
            <input type="hidden" name="userID" value="${user.id}" />
            <input type="submit" value="View Your Profile">
            </form>

        </td>
    </tr>
</table>

<h3>List of you're bookings:</h3>

<table border="1px">
    <tr>
        <th>
            Booking Number
        </th>
        <th>
            Car
        </th>
        <th>
            Model
        </th>
        <th>
            Color
        </th>
        <th>
            Number plate
        </th>
        <th>
            Starting Date
        </th>
        <th>
            Drop-Off Date
        </th>
        <th>
            Confirmation
        </th>
        <th>
            Options
        </th>
    </tr>
    <c:forEach items="${bookList}" var="book">
        <tr>
            <td>
                ${book.id}
            </td>
            <td>
                ${book.car.brand}
            </td>
            <td>
                ${book.car.model}
            </td>
            <td>
                ${book.car.color}
            </td>
            <td>
                ${book.car.numberPlate}
            </td>
            <td>
                ${book.startDate}
            </td>
            <td>
                ${book.endDate}
            </td>
            <td>
                ${book.valid}
            </td>
            <td>
                <form action="book" method="POST">
                    <input type="hidden" name="command" value="addOrChangeBooking"/>
                    <input type="hidden" name="userID" value="${user.id}"/>
                    <input type="hidden" name="bookID" value="${book.id}">
                    <input type="submit" value="Modify Booking">
                </form>

                <form action="book" method="POST">
                    <input type="hidden" name="command" value="delete" />
                    <input type="hidden" name="userID" value="${user.id}" />
                    <input type="hidden" name="deleteID" value="${book.id}">
                    <input type="submit" value="Delete Booking">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

