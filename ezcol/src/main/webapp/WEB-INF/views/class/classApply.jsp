<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.kh.ezcol.classInfo.model.vo.ClassInfo"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	List<ClassInfo> list = (List<ClassInfo>) request.getAttribute("list");
	List<ClassInfo> list2 = (List<ClassInfo>) request.getAttribute("list2");
%>
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
<link href="resources/css/layout.css" rel="stylesheet" type="text/css"
	media="all">

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
	background: red !important;
	color: white;
	cursor: pointer;
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

td:nth-child(9n) {
	display: none;
}

.tr :hover, .tr:hover * {
	background: #ececec;
	font-weight: bold;
}

#sort a{
 color:  #639fff;
 font-weight: bold;
}

#scroll {
 -ms-overflow-style: none; 
}

::-webkit-scrollbar {
display:none;
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
	<div class="wrapper row3" style="margin-left: 50px;">
		<main class="container-fluid clear"> <!-- main body --> <!-- ################################################################################################ -->
		<div class="content " id="content">
			<!-- ################################################################################################ -->

			<div class="scrollable" id="searchClass">
				
				
			
					
				<h1 id="title">수강신청</h1>
				
				<div style="float: left; margin-top: 20px;" id="sort">
					<a href="classApplyList.do?deptno=${ loginMember.deptno }&studentno=${ loginMember.studentno }&keyword='전공필수'">전공필수</a>
					<a href="classApplyList.do?deptno=${ loginMember.deptno }&studentno=${ loginMember.studentno }&keyword='전공선택'">전공선택</a>
					<a href="classApplyList.do?deptno=${ loginMember.deptno }&studentno=${ loginMember.studentno }&keyword='교양필수'">교양필수</a>
					<a href="classApplyList.do?deptno=${ loginMember.deptno }&studentno=${ loginMember.studentno }&keyword='교양선택'">교양선택</a>
				</div>
				
					<div style="clear: both;"></div>
				
				<br>
				<div style="overflow: scroll; height: 300px;" id="scroll">
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


							<%-- <% for(ClassInfo classInfo : list){ %>
						<% for(ClassInfo classInfo2 : list2){ %>
						
						<% if(!classInfo.getClassno().equals(classInfo2.getClassno())){ %>
						<h1><%= classInfo.getClassno() %></h1>
						
						<% break; %>
						
						<% } %>
						
						<% } %>
						<% } %>  --%>


							
							<c:forEach var="classInfo" items="${ list }">

								<%--  <c:set var="loopFlag" value="true"/> --%>
								
							<!-- 상단 리스트가 하단 리스트와 모두 같지않을때 (즉, 하단리스트의 길이만큼의 갯수가 일치하지않을때)만 출력한다  -->
								
								 <c:set var="count" value="0" />

								<c:forEach var="breakdown" items="${list2}">

									<c:if test="${ classInfo.classno ne breakdown.classno }">
									
									<c:set var="count" value="${ count+1 }" />
									
									</c:if>

									<c:if test="${ classInfo.classno eq breakdown.classno }">
									
									<c:set var="loopFlag" value="false" />
									
									</c:if>
										
									<c:if
										test="${ fn:length(list2) == count }">


										<tr class="tr">

											<td
												onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
												style="cursor: pointer">${ classInfo.classno }</td>
											<td
												onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
												style="cursor: pointer">${classInfo.classtype}</td>
											<td
												onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
												style="cursor: pointer">${classInfo.classname}</td>
											<td
												onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
												style="cursor: pointer">${classInfo.teachername}</td>
											<td
												onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
												style="cursor: pointer">${classInfo.place}</td>
											<td
												onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
												style="cursor: pointer">${classInfo.score}</td>
											<td
												onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
												style="cursor: pointer">${classInfo.classday}
												${classInfo.classstart}교시~${classInfo.classend }교시</td>
											<td align="center"><button type="button" class="apply"
													onclick="validate('${classInfo.classno}')">신청</button>
											<td>
										</tr>

										<%-- <c:set var="loopFlag" value="false" />
									 <c:set var="loopFlag" value="false" />  --%>

									</c:if>


								</c:forEach>

							</c:forEach>

						</tbody>
					</table>
				</div>

			</div>

			<img src="resources/images/avatar.png"
				style="float: left; padding-right: 10px;">
			<h1 style="font-size: 19pt; float: left;">수강신청 내역</h1>
			<p>학점</p>
			<p id="hakjum">${addAll}</p>
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
							<th>신청</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="classInfo" items="${ list2 }">

							<tr class="tr">

								<td
									onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${ classInfo.classno }</td>
								<td
									onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.classtype}</td>
								<td
									onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.classname}</td>
								<td
									onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.teachername}</td>
								<td
									onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.place}</td>
								<td
									onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.score}</td>
								<td
									onclick="location.href='detailClass.do?classno=${classInfo.classno}'"
									style="cursor: pointer">${classInfo.classday}
									${classInfo.classstart}교시~${classInfo.classend }교시</td>
								<td align="center"><button type="button" class="cancel"
										onclick="location.href='classCancel.do?studentno=${loginMember.studentno}&classno=${classInfo.classno }&deptno=${loginMember.deptno }'">취소</button>
								<td>
							</tr>

						</c:forEach>

					</tbody>
				</table>
			</div>







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


	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	
	<br><br>
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

			//현재 메뉴 굵게 처리  
			$("#sideList li a").each(function() {

				if ($(this).text() == $("#title").text()) {
					console.log($(this).text());
					$(this).css('color', 'red');
					$(this).css('font-weight', 'bold');
				}
			});

		});

		function validate(classno) {

			console.log(classno);

			$
					.ajax({
						url : "breakDownValidate.do",
						data : {
							classno : classno,
							studentno : '${loginMember.studentno}',
							deptno : '${loginMember.deptno}'
						},
						success : function(message) {
							if (message == '수강신청이 완료되었습니다.') {
								alert(message);
								console.log("컨트롤러로 이동함");

								location.href = "classApply.do?studentno=${loginMember.studentno}&deptno=${loginMember.deptno}&classno="
										+ classno;

							} else {
								alert(message);
							}

						}

					});
		}

		/* function classApply(classno, studentno, deptno){
			console.log("classApply run....");
			console.log("classno : " + classno);
			console.log("studnetno : " + studentno);
			console.log("deptno : " + deptno);
			
			$.ajax({
				url : "classApply.do",
				data : {
					classno : classno,
					studentno : studentno,
					deptno : deptno
				},
				success : function(message) {
					
					console.log("수강신청완료");

				}

			});
			
		} */
	</script>
</body>
</html>