<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!--
Template Name: Colossus
Author: <a href="https://www.os-templates.com/">OS Templates</a>
Author URI: https://www.os-templates.com/
Licence: Free to use under our free template licence terms
Licence URI: https://www.os-templates.com/template-terms
-->
<html>
<head>
<title>Colossus | Pages | Sidebar Right</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="resources/css/layout.css" rel="stylesheet"
	type="text/css" media="all">

<!-- 개인 CSS -->
<style type="text/css">
* {
	font-family: initial !important;
}

input {
	border-style: none;
	margin: 0;
	padding: 0;
}

table {
	text-align: center;
}

#title {
	font-size: 30pt;
	color: black;
}

table a {
	font-weight: bold;
	color: black;
}

.insert {
	background: #639fff;
}

.delete {
	background: red;
}

.add {
	background: gray;
}

button {
	float: right;
	margin-right: 30px;
	border-style: none;
	width: 100px;
	height: 40px;
	font-weight: bold;
	color: white;
	cursor: pointer;
}

.delete{
background: red;
width: 70px;
height: 30px;
}

.add{
background: #639fff;
width: 80px;
height: 30px;
float:right;
}

</style>

</head>
<body id="top">
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<c:import url="../header.jsp"></c:import>
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<div class="wrapper row3">
		<main class="container clear"> <!-- main body --> <!-- ################################################################################################ -->
		<div class="content three_quarter first" id="content">
			<!-- ################################################################################################ -->

			<h1 id="title">공지사항관리</h1>

			<h1>공지사항 작성</h1>
			
			<form action="updateNotice.do" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<th>제목</th>
					<td><input type="text" size="30" name="title" value="${ notice.title }" required="required"></td>


				</tr>

				<tr>
					<th>종류</th>
					<td><select name="noticetype" required="required">
							
							<option value="공지사항">공지사항</option>
							<option value="휴/복학 안내">휴/복학 안내</option>
							<option value="수강신청안내">수강신청안내</option>
							
							
					</select></td>
				</tr>

				<tr>
					<th>첨부파일</th>
					<td colspan="3">
					
					<c:if test="${ notice.ofilename != '' }">
					
					
					
					<c:forEach items="${ fileList }" var="file">
					
					<c:if test="${ file.ofilename ne '' }">
					<div class="fileDiv">
					${ file.ofilename } <button type="button" class="delete" onclick="deleteFile('${file.ofilename}','${file.rfilename }','${notice.noticeno }')">삭제</button>
					</div><br>
					</c:if>
					
					
					</c:forEach>
					
					
					
					</c:if>
					
					
					<input multiple="multiple" type="file" name="upfile">

		
					<!-- 파일업로드 -->
				</tr>

				<tr>

					<th colspan="4">내용</th>
				</tr>
				<tr>
					<td colspan="4"><script
							src="//cdn.ckeditor.com/4.13.0/standard/ckeditor.js"></script> <textarea
							name="noticecontent" id="text" required>${ notice.noticecontent }</textarea> <script>
								CKEDITOR.replace('noticecontent');
							</script></td>
				</tr>
			</table>
			
				
				<input type="hidden" value="${ notice.noticeno }" name="noticeno">
				
				<button type="submit" class="insert" onclick="return validate()">완료</button>
             </form>    




			<!-- 이곳에 글내용이들어감 -->


			<!-- 직원들만 수정 삭제 할 수 있음  -->

			<div style="text-align: center; margin-top: 50px;">

				
				


			</div>


		</div>




		<!-- ################################################################################################ -->
		<!-- ################################################################################################ -->
		
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<c:import url="../sidebar.jsp"></c:import>
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<c:import url="../footer.jsp"></c:import>
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a>
	<!-- JAVASCRIPTS -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.backtotop.js"></script>
	<script src="resources/js/jquery.mobilemenu.js"></script>

	<script type="text/javascript">
		
	
    //실시간 파일 삭제 
	function deleteFile(ofilename, rfilename, noticeno){
		
		console.log(ofilename,rfilename);
		
		$.ajax({
			url: "deleteNoticeFile.do",
            data:{
                ofilename: ofilename+",",
                rfilename: rfilename+",",
                noticeno : noticeno
            },success: function(){
                console.log("파일삭제 성공");
            },error: function(request, status, errorData){
					console.log("error code : " + request.status + 
								"\nMesaage : " + request.responseText + 
                                    "\nError : " + errorData);
                    }
			
		});
		
	}
    
    function validate(){
    	
    	if (confirm("정말 수정하시겠습니까?") == false){
    		return false;
    	}else{
    		return ture;
    	}
    
    }
	
	
	$(function(){
		
		$(".fileDiv").children().click(function(){
			$(this).parent().text("");
			
			$(this).css("display","none");
		});
		
		//셀렉트 박스 값 일치시 selected 만들기 

		//공지사항 종류 상태 부분 
		$("select[name=noticetype] option").each(function() {

			if ($(this).val() == '${notice.noticetype}') {
				$(this).attr("selected", "selected");
			}

		});
		
		
	
		//현재 메뉴 굵게 처리  
		$("#sideList li a").each(function() {

			if ($(this).text() == $("#title").text()) {
				console.log($(this).text());
				$(this).css('color', 'red');
				$(this).css('font-weight', 'bold');
			}
		});
		
		
		
		
		
		
	});
	
	</script>

</body>
</html>