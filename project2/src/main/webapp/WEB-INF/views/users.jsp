<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>회원목록</title>
  </head>
  <body>
    <h1>회원목록</h1>
    <table>
      <tr>
        <th>아이디</th>
        <th>닉네임</th>
      </tr>
      <c:forEach var="mem" items="${mList}">
        <tr>
          <td>${mem.uid}</td>
          <td>${mem.nickname}</td>
        </tr>
      </c:forEach>
    </table>
    <a href="/">홈페이지 이동</a>
  </body>
</html>