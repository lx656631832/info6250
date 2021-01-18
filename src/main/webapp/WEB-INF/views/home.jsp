<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<center>

    ${requestScope["msg1"]}
    ${requestScope["msg2"]}
    ${requestScope["msg3"]}
    <form method="post" action ="<%=request.getContextPath()%>/checklogin.htm">
    <h1>Welcome to Course Registration System, Please log in!</h1>
    User Email<input type="text" name="email"/>
        </br>
    Password<input type="password" name = "password"/>
    </br>
    Role <select name = "role">
    		<option value="0">Please select your role</option>
    		<option value = "student">Student</option>
    		<option value = "instructor">Instructor</option>
         </select>
     <br/>
    <input type="submit" value = "Login">
    </form>
</center>
</body>
</html>
