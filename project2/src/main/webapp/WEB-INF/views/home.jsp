<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
$(document)
.on('click', '#btnWrite',function(){
    document.location="/board"
})
</script>
<body>
    <h1>홈페이지</h1>
    
    <h1>Welcome to My Home!!!</h1>
    <a href="/register">회원가입 이동</a>
    <a href='/login'>로그인</a>
    <a href="/users">회원목록 이동</a>
    <br><br>
    <table border="1" align="center">
        <tr>
            <th>글 번호</th><th>글 제목</th><th>글 내용</th><th>글 작성자</th><th>등록 일자</th>
        </tr>
        <c:forEach var="list" items="${c_List}">
            <tr>
                <td>${list.num}</td>
                <td>${list.title}</td>
                <td>${list.content}</td>
                <td>${list.author}</td>
                <td>${list.reg_date}</td>
            </tr>
        </c:forEach>
        <tr><td colspan="5" align="right"><input type="button" id="btnWrite", value="글 작성"></td></tr>
    </table>    
</body>
</html>