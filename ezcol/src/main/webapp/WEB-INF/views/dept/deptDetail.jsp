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
<title>학과정보</title>
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

th:nth-child(1){
 width:180px;
}



button:nth-child(2) {
	background: #639fff;
}

button:nth-child(1) {
	background: red;
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

input {
	border-style: none;
	background: #f7f7f7;
	display: inline-block
}

#addressSearch {
	height: 30px;
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
	<div class="wrapper row3">
		<main class="container clear"> <!-- main body --> <!-- ################################################################################################ -->
		<div class="content three_quarter first" id="content">
			<!-- ################################################################################################ -->


			<div class="scrollable">
				<h1 id="title">학과관리</h1>
				<h1>학과 상세 조회</h1>
				
				<table>
					<tr>
						<th>No.</th>
						<td>${dept.deptno }</td>
						<th>학과명</th>
						<td>${ dept.deptname }</td>
					</tr>
					<tr>
						<th>학과장 코드</th>
						<td>${dept.teacherno }</td>
						<th>정원</th>
						<td>${dept.admission }</td>
					</tr>



				</table>


				<div style="text-align: center; margin-top: 50px;">

					<button type="button" onclick="confirmFunction();">삭제</button>
					<button type="button"
						onclick="location.href='updateDeptForm.do?deptno=${dept.deptno}'">수정</button>


				</div>



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




	<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a>
	<!-- JAVASCRIPTS -->

	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.backtotop.js"></script>
	<script src="resources/js/jquery.mobilemenu.js"></script>
	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>




	<script type="text/javascript">
		function confirmFunction() {

			if (confirm("정말 삭제하시겠습니까?") == true) {

				location.href = "deleteDept.do?deptno=${dept.deptno}";

			} else {
				return false;
			}

		}

		//현재 메뉴 굵게 처리  
		$("#sideList li a").each(function() {

			if ($(this).text() == $("#title").text()) {
				console.log($(this).text());
				$(this).css('color', 'red');
				$(this).css('font-weight', 'bold');
			}
		});
	</script>

</body>
</html>