<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>관리자 페이지</title>
</head>
<body>
    <div>
        <input type="text" id="search" name="search" size="30">
        <input type="button" id="btnSch" name="btnSch" width="100" value="검색">
        <input type="button" id="btnAdmin" name="btnAdmin" width="100" value="관리자 지정">
    </div>
    <table>
        <tr>
            <th><input type="checkbox" id="checkall"></th>
            <th>아이디</th>
            <th>닉네임</th>
            <th>이름</th>
            <th>이메일</th>
            <th>관리자 구분</th>
        </tr>
        <!-- <c:foreach>
            <tr> -->
                <!-- <td><input type="checkbox" id="checkall"></td>
                <td>${.id}</td>
                <td>${.nickname}</td>
                <td>${.name}</td>
                <td>${.email}</td>
                <td>${.admin}</td> -->
            <!-- </tr>
        </c:foreach> -->
    </table>
    <div>
        <input type="button" id="btnDel" name="btnDel" width="100" value="삭제">
    </div>
</body>
</html>