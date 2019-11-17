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
<link href="resources/css/layout.css" rel="stylesheet" type="text/css"
	media="all">

<!-- 개인 CSS -->
<style type="text/css">
* {
	font-family: initial !important;
}

table {
	text-align: center;
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

button:nth-child(2) {
	background: #639fff;
}

button:nth-child(1) {
	background: red;
}

#paging {
	
}

#paging a {
	color: black;
	margin: 5px;
}

#searchDiv * {
	display: inline-block;
	margin: -4px;
	margin-top: 30px;
}

#searchBar {
	font-size: 15pt;
	border-style: none;
	border-bottom: 1px solid black;
	width: 350px;
	background-color: #ffffff;
}

#culum {
	text-align: left
}

table a {
	font-weight: bold;
	color: black;
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

			<h1 class="htitle">수업관리</h1>

			<h1>수업 상세 조회</h1>


			<table>
				<tr>
					<th>No.</th>
					<td>${classInfo.classno }</td>
					<th>학과</th>
					<td>${classInfo.deptname }</td>
				</tr>
				<tr>
					<th>강의이름</th>
					<td>${classInfo.classname }</td>
					<th>강의종류</th>
					<td>${classInfo.classtype }</td>
				</tr>
				<tr>
					<th>수업시간</th>
					<td>${ classInfo.classday }${classInfo.classstart }교시~${classInfo.classend }교시</td>
					<th>담당교수</th>
					<td>${ classInfo.teachername }</td>

				</tr>
				<tr>
					<th>정원</th>
					<td>${ classInfo.admission }</td>
					<th>학점</th>
					<td>${ classInfo.score }</td>
				</tr>
				<tr>
					<th>강의장소</th>
					<td>${ classInfo.place }</td>
					<th>학기</th>
					<td>${ classInfo.semester }학기</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3"><c:if test="${ notice.ofilename eq ' '}">
					첨부 파일없음 
					</c:if> <c:if test="${ notice.ofilename ne '' }">

							<c:forEach items="${ fileList }" var="file">

								<c:if test="${ file.ofilename ne '' }">
									<a
										href="cfdown.do?ofile=${ file.ofilename }&rfile=${file.rfilename}">${file.ofilename}</a>
									<br>
								</c:if>


							</c:forEach>

						</c:if></td>

					<!-- 파일다운로드 -->
				</tr>
				<tr>
					<th colspan="4">커리큘럼</th>
				</tr>
				<tr>
					<td colspan="4" id="culum">${ classInfo.curriculum }</td>
				</tr>

			</table>







			<!-- 이곳에 글내용이들어감 -->


			<!-- 직원들만 수정 삭제 할 수 있음  -->


			<c:if test="${ fn:contains(loginMember.identity, 'E') }">
				<div style="text-align: center; margin-top: 50px;">


					<button type="button" id="delete"
						onclick="return confirmFunction()">삭제</button>
					<button type="button" id="update"
						onclick="location.href='updateClassForm.do?classno=${classInfo.classno}'">수정</button>


				</div>
			</c:if>

		</div>




		<!-- ################################################################################################ -->
		<!-- ################################################################################################ -->

		<!-- ################################################################################################ -->
		<!-- ################################################################################################ -->
		<!-- ################################################################################################ -->
		<c:import url="../sidebar.jsp"></c:import> <!-- ################################################################################################ -->
		<!-- ################################################################################################ -->
		<!-- ################################################################################################ -->
		<c:import url="../footer.jsp"></c:import> <!-- ################################################################################################ -->
		<!-- ################################################################################################ -->
		<!-- ################################################################################################ -->
		<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a> <!-- JAVASCRIPTS -->
		<script src="resources/js/jquery.min.js"></script> <script
			src="resources/js/jquery.backtotop.js"></script> <script
			src="resources/js/jquery.mobilemenu.js"></script> <script
			type="text/javascript">
				
			</script>
			
			 <script type="text/javascript">
				function confirmFunction() {

					if (confirm("정말 삭제하시겠습니까?")) {
						location.href = 'deleteClass.do?classno=${classInfo.classno}';
					}

				}

				//현재 메뉴 굵게 처리  
				$("#sideList li a").each(function() {

					if ($(this).text() == $(".htitle").text()) {
						console.log($(this).text());
						$(this).css('color', 'red');
						$(this).css('font-weight', 'bold');
					}
				});
			</script>
</body>
</html>