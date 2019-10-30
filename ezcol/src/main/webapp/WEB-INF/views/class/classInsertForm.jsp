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

button:nth-child(1) {
	background: #639fff;
}

input {
	border-style: none;
	background: #f7f7f7;
	display: inline-block
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

#teachername, #deptname {
	color: red;
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


			<div class="scrollable">
				<h1 id="title">수업관리</h1>
				<h1>수업 추가</h1>
				<form action="insertClass.do" method="post"
					enctype="multipart/form-data">
					<table>
						<tr>
							<th>No.</th>
							<td><input type="text" placeholder="Ex)자동생성됨"
								style="float: left;" readonly required></td>
							<th>학과</th>
							<td><input type="text" placeholder="학과코드" name="deptno"
								maxlength="7" size="10" style="float: left" required>
								<div id="deptname"></div></td>
						</tr>
						<tr>
							<th>강의이름</th>
							<td><input type="text" size="30" required name="classname"></td>
							<th>강의종류</th>
							<td><select name="classtype" id="" required>
									<option value="전공필수">전공필수</option>
									<option value="전공선택">전공선택</option>
									<option value="교양필수">교양필수</option>
									<option value="교양선택">교양선택</option>
							</select></td>
						</tr>
						<tr>
							<th>수업시간</th>
							<td><select name="classday" id="" style="float: left;" required>
									<option value="월요일">월</option>
									<option value="화요일">화</option>
									<option value="수요일">수</option>
									<option value="목요일">목</option>
									<option value="금요일">금</option>

							</select> <input type="number" name="classstart" min="1" max="8"
								style="padding-bottom: 6px;" required>교시 ~ <input
								type="number" name="classend" min="2" max="9"
								style="padding-bottom: 6px;" required>교시</td>


							<th>담당교수</th>
							<td><input type="text" placeholder="교수코드"
								style="float: left;" name="teacherno" maxlength="7" size="10" required>

								<div id="teachername"></div></td>

						</tr>
						<tr>
							<th>정원</th>
							<td><input type="number" name="admission" min="10" max="100"
								required style="float: left;"></td>
							
							
							<th>학점</th>
							<td><input type="number" name="score" maxlength="2"
								style="float: left;" required></td>
						</tr>
						<tr>
							<th colspan="1">강의장소</th>
							<td colspan="1"><input type="text" name="place" size="30" required></td>
							<th>학기</th>
							<td>
							<select name="semester" required="required">
							
								<option value="1">1학기</option>
								<option value="2">2학기</option>
								<option value="계절">계절학기</option>
							</select>
							
							</td>
						</tr>
						<tr>
							<th colspan="4">커리큘럼</th>
						</tr>
						<tr>
							<td colspan="4" id="culum"><script
									src="//cdn.ckeditor.com/4.13.0/standard/ckeditor.js"></script>
								<textarea name="curriculum" id="text" required></textarea> <script>
									CKEDITOR.replace('curriculum');
								</script></td>

						</tr>

					</table>

					<br> <input type="file" multiple="multiple" name="upfile">

					<div style="text-align: center; margin-top: 50px;">


						<button type="submit" id="button">완료</button>


					</div>

				</form>




			</div>

			<!-- ################################################################################################ -->
		</div>
		<!-- ################################################################################################ -->
		<!-- ################################################################################################ -->

		<c:import url="../sidebar.jsp"></c:import> <!-- ################################################################################################ -->
		<!-- / main body -->
		<div class="clear"></div>
		</main>
	</div>
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<c:import url="../footer.jsp"></c:import>
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->

	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a>
	<!-- JAVASCRIPTS -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.backtotop.js"></script>
	<script src="resources/js/jquery.mobilemenu.js"></script>

	<script type="text/javascript">
		$(function() {

			//현재 페이지 굵게 처리 
			$("#paging a").each(function() {

				if ($(this).text() == Number('${paging.currentPage}')) {
					console.log($(this).text());
					$(this).css('color', 'red');
					$(this).css('font-weight', 'bold');
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

			//학과이름 불러오기 
			$("input[name=deptno]").keyup(
					function() {

						$.ajax({
							url : "getDeptName.do",
							data : {
								deptno : $("input[name=deptno]").val()
							},
							success : function(result) {
								console.log(result);
								$("#deptname").text(result);
							},
							error : function(request, status, errorData) {
								console.log("error code : " + request.status
										+ "\nMesaage : " + request.responseText
										+ "\nError : " + errorData);
							}

						});

					});

			//교수 이름 불러오기 
			$("input[name=teacherno]").keyup(
					function() {

						$.ajax({
							url : "getTeacherName.do",
							data : {
								teacherno : $("input[name=teacherno]").val()
							},
							success : function(result) {
								console.log(result);
								$("#teachername").text(result);
							},
							error : function(request, status, errorData) {
								console.log("error code : " + request.status
										+ "\nMesaage : " + request.responseText
										+ "\nError : " + errorData);
							}

						});

					});

		});
	</script>

</body>
</html>