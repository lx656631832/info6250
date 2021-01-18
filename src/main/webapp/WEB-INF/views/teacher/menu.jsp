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

        <c:url var="add" value="/teacher/showAddSession.htm"/>
        <a href="${add}" target="main">Add</a>
        <br/>
        <br/>
        <c:url var="view" value="/teacher/viewSession.htm"/>
        <a href="${view}" target="main">View</a>
        <br/>        
        <br/>
        <c:url var="logout" value="/logout.htm"/>
        <a href="${logout}" target = "_top">Logout</a>
    </div>
</body>
</html>