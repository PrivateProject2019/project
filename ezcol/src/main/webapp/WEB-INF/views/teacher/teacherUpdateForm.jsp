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
<title>교수정보</title>
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
	display: inline-block;
	float: left;
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

#errorMessage {
	float: left;
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
				<h1 id="title">교수관리</h1>
				<h1>교수 정보 입력</h1>
				<form action="updateTeacher.do" method="post">
				
				<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<table>
						<tr>
							<th>No.</th>
							<td><input type="text" name="teacherno"
								value="${ teacher.teacherno }" readonly size="25"></td>
							<th>학과코드</th>

							<td><input type="text" name="deptno"
								value="${ teacher.deptno }" required readonly></td>
						</tr>
						<tr>
							<th>교수이름</th>
							<td><input type="text" size="15" name="name"
								value="${ teacher.name }" required></td>
							<th>재직상태</th>
							<td><select name="status" required>

									<option value="재직" selected>재직</option>
									<option value="휴직">휴직</option>
							</select></td>
						</tr>
						<tr>
							<th>주민등록번호</th>
							<td><input type="text" name="ssn" value="${ teacher.ssn }"></td>
							<th>이메일</th>
							<td><input type="email" size="23" name="email"
								value="${ teacher.email }" required></td>

						</tr>
						<tr>


						</tr>

						<tr>
							<th>전화번호</th>
							<td><input type="text" name="phone" required
								value="${ teacher.phone }"></td>
							<th>비상연락망</th>
							<td><input type="text" name="emergency"
								value="${teacher.emergency }" required></td>
						</tr>
						<tr>



							<th rowspan="">주소</th>
							<td colspan="3">

								<p style="margin: 0" id="addressP"></p> <!-- 서블렛에 보낼 주소값 --> <input
								type="text" name="address" value="${teacher.address }" readonly>
							</td>


						</tr>

						<tr>
							<th>우편번호</th>
							<td id="postcode" style="border-right-style: none;" colspan="2"><input
								type="text" name="post" value="${teacher.post }" readonly></td>
							<!-- 서블렛에 보낼 우편번호값 -->



							<td colspan="2" style="border-left-style: none;"><button
									type="button" id="addressSearch" onclick="addressApi()">주소검색</button></td>
						</tr>

						<tr>
							<th>상세주소</th>
							<td colspan="3"><input type="text" name="detailaddress"
								size="60" value="${ teacher.detailaddress }" required></td>

						</tr>


					</table>


					<div id="errorMessage"></div>
					<div style="text-align: center; margin-top: 50px;">


						<button type="submit" onclick="return validate()">수정</button>


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
	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>




	<script type="text/javascript">
		function addressApi() {

			console.log("gkdl");

			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var addr = ''; // 주소 변수
							var extraAddr = ''; // 참고항목 변수

							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								addr = data.roadAddress;
							} else { // 사용자가 지번 주소를 선택했을 경우(J)
								addr = data.jibunAddress;
							}

							// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
							if (data.userSelectedType === 'R') {
								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								if (data.bname !== ''
										&& /[동|로|가]$/g.test(data.bname)) {
									extraAddr += data.bname;
								}
								// 건물명이 있고, 공동주택일 경우 추가한다.
								if (data.buildingName !== ''
										&& data.apartment === 'Y') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraAddr !== '') {
									extraAddr = ' (' + extraAddr + ')';
								}
								// 조합된 참고항목을 해당 필드에 넣는다.
								/* document.getElementById("sample6_extraAddress").value = extraAddr;*/

							} else {
								/* document.getElementById("sample6_extraAddress").value = '';*/
							}

							/*$("#addressP").html(addr);
							$("#post").html(data.zonecode);*/

							$("input[name=post]").val(data.zonecode);
							$("input[name=address]").val(addr);

							$("input[name=detailaddress]").focus();

						}
					}).open();
		}

		function validate() {

			console.log($("input[name=ssn]").val());

			if (confirm("정말 수정하시겠습니까?") == false) {
				return false;
			} else if ($("input[name=ssn]").val().indexOf("-") == -1) {
				$("#errorMessage").text("주민등록번호에 - 를 입력해주세요");
				return false;
			} else if ($("input[name=ssn]").val().length <= 13) {
				$("#errorMessage").text("주민등록번호 13자리를 확인해주세요");
				return false;
			} else if ($("input[name=address]").val() == "") {
				$("#errorMessage").html("주소를 입력해주세요");

				console.log("주소입력 경고");
				return false;
			} else {
				return true;
			}

		}

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
	</script>

</body>
</html>