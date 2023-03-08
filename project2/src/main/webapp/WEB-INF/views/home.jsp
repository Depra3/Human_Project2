<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
$(document)
.ready(function(){
    $.post('/loginchk',{},function(data){
        if(data == ''){
            str = "<a href='/register'>회원가입</a>&nbsp;&nbsp;<a href='/login'>로그인</a>&nbsp;&nbsp;<a href='/users'>회원목록</a><br><br>";
        }else{
            str = "<label>" + data + "</label>&nbsp;&nbsp;<button id='btnsignout'>로그 아웃</button><br><br>"
        }
        $('#dvHead').html(str);
        if(data!=''){
			$('#tblboard').append("<tr><td colspan='5' align='right'><input type='button' id='btnWrite', value='글 작성'></td></tr>");
		}
    }, 'text');
})
.on('click', '#btnWrite',function(){
    document.location="/board";
})
.on('click', '#btnsignout', function(){
    $.post('/signout',{},function(data){
        if(data=='ok'){
            document.location="/";
        }else{
            alert('로그아웃 실패. 다시 시도하십시오.');
        }
    }, 'text');
})
</script>
<body>
    <h1>홈페이지</h1>
    
    <h1>Welcome to My Home!!!</h1>
    
    <div id="dvHead" style="width: 100%;"></div>
    <table border="1" align="center" id="tblboard">
        <tr>
            <th>글 번호</th>
            <th>글 제목</th><th>글 내용</th>
            <th>글 작성자</th>
            <th>등록 일자</th>
        </tr>
        <!-- <c:forEach var="list" items="${cList}">
            <tr> -->
                <!-- <td>${list.num}</td> -->
                <!-- <td>${list.title}</td>
                <td>${list.content}</td> -->
                <!-- <td>${list.author}</td> -->
                <!-- <td>${list.reg_date}</td> -->
                <!-- <td>${list.mod_date}</td> -->
            <!-- </tr>
        </c:forEach> -->
    </table>    
    
</body>
</html>