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

#delete {
	background: red;
}

#update {
	background: #639fff;
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

			<h1 class="htitle">공지사항관리</h1>

			<h1>공지사항 조회</h1>


			<table>
				<tr>
					<th>제목</th>
					<td>${ notice.title }</td>
					<th>글쓴이</th>
					<td>${ notice.empname }</td>
				</tr>
				<tr>
					<th>작성날짜</th>
					<td colspan="3">${ notice.noticedate }</td>

				</tr>

				<tr>
					<th>첨부파일</th>
					<td colspan="3"><c:if test="${ notice.ofilename == ','}">
					첨부 파일없음 
					</c:if> <c:if test="${ notice.ofilename != '' }">

							<c:forEach items="${ fileList }" var="file">

								<c:if test="${ file.ofilename ne '' }">
									<a
										href="nfdown.do?ofile=${ file.ofilename }&rfile=${file.rfilename}">${file.ofilename}</a>
									<br>
								</c:if>


							</c:forEach>

						</c:if></td>

					<!-- 파일다운로드 -->
				</tr>

			</table>


			<p id="title">${ notice.title }</p>
			<!-- 이곳에 제목이들어감  -->

			<p id="textContent">${ notice.noticecontent }</p>



			<!-- 이곳에 글내용이들어감 -->


			<!-- 직원들만 수정 삭제 할 수 있음  -->


			<c:if test="${ fn:contains(loginMember.identity, 'E') }">
				<div style="text-align: center; margin-top: 50px;">


					<button type="button" id="delete" onclick="return confirmFunction()">삭제</button>
					<button type="button" id="update"
						onclick="location.href='updateNoticeForm.do?noticeno=${notice.noticeno}'">수정</button>


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
			
				function confirmFunction(){
					
					if(confirm("정말 삭제하시겠습니까?")){
						location.href='deleteNotice.do?noticeno=${notice.noticeno}';
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