<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h3>Update a session</h3>
    <c:url var="actionUrl" value="/teacher/updateSession.htm"/>
    <form method="post" action="${actionUrl}">
		<input type="text" name="id" hidden value="${requestScope.session.id}">
        course name: 
        <select name="courseName">
            <c:forEach var="course" items="${requestScope.courseList}">
                <option value="${course.name}">${course.name}</option>
            </c:forEach>
        </select>
        <br>
        Location: <input type = "text" name = "location" value = "${requestScope.session.location }"/>
            <br />
        Time: <input type = "text" name = "time" value = "${requestScope.session.time }"/>
            <br />
        Department: <select name ="department">
            <option value ="Information Systems">Information Systems</option>
            <option value ="Information Systems">Computer Science</option>
            <option value ="Information Systems">Data Analysis</option>
        </select>
            <br />
        <br>
        <input type="submit" value="Update"><br/>
            ${requestScope["msg4"]}
            ${requestScope["msg5"]}
    </form>
</body>
</html>