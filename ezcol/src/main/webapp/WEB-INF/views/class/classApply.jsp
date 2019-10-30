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
<title>Enrolment</title>
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

#content {
	margin-left: 40px;
	margin-right: 0px;
	width: 1500px;
	float: left;
}

#sideBar {
	width: 200px;
	margin-left: 90px;
	float: left;
	margin-top: 60px;
}

#searchClass {
	margin-top: 60px;
	margin-bottom: 50px;
}

#hakjum {
	color: red;
}

.apply {
	width: 120px;
	border-style: none;
	background: #639fff !important;
	color: white;
	cursor: pointer;
}

.cancel {
	width: 120px;
	border-style: none;
	background: red;
	color: white;
}

p {
	float: right;
	padding: 3px;
	color: black;
}

h1 {
	margin-bottom: 0px !important;
}

table {
	text-align: center;
}



#paging a {
	margin-left: 5px;
}

.tr :hover, .tr:hover *   {
	background: #ececec;
	font-weight: bold;
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
		<main class="container-fluid clear"> <!-- main body --> <!-- ################################################################################################ -->
		<div class="content " id="content">
			<!-- ################################################################################################ -->

			<div class="scrollable" id="searchClass">
				<div style="overflow: scroll; height: 300px;">
					<table>
						<thead>
							<tr>
								<th>No.</th>
								<th>구분</th>
								<th>과목명</th>
								<th>담당교수</th>
								<th>강의장</th>
								<th>학점</th>
								<th>강의시간</th>
								<th>신청</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="classInfo" items="${ list }">

								<tr
									 class="tr">

									<td onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${ classInfo.classno }</td>
									<td onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.classtype}</td>
									<td onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.classname}</td>
									<td onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.teachername}</td>
									<td onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.place}</td>
									<td onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.score}</td>
									<td onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.classday}
										${classInfo.classstart}교시~${classInfo.classend }교시</td>
									<td align="center"><button type="button" class="apply" onclick="validate('${classInfo.classno}')">신청</button>
									<td>
								</tr>

							</c:forEach>
							
						</tbody>
					</table>
					</div>
				</div>

				<div style="text-align: center;">
					<div id="paging">




						<c:if test="${ type eq 'all' }">
							<c:forEach var="num" begin="${ paging.startPage }"
								end="${ paging.endPage }">

								<a href="noticeMain.do?currentPage=${num}">${num}</a>

							</c:forEach>
						</c:if>







						<c:if test="${ type eq 'search' }">
							<c:forEach var="num" begin="${ paging.startPage }"
								end="${ paging.endPage }">

								<a
									href="searchNotice.do?currentPage=${num}&keyword=${ keyword }">${num}</a>

							</c:forEach>

						</c:if>



					</div>

				</div>
				<img src="resources/images/avatar.png"
					style="float: left; padding-right: 10px;">
				<h1 style="font-size: 19pt; float: left;">수강신청 내역</h1>
				<p>학점</p>
				<p id="hakjum">2</p>
				<p>신청학점 :</p>
				<div class="scrollable">
					<table id="resultClass">
						<thead>
							<tr>
								<th>No.</th>
								<th>구분</th>
								<th>과목명</th>
								<th>담당교수</th>
								<th>강의장</th>
								<th>학점</th>
								<th>강의시간</th>

								<th>취소</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><a href="#">1</a></td>
								<td>Value 2</td>
								<td>Value 3</td>
								<td>Value 4</td>
								<td></td>
								<td></td>
								<td></td>
								<td align="center"><button type="button" class="cancel">취소</button></td>
							</tr>
							<tr>
								<td>Value 5</td>
								<td>Value 6</td>
								<td>Value 7</td>
								<td><a href="#">Value 8</a></td>
								<td></td>
								<td></td>
								<td></td>

							</tr>
							<tr>
								<td>Value 9</td>
								<td>Value 10</td>
								<td>Value 11</td>
								<td>Value 12</td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>Value 13</td>
								<td><a href="#">Value 14</a></td>
								<td>Value 15</td>
								<td>Value 16</td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>

				<!-- ################################################################################################ -->
			</div>
			<!-- ################################################################################################ -->
			<!-- ################################################################################################ -->
			<c:import url="../sidebar.jsp"></c:import>
			<!-- ################################################################################################ -->
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
	
	function validate(classno){
        
        console.log(classno);
        
        $.ajax({
            url: "breakDownValidate.do",
            data:{
                classno:classno,
                studentno:'${loginMember.studentno}'  
            },success: function(message){
                
                
            }
            
            
        });
    }
		
	</script>

</body>
</html>