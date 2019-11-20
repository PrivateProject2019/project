<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login V2</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="resources/images/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="resources/css/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/css/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/css/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/css/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="resources/css/util.css">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<!--===============================================================================================-->
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action="login.do"
					method="post">
					<span class="login100-form-title p-b-26"> EzCol LOGIN </span> <span
						class="login100-form-title p-b-48"> <i
						class="zmdi zmdi-font"></i>
					</span>

					<div class="wrap-input100 validate-input"
						data-validate="Valid email is: a@b.c">
						<input class="input100" type="text" name="id"> <span
							class="focus-input100" data-placeholder="ID"></span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Enter password">
						<span class="btn-show-pass"> <i class="zmdi zmdi-eye"></i>
						</span> <input class="input100" type="password" name="password">
						<span class="focus-input100" data-placeholder="PassWord"></span>
					</div>

					<div
						style="margin: 0; color: red; font-weight: bold; font-size: 10pt;"
						id="errorMessage">${ message }</div>

					<input type="radio" value="student" name="type"> Student<input
						type="radio" value="teacher" name="type"> Professor <input
						type="radio" value="employee" name="type"> Employee

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn" type="submit"
								onclick="return validate()">Login </button>
						</div>
					</div>

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

					<div class="text-center p-t-115"></div>
				</form>
			</div>
		</div>
	</div>


	<div id="dropDownSelect1"></div>

	<!--===============================================================================================-->
	<script src="resources/js/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="resources/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="resources/js/popper.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="resources/js/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="resources/js/moment.min.js"></script>
	<script src="resources/js/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="resources/js/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="resources/js/main.js"></script>



	<script type="text/javascript">
		function validate() {

			if ($("input:radio[name=type]").is(":checked") == false) {
				$("#errorMessage").text("학생,교수,직원을 선택해주세요");
				return false;
			}

			return true;
		}
		
		
	</script>

</body>
</html>