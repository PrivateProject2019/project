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
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="fl_left">
      <h1><a href="home.do">Colossus</a></h1>
    </div>
    <!-- ################################################################################################ -->
    <nav id="mainav" class="fl_right">
      <ul class="clear">
        <li class="active"><a href="home.do">Home</a></li>
        
        
        <!-- 로그인한 사람의 신분 -->
        <c:set var="identity" value="${ loginMember.identity }"/>
        
         <!-- 로그인한 사람이 학생일때 -->
        <c:if test="${ fn:contains(identity, 'S') }">
         <li><a class="drop" href="#">Menu</a>
          <ul>
            <li><a href="#">수강관리</a>
            <ul>
            	<li><a href="classApplyGuide.do">수강신청안내</a></li>
            	<li><a href="classApplyList.do?deptno=${ loginMember.deptno }">수강신청</a>
            </ul>
            </li>
            <li><a href="pages/full-width.html">학점조회</a></li>
            <li><a href="pages/sidebar-left.html">출결조회</a></li>
            <li><a href="pages/sidebar-right.html">증명서 출력</a>
            <ul>
            	<li><a href="#">졸업증명서 출력</a></li>
            	<li><a href="#">성적증명서 출력</a></li>
            </ul>
            
            </li>
            <li><a href="pages/basic-grid.html">휴/복학신청</a>
            <ul>
            	<li><a href="#">휴학신청</a></li>
            	<li><a href="#">복학신청</a></li>
            	<li><a href="#">신청결과조회</a></li>
            </ul>
            </li>
            <li><a href="pages/basic-grid.html">공지사항</a></li>
          </ul>
        </li>
        </c:if>
        
        
         <!-- 로그인한 사람이 직원일때 -->
        <c:if test="${ fn:contains(identity, 'E') }">
        
        <li><a class="drop" href="#">Menu</a>
          <ul>
            <li><a href="classMain.do?currentPage=1">수업관리</a></li>
            <li><a href="studentMain.do?currentPage=1">학생관리</a></li>
            <li><a href="teacherMain.do?currentPage=1">교수관리</a></li>
            <li><a href="empMain.do?currentPage=1">직원관리</a></li>
            <li><a href="deptMain.do?currentPage=1">학과관리</a></li>
             <li><a href="noticeMain.do?currentPage=1">공지사항관리</a></li>
              <li><a href="pages/basic-grid.html">휴/복학처리</a></li>
          </ul>
        </li>
        
        </c:if>
        
        <!-- 로그인한 사람이 교수일때 -->
         <c:if test="${ fn:contains(identity, 'T') }">
        
        <li><a class="drop" href="#">Menu</a>
          <ul>
            <li><a href="pages/gallery.html">출결관리</a></li>
            <li><a href="pages/full-width.html">학점평가</a></li>
            
          </ul>
        </li>
        
        </c:if>
        
        <c:if test="${ loginMember == null }">
        <li><a href="loginForm.do">login</a></li> <!-- 로그인세션이 존재할때만 보이게 설정  -->
        </c:if>
        <c:if test="${ loginMember != null }">
        <li><a href="logout.do">logout</a></li>
        <li>${ loginMember.name }님</li>
        </c:if>
      </ul>
    </nav>
    <!-- ################################################################################################ -->
  </header>
</div>
</body>
</html>