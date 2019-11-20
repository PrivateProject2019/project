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
<title>공지사항</title>
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

			<h1>공지사항</h1>

			<h1>공지사항 작성</h1>
			
			<form action="insertNotice.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
			<table>
				<tr>
					<th>제목</th>
					<td><input type="text" size="30" name="title" required="required"></td>


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
					<td colspan="3"><input multiple="multiple" type="file" name="upfile">

		
					<!-- 파일업로드 -->
				</tr>

				<tr>

					<th colspan="4">내용</th>
				</tr>
				<tr>
					<td colspan="4"><script
							src="//cdn.ckeditor.com/4.13.0/standard/ckeditor.js"></script> <textarea
							name="noticecontent" id="text" required></textarea> <script>
								CKEDITOR.replace('noticecontent');
							</script></td>
				</tr>
			</table>
			
				<!-- 로그인 사용자의 사번 보내기 -->
				<input type="hidden" value="${ loginMember.empno }" name="empno">
				
				<button type="submit" class="insert">완료</button>
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
		
	</script>

</body>
</html>