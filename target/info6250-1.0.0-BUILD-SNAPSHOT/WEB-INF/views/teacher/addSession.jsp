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
    <h3>Add a new session</h3>
    <c:url var="actionUrl" value="/teacher/addSession.htm"/>
    <form method="post" action="${actionUrl}">

        course name: 
        <select name="courseName">
            <c:forEach var="course" items="${requestScope.courseList}">
                <option value="${course.name}">${course.name}</option>
            </c:forEach>
        </select>
        <br>
        Location: <input type = "text" name = "location"/>    
            <br />
        Time: <input type = "text" name = "time"/>    
            <br />
        Department: 
        <select name ="department">
            <option value ="Information Systems">Information Systems</option>
            <option value ="Information Systems">Computer Science</option>
            <option value ="Information Systems">Data Analysis</option>
        </select>
            <br />
        <br>
        <input type="submit" value="Add"><br/>
            ${requestScope["msg4"]}
            ${requestScope["msg5"]}
            
    </form>
</body>
</html>