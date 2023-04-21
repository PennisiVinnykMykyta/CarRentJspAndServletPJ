<%@ page import="com.example.fullstackpj.entities.enums.UserType" %>
<%@ page import="com.example.fullstackpj.servlets.Login" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<h4>Please enter your Credentials:</h4>

<form action="login" method="GET">

    First Name: <input type="text" name="firstName"/>

    <br/><br/>

    Last Name: <input type="text" name="lastName" />

    <br/><br/>

    Password: <input type="text" name="password" />

    <br/><br/>

    <tr>
        <td>Enter as:</td>
        <td><input type="radio" name="type" value="admin" />Admin</td>
        <td><input type="radio" name="type" value="customer" />Customer</td>

    </tr>

    <br/><br/>

    <input type="submit" value="Submit"/>

</form>

</body>
</html>
