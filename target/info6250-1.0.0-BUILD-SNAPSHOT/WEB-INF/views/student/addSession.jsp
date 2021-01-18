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
        <div class="row margin-top-20">
        <c:url var="selectUrl" value="/student/viewSession.htm"/>
            <form action="${selectUrl}" method="post">
                Select your department：
                <select name="department">
                    <option value="Information Systems">Information Systems</option>
                    <option value="Computer Science">Computer Science</option>
                    <option value="Data Analysis">Data Analysis</option>
                </select>
                <input type="submit" value="Search">
            </form>
                
                <form action="${pageContext.request.contextPath }/student/addSession.htm" method="post">
                    <input type="text" name="department" hidden value="${department}">
                <table border="1">
                <tr>
                    <th>--</th>
                    <th >Course name</th>
                    <th>Instructor</th>
                    <th>Location</th>
                    <th>Time</th>
                    <th>Credit</th>
                    <th>Description</th>
                </tr>
                <c:forEach var="sessions" items="${sessions}" varStatus="loop">
                    <tr>
                        <td><input type="checkbox" name="selectedSessions" value="${sessions.id}"></td>
                        <td>${sessions.course.name}</td>
                        <td>${sessions.instructor.firstName} ${sessions.instructor.lastName}</td>
                        <td>${sessions.location}</td>
                        <td>${sessions.time}</td>
                        <td>${sessions.course.credit}</td>
                        <td>${sessions.course.description}</td>
                    </tr>
                </c:forEach>
                </table>
                <input type = "submit" value="SELECT"/><br/>
                ${requestScope["msg1"]}
               </form>


        </div>
</body>
</html>