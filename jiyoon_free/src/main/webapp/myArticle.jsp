<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/all.css">
    <title>todoList</title>
</head>
<body>
<h1 style="margin-top:80px; margin-bottom: 50px">My Todo</h1>
<table class="table table-hover" style="width: 50%; text-align: center;">
    <thead>
    <tr>
        <th scope="col">작성날짜</th>
        <th scope="col">제목</th>
        <th scope="col">관리</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="vo" items="${articleList}">
        <tr style="cursor: pointer;"
            onClick="location.href='http://localhost:8080/jiyoon_free/todo?cmd=update&articleId=${vo.articleId}'">
            <td>${vo.createdAt}</td>
            <td>${vo.title}</td>
            <td onclick="event.cancelBubble=true">
                <a class="manage" href="http://localhost:8080/article?target=delete&articleId=${vo.articleId}"
                   target="_self">삭제</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div style="text-align: center; margin-top:20px; margin-bottom: 50px">
    <div class="cover">
        <div style="display: inline-block;">
            <button type="button" class="btn btn-dark"
                    onclick="location.href='todoForm.jsp'"> 계획 추가
            </button>
        </div>
        <div style="display: inline-block;">
            <button type="button" class="btn btn-dark"
                    onclick="location.href='http://localhost:8080/jiyoon_free/article'"> 전체 투두리스트
            </button>
        </div>
    </div>
    <div style="width:50%; margin-left:auto; margin-right:auto; text-align:right">
        <a href="logout.jsp" class="btn btn-link" target="_self">로그아웃</a>
    </div>
</div>
</body>
</html>
