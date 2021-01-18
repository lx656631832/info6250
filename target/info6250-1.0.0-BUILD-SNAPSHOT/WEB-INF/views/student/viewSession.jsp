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
                <table border="1">
                <tr>
                    <th>Course name</th>
                    <th>Course instructor</th>
                    <th>Course time</th>
		    <th>Course location</th>
		    <th>Course department</th>
		    <th>Course credit</th>

                </tr>
                <c:forEach var="session" items="${sessionList}" varStatus="loop">
                    <tr>
                        <td>${session.course.name}</td>
                        <td>${session.instructor.firstName} ${session.instructor.lastName}</td>
                        <td>${session.time}</td>
                        <td>${session.location}</td>
                        <td>${session.department}</td>
                        <td>${session.course.credit}</td>
                        <c:url var="deleteProject" value="/student/deleteSession.htm">
                            <c:param name="id" value="${session.id}"/>
                        </c:url>
                        <td><a href="${deleteProject}" >delete</a></td>
                    </tr>
                </c:forEach>
            </table>

        </div>
</body>
</html>