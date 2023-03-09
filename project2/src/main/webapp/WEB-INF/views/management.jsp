<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>관리자 페이지</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
var chk = 0;
var tbl;
$(document)
.ready(function(){
    if("${admin}" == '' || "${admin}" == 'False'){
        alert('관리자 페이지 입니다.')
        document.location="/"
    }
    str = '<c:forEach var="mem" items="${mList}"> \
            <tr> \
                <td><input type="checkbox" name="chkList" value="${mem.uid}"></td> \
                <td>${mem.uid}</td> \
                <td>${mem.nickname}</td> \
                <td>${mem.name}</td> \
                <td>${mem.email}</td> \
                <td>${mem.admin}</td> \
            </tr> \
        </c:forEach>';
    $('#tblList').append(str);    
})
.on('click', '#checkall', function(){
    if (chk == 0){
        $('input[name=chkList]:checkbox').attr("checked",true);
        chk = 1;
    }else{
        $('input[name=chkList]:checkbox').attr("checked",false);
        chk = 0;
    }
})
.on('click', '#btnSch', function(){
    $('#tblList tr:gt(0)').remove();
    let search = $('#search').val();
    $.get('/memSearch',{},function(data){
        for (i = 0; i < data.length; i++) {
            let m_List = data[i];
            console.log(m_List)
            if (m_List['nickname'].includes(search)) {
                let str = '<tr><td><input type="checkbox" name="chkList" value='+ m_List["uid"] +'></td>'+
                            '<td>'+m_List['uid']+'</td><td>'+m_List['nickname']+'</td><td>'+m_List['name']+'</td><td>'+m_List['email']+'</td><td>'+m_List['admin']+'</td></tr>';
                $('#tblList').append(str);
            }            
        }
    }, 'json');
})
.on('click', '#btnDel',function(){
    let va = [];
    $("input[name='chkList']:checked").each(function(){
        va.push($(this).val());
    })
    console.log(va);
    $.get('/memDel',{optype:'delete', delList:va},function(){
        
    }, 'text');
    // return false;
})
</script>
<body>
    <c:if test="${admin eq 'True'}">
        <table>
            <tr>
                <td>
                    <input type="text" id="search" name="search" size="20">
                    <input type="button" id="btnSch" name="btnSch" width="100" value="검색">    
                </td>
                <td align="right">
                    <input type="button" id="btnAdmin" name="btnAdmin" width="100" value="운영자 지정">
                </td>
            </tr>
            <tr><td colspan="2">
                <table border="1" id="tblList">
                    <tr>
                        <th><input type="checkbox" id="checkall"></th>
                        <th>아이디</th>
                        <th>닉네임</th>
                        <th>이름</th>
                        <th>이메일</th>
                        <th>관리자 구분</th>
                    </tr>
                </table>
            </td></tr>
            <tr>
                <td colspan="2" align="right">
                    <input type="button" id="btnDel" name="btnDel" width="100" value="삭제">
                </td>
            </tr>
        </table>
    </c:if>
</body>
</html>