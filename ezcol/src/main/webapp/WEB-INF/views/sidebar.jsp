<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="sidebar one_quarter">
		<!-- ################################################################################################ -->

		<jsp:useBean id="toDay" class="java.util.Date" />
		<fmt:formatDate value='${toDay}' pattern='yyyy' var="nowDate" />


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
							<li><a
								href="#" onclick="classApplyOnOffValidate()">수강신청</a>
							<li><a
								href="timeTable.do?deptno=${ loginMember.deptno }&studentno=${loginMember.studentno}">시간표
									조회</a></li>
						</ul></li>
					<li><a
						href="gradeView.do?studentno=${loginMember.studentno }">학점조회</a></li>
					<li><a href="attendanceMain.do?studentno=${ loginMember.studentno }">출결조회</a></li>
					<li><a href="#">증명서 출력</a>
						<ul>
							<li><a href="#">졸업증명서 출력</a></li>
							<li><a href="gradePrint.do?studentno=${ loginMember.studentno }&deptno=${loginMember.deptno}&teacherno=${loginMember.teacherno}">성적증명서 출력</a></li>
						</ul></li>
					<li><a href="#">휴/복학신청</a>
						<ul>
							<li><a href="absenceInsertForm.do">휴학신청</a></li>
							<li><a href="recallInsertForm.do">복학신청</a></li>
							<li><a href="absenceMain.do?studentno=${loginMember.studentno}">신청결과조회</a></li>
						</ul></li>
					<li><a href="noticeMain.do?currentPage=1">공지사항</a></li>
					<li><a href="articleMain.do?currentPage=1">학교소식</a></li>
					<li><a href="#" onclick="surveyOnOffValidate()">설문지 작성</a></li>

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
					<li><a href="recallProcessMain.do?currentPage=1">휴/복학처리</a></li>
					<li><a href="articleMain.do?currentPage=1">학교소식관리</a></li>
					<li><a href="surveyManageMain.do?currentPage=1">설문결과조회</a></li>
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
		
		
		 <!-- 로그인한 사람이 교수일때 -->
         <c:if test="${ fn:contains(identity, 'T') }">
        
        <h6>행정관리</h6>
			<nav class="sdb_holder">
				<ul id="sideList">
					<li><a href="attendanceManageMain.do?teacherno=${ loginMember.teacherno }">출결관리</a></li>
					<li><a href="gradeManageMain.do?teacherno=${ loginMember.teacherno }">학점평가</a></li>
				

				</ul>
			</nav>
			<div class="sdb_holder">
				<h6>교수 정보</h6>
				<address>
					성명 : ${ loginMember.name }<br> 학과 : ${ loginMember.deptname }<br>주소
					: ${ loginMember.address }
				</address>
			</div>
        
        </c:if>

		<!-- ################################################################################################ -->
	</div>
	
	
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.backtotop.js"></script>
	<script src="resources/js/jquery.mobilemenu.js"></script>

	<script type="text/javascript">
		
	<c:if test="${ identity eq 'S'}">
	
	function classApplyOnOffValidate(){
	
		
		//classApplyList.do?deptno=${ loginMember.deptno }&studentno=${ loginMember.studentno }
		
		 $.ajax({
             url: "classApplyOnOffValidate.do",
             success: function(result){
                 console.log(result);
                 
                 if(result == '수강신청 기간이 아닙니다.'){
                	 alert(result);
                 }else{
                	 location.href='classApplyList.do?deptno=${ loginMember.deptno }&studentno=${ loginMember.studentno }';
                 }
                 
                 
                 
             },error: function(request, status, errorData){
				console.log("error code : " + request.status + 
							"\nMesaage : " + request.responseText + 
                             "\nError : " + errorData);
             }
             
             
             
         });
		
		
		
	}
	
	
	function surveyOnOffValidate() {
		
		$.ajax({
            url: "surveyOnOffValidate.do",
            success: function(result){
                console.log(result);
                
                if(result == '설문기간이 아닙니다.'){
               	 alert(result);
                }else{
               	 location.href='surveyMain.do?studentno=${ loginMember.studentno }';
                }
                
                
                
            },error: function(request, status, errorData){
				console.log("error code : " + request.status + 
							"\nMesaage : " + request.responseText + 
                            "\nError : " + errorData);
            }
            
            
            
        });
		
	}
	
	</c:if>
	
	</script>
	
</body>
</html>