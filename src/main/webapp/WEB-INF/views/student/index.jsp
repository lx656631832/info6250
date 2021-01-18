<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Instructor view</title>

    <style>
        body
        {
            margin: 0;
            padding: 0;
            border: 5;
            overflow: hidden;
            height: 100%;
            max-height: 100%;
        }
 
        #frameTop
        {
            position: absolute;
            top: 0;
            left: 0;
            height: 100px;
            width: 100%;
            overflow: hidden;
            vertical-align: middle;
        }
 
        #frameContentLeft
        {
            position: fixed;
            top: 100px;
            left: 0;
            height: 100%;
            width: 150px;
            overflow: hidden;
            vertical-align: top;
            background-color: #D2E6FA;
        }
 
        #frameContentRight
        {
            position: absolute;
            left: 150px;
            top: 100px;
            height: 100%;
            width: 100%;
            right: 0;
            bottom: 0;
            overflow: hidden;
            background: #fff;
        }
    </style>

</head>
<body>

<c:url var="headerUrl" value="/student/header.htm"/>
<c:url var="menuUrl" value="/student/menu.htm"/>
<c:url var="welcomeUrl" value="/student/welcome.htm"/>

    <div>
        <iframe id="frameTop" src="${headerUrl}"></iframe>
    </div>
 
    <div>
        <iframe id="frameContentLeft" src="${menuUrl}"></iframe>
        <iframe id="frameContentRight" name = "main" src="${welcomeUrl}"></iframe>
    </div>


<%-- <frameset rows="25%,75%">
    <frame src="${headerUrl}" noresize="noresize"
    scrolling="no" frameborder="0"/>
    <frameset cols="15%,85%">
        <frame src="${menuUrl}"noresize="noresize" frameborder="1"/>
        <frame src="${welcomeUrl}" noresize="norezise" name="main" frameborder="1"/>
    </frameset>
</frameset> --%>

</body>
</html>