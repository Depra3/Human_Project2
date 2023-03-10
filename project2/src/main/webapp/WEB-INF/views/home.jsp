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
.on('click','#boardView',function(){
    let ndx = $(this).parent().parent().index();
    let id = $('#tblboard tr:eq(' + ndx + ') td:eq(0) input').val();
    // console.log(id);
    $.post('/boardView',{optype:'view', id:id},function(){
    },'text');
})
</script>
<style>
    #tblboard {
        width: 700px;
    }
</style>
<body>
    <h1>홈페이지</h1>
    
    <h1>Welcome to My Home!!!</h1>
    
    <div id="dvHead" style="width: 100%;"></div>
    <table border="1" align="center" id="tblboard">
        <tr>
            <th>번호</th><th>글 제목</th><th>글 작성자</th><th>조회수</th><th>등록 일자</th><th>수정 일자</th>
            <c:if test="${not empty gUserid}">
                <th>작업 선택</th>
            </c:if>
        </tr>
        <c:forEach var="cList" items="${cList}">
            <tr id="trA">
                <td align="center"><input type="hidden" value="${cList.id}"></td>
                <td><a href="/boardView" id="boardView">${cList.title}</a></td>
                <td>${cList.author}</td>
                <td>${cList.join}</td>
                <fmt:formatDate var="r_date" value="${cList.reg_date}" pattern="yyyy-MM-dd hh:mm:ss"/>
                <td>${r_date}</td>
                <fmt:formatDate var="m_date" value="${cList.mod_date}" pattern="yyyy-MM-dd hh:mm:ss"/>
                <td>${m_date}</td>
                
                <c:if test="${gUserid eq cList.author && admin eq 'False'}">
                    <td>
                    <input type="button" id="btnUp" name="btnUp" value="수정">
                    <input type="button" id="btnDel" name="btnDel" value="삭제"></td>
                </c:if>
                <c:if test="${admin eq 'True' || admin eq 'M'}">
                    <td>
                    <input type="button" id="btnUp" name="btnUp" value="수정">
                    <input type="button" id="btnDel" name="btnDel" value="삭제"></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <a href="/mg">관</a>
</body>
</html>