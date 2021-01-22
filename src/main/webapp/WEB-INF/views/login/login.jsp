<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
    <meta charset="UTF-8">
    <title>demo main</title>
</head>
<body>
<h1>demo main 페이지</h1>
<th:block sec:authorize="isAuthenticated()">
    <span sec:authentication="principal.username"></span>님 반가워요!
</th:block>
<a th:href="@{/logout}">로그아웃</a>
</body>
</html>