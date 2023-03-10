<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<body>
    <table id="tblboard" border="1">
        <tr><td>제목</td><td><label id="lblTitle"></label>${comm.title }</td></tr>
        <tr><td>내용</td><td><textarea name="content" id="content" rows="10" cols="80" readonly style="resize:none">${comm.content }</textarea></td></tr>
        <tr><td>작성자</td>
            <td><label id=lblWriter>${comm.author }</label></td></tr>
        <tr><td>작성시각</td><td><label id="lblCd">${comm.reg_date }</label></td></tr>
        <tr><td>수정시각</td><td><label id="lblUd">${comm.mod_date }</label></td></tr>		
        <tr><td colspan=2 align=right><a href='/'>목록보기</a></td></tr>
    </table>
</body>
</html>