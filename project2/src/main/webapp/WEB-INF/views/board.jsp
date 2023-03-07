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
//     ses = 
// })
.on('click', '#btnUp',function(){
    
    document.location="/boardUp"
});
</script>
<body>
<form:form method="POST" modelAttribute="comm">
    <table id="tblboard" border="1">
        <tr>
            <td>작성자</td>
            <td><label id="lblauthor"></label></td>
        </tr>
        <tr>
            <td>글 제목</td>
            <td><input type="text" id="title" name="title" size="50"></td>
        </tr>
        <tr>
            <td>글 내용</td>
            <td><textarea id="content" name="content" cols="50" rows="20"></textarea></td>
        </tr>
        <tr><td colspan="2" align="right"><input type="submit" id="btnUp" name="btnUp" value="글 작성"></td>
        </tr>
    </table>
</form:form>
</body>
</html>