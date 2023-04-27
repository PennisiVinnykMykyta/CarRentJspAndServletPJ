<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<h3>Compile the form below to add a user; Use yyyy-mm-dd for the birthday date:</h3>
<form action="user" method="POST">
    <input type="hidden" name="command" value="addUser" />
    <input type="hidden" name="adminID" value="${admin.getId()}">

    Email: <input type = "text" name="email">

    <br/><br/>

    Password: <input type="text" name="password" />

    <br/><br/>

    First Name: <input type="text" name="firstName" />

    <br/><br/>

    Last Name: <input type="text" name="lastName" />

    <br/><br/>

    Birth Date: <input type="text" name="date" />

    <br/><br/>
<tr>
    <td>Type of user:</td>
    <td><input type="radio" name="type" value="admin" />Admin</td>
    <td><input  type="radio" name="type" value="customer" checked="true" />Customer</td>
</tr>
    <br/><br/>


    <input type="submit" value="Add New User"/>

</form>
<form action="user" method="GET">
    <input type="hidden" name="command" value="admin" />
    <input type="hidden" name="adminID" value="${admin.getId()}">
    <input type="hidden" name="id" >
    <input type="submit" value="Go back to the homepage.">
</form>


</body>
</html>
