<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			$('#tblboard').append("<tr><td colspan='7' align='right'><input type='button' id='btnWrite', value='글 작성'></td></tr>");
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
            <!-- <th>글 번호</th> -->
            <th>글 제목</th><th>글 내용</th><th>글 작성자</th><th>등록 일자</th><th>수정 일자</th><th>작업 선택</th>
        </tr>
        <c:forEach var="cList" items="${cList}">
            <tr>
                <!-- <td>${cList.num}</td> -->
                <td>${cList.title}</td>
                <td>${cList.content}</td>
                <td>${cList.author}</td>
                <fmt:formatDate var="r_date" value="${cList.reg_date}" pattern="yyyy-MM-dd"/>
                <td>${r_date}</td>
                <fmt:formatDate var="m_date" value="${cList.mod_date}" pattern="yyyy-MM-dd"/>
                <td>${m_date}</td>
                <td>
                <c:if test="${gUserid eq cList.author}">
                    <input type="button" id="btnUp" name="btnUp" value="수정">
                    <input type="button" id="btnDel" name="btnDel" value="삭제"></td>
                </c:if>
                <c:if test="${'True' eq List.admin}">
                    <input type="button" id="btnUp" name="btnUp" value="수정">
                    <input type="button" id="btnDel" name="btnDel" value="삭제"></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</body>
</html>