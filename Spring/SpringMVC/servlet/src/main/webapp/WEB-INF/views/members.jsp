
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>멤버 리스트</title>
</head>
<body>
    <table>
        <thead>
        <th>id</th><th>username</th><th>age</th>
        </thead>
        <tbody>
            <c:forEach var="member" items="${members}">
                <tr><td>${member.id}</td><td>${member.username}</td><td>${member.age}</td></tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
