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

.tr :hover, .tr:hover * {
	background: #ececec;
	font-weight: bold;
}

td {
	
}

button {
	float: right;
	margin-right: 30px;
	border-style: none;
	width: 120px;
	height: 35px;
	font-weight: bold;
	color: white;
	background: #639fff;
	cursor: pointer;
}

span a {

font-weight: bold;
color: #639fff;
font-size: 13pt;
margin-right: 20px;

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
			
				<c:if test="${ loginMember.identity eq 'E' }">
				<h1 id="title">공지사항관리</h1>
				</c:if>
				
				<c:if test="${ loginMember.identity eq 'S' }">
				<h1 id="title">공지사항</h1>
				</c:if>
				
				<h1>공지사항 전체 조회</h1>
				
				<span><a href="noticeMain.do?currentPage=1">전체</a></span>
				<span><a href="noticeType.do?currentPage=1&type=공지사항">공지사항</a></span>
				<span><a href="noticeType.do?currentPage=1&type=휴/복학안내">휴/복학 안내</a></span>
				<span><a href="noticeType.do?currentPage=1&type=수강신청안내">수강신청안내</a></span>
				
				<table style="margin-top: 20px;">
					<thead>
						<tr>
							<th>구분</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성날짜</th>



						</tr>
					</thead>
					<tbody>
						<!-- <tr>
							<td><a href="#">E0001</a></td>
							<td>컴퓨터공학과</td>
							<td>김효진</td>
							<td>전공필수</td>

						</tr> -->

						
						<c:forEach var="notice" items="${ list }">

							<tr onclick="location.href='detailNotice.do?noticeno=${notice.noticeno}'" style="cursor: pointer"
								class="tr">

								<td>${ notice.noticetype }</td>
								<td>${ notice.title }</td>
								<td>${ notice.empname }</td>
								<td>${ notice.noticedate}</td>

							</tr>

						</c:forEach>
						





					</tbody>
				</table>

				<c:if test="${ loginMember.identity =='E'}">

				<div style="text-align: center; margin-top: 50px;">


					<button type="button" onclick="location.href='insertNoticeForm.do'">공지사항 추가</button>


				</div>
				</c:if>

				<div style="text-align: center; margin-top: 50px;">




					<div id="paging">

						 
					
						
						 <c:if test="${ kind eq 'all' }"> 
						 
						 
						 <c:if test="${ paging.startPage != 1 }">
						 
						 <a href="noticeMain.do?currentPage=${paging.startPage - 1}">이전</a>
						 
						 </c:if>
						 
						 
						<c:forEach var="num" begin="${ paging.startPage }"
							end="${ paging.endPage }">

							<a href="noticeMain.do?currentPage=${num}">${num}</a>

						</c:forEach>
						
						<c:if test="${ paging.endPage != paging.maxPage }">
							
						<a href="noticeMain.do?currentPage=${paging.endPage + 1}">다음</a>
							
						</c:if>
						
						
						
						 </c:if> 
						 
						 
					
						
						
						
						
						<c:if test="${ kind eq 'sort' }">
						
						
						<c:if test="${ paging.startPage != 1 }">
						 
						 <a href="noticeType.do?currentPage=${paging.startPage - 1}&type=${type}">이전</a>
						 
						 </c:if>
						
						<c:forEach var="num" begin="${ paging.startPage }"
							end="${ paging.endPage }">

							<a href="noticeType.do?currentPage=${num}&type=${ type }">${num}</a>

						</c:forEach>
						
						
						<c:if test="${ paging.endPage != paging.maxPage }">
							
						<a href="noticeType.do?currentPage=${paging.endPage + 1}&type=${type}">다음</a>
							
						</c:if>
						
						</c:if>
						
						
						
					</div>

				</div>



				<!-- <div style="text-align: center;">
					<div id="searchDiv">
						<div style="margin: 0 auto;">
							<form action="searchNotice.do">
							<input type="text" placeholder="검색" id="searchBar" name="keyword"> 
							<input type="hidden" value="1" name="currentPage">
							
							
							<a	href="#"><input type="image" src="resources/images/search.PNG"
								style="height: 30px; border-bottom: 1px solid black;"></a>
							</form>	
						</div>
	
					</div>
				</div> -->

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
		
	$(function(){
		
		
		//현재 페이지 굵게 처리 
		$("#paging a").each(function(){
			
			if($(this).text() == Number('${paging.currentPage}')){
				console.log($(this).text());
				$(this).css('color','red');
				$(this).css('font-weight','bold');
			}
		});
		
		//현재 메뉴 굵게 처리  
		$("#sideList li a").each(function(){
			
			if($(this).text() == $("#title").text()){
				console.log($(this).text());
				$(this).css('color','red');
				$(this).css('font-weight','bold');
			}
		});
		
	});
	
	</script>

</body>
</html>