<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<h3>Compile the form below to add a user; Use yyyy-mm-dd for the birthday date:</h3>
<form action="user" method="POST">
    <input type="hidden" name="command" value="addUser" />

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

    Type of user: <input type="text" name="type" />

    <br/><br/>


    <input type="submit" value="Submit"/>

</form>
<form action="user" method="GET">
    <input type="hidden" name="command" value="admin" />
    <input type="hidden" name="id" value="${user.getId()}">
    <input type="submit" value="Go back to the homepage.">
</form>
</body>
</html>
