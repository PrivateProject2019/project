<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="sidebar one_quarter">
		<!-- ################################################################################################ -->

		<!-- 로그인한 사람의 신분 -->
		<c:set var="identity" value="${ loginMember.identity }" />

		<!-- 로그인한 사람이 학생일때 -->
		<c:if test="${ fn:contains(identity, 'S') }">
			<h6>학사관리</h6>
			<nav class="sdb_holder">
				<ul id="sideList">
					<li><a href="#">수강관리</a>
            <ul>
            	<li><a href="classApplyGuide.do">수강신청안내</a></li>
            	<li><a href="classApplyList.do?deptno=${ loginMember.deptno }">수강신청</a>
            </ul>
            </li>
            <li><a href="#">학점조회</a></li>
            <li><a href="#">출결조회</a></li>
            <li><a href="#">증명서 출력</a>
            <ul>
            	<li><a href="#">졸업증명서 출력</a></li>
            	<li><a href="#">성적증명서 출력</a></li>
            </ul>
            
            </li>
            <li><a href="#">휴/복학신청</a>
            <ul>
            	<li><a href="#">휴학신청</a></li>
            	<li><a href="#">복학신청</a></li>
            	<li><a href="#">신청결과조회</a></li>
            </ul>
            </li>
            <li><a href="#">공지사항</a></li>

				</ul>
			</nav>
			<div class="sdb_holder">
				<h6>학생 정보</h6>
				<address>
					성명 : ${ loginMember.name }<br> 학과 : ${ loginMember.deptname }<br>주소
					: ${ loginMember.address }
				</address>
			</div>
		</c:if>


		<!-- 로그인한 사람이 직원일때 -->
		<c:if test="${ fn:contains(identity, 'E') }">
			<h6>행정관리</h6>
			<nav class="sdb_holder">
				<ul id="sideList">
					<li><a href="classMain.do?currentPage=1">수업관리</a></li>
					<li><a href="studentMain.do?currentPage=1">학생관리</a></li>
					<li><a href="teacherMain.do?currentPage=1">교수관리</a></li>
					<li><a href="empMain.do?currentPage=1">직원관리</a></li>
					<li><a href="deptMain.do?currentPage=1">학과관리</a></li>
					<li><a href="noticeMain.do?currentPage=1">공지사항관리</a></li>
					<li><a href="#">휴/복학처리</a></li>

				</ul>
			</nav>
			<div class="sdb_holder">
				<h6>직원 정보</h6>
				<address>
					성명 : ${ loginMember.name }<br> 소속 : ${ loginMember.belong }<br>주소
					: ${ loginMember.address }
				</address>
			</div>
		</c:if>

		<!-- ################################################################################################ -->
	</div>
</body>
</html>