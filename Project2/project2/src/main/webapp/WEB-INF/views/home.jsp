<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
</head>
<body>
    <h1>홈페이지</h1>
    <%
        if(session.getAttribute("gUserid")==null){
            out.print("<a href="/register">회원가입 이동</a>&nbsp;&nbsp;<a href='/login'>로그인</a>&nbsp;&nbsp;<a href="/users">회원목록 이동</a><br><br>");
        } else{
            out.print("<label>"+session.getAttribute("gUserid")+"</label>&nbsp;&nbsp;<a href='/signout'>로그아웃</a><br><br>");
        }
    %>
    <h1>Welcome to My Home!!!</h1>
</body>
</html>