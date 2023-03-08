<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글 작성</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
$(document)
// .ready(function(){
//     session = request.getSession();
//     ses = session.getAttribute("gUserid");
//     console.log(ses)
//     $('#lblauthor').text(ses);
// })
// .on('click', '#btnUp',function(){
//     document.location="/"
// });
</script>
<body>
<form method="POST" action="/board">
    <table id="tblboard" border="1">
        <!-- <tr>
            <td>작성자</td>
            <td colspan="2"><label id="lblauthor"></label></td>
        </tr> -->
        <tr>
            <td>글 제목</td>
            <td colspan="2"><input type="text" id="title" name="title" size="50"></td>
        </tr>
        <tr>
            <td>글 내용</td>
            <td colspan="2"><textarea id="content" name="content" cols="50" rows="20"></textarea></td>
        </tr>
        <tr align="center">
            <td><input type="submit" id="btnUp" name="btnUp" value="등록"></td>
            <td><input type="submit" id="btnUp" name="btnMod" value="수정"></td>
            <td><input type="submit" id="btnUp" name="btnDel" value="삭제"></td>
        </tr>
    </table>
</form>
</body>
</html>