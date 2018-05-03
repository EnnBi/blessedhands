<!DOCTYPE html>
<html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
	<title>Medico</title>
	<meta charset="utf-8">
	<meta content="ie=edge" http-equiv="x-ua-compatible">
	<meta content="template language" name="keywords">
	<meta content="Tamerlan Soziev" name="author">
	<meta content="Admin dashboard html template" name="description">
	<meta content="width=device-width, initial-scale=1" name="viewport">
	<link href="favicon.png" rel="shortcut icon">
	<link href="apple-touch-icon.png" rel="apple-touch-icon">
	<link href="http://fast.fonts.net/cssapi/487b73f1-c2d1-43db-8526-db577e4c822b.css" rel="stylesheet" type="text/css">
	<link href="/resources/bower_components/select2/dist/css/select2.min.css" rel="stylesheet">
	<link href="/resources/bower_components/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
	<link href="/resources/bower_components/dropzone/dist/dropzone.css" rel="stylesheet">
	<link href="/resources/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
	<link href="/resources/bower_components/fullcalendar/dist/fullcalendar.min.css" rel="stylesheet">
	<link href="/resources/bower_components/perfect-scrollbar/css/perfect-scrollbar.min.css" rel="stylesheet">
	<link href="/resources/css/main.css" rel="stylesheet">
</head>

<body class="auth-wrapper">
	<div class="all-wrapper menu-side with-pattern">
		<div class="auth-box-w">
			<div class="logo-w">
				<a><img alt="" src="/resources/img/logo-big.png"></a>
			</div>
			<h4 class="auth-header">Login Form</h4>
			<c:if test="${not empty param.error}">
			 <div class="alert alert-danger">Invalid Email or Password</div>
			</c:if>
			<form action="/j_security_check" method="post">
				<div class="form-group"><label for="">UserName</label><input class="form-control" name='j_username' placeholder="Enter your username" type="text">
					<div class="pre-icon os-icon os-icon-user-male-circle"></div>
				</div>
				<div class="form-group"><label for="">Password</label><input class="form-control" name='j_password' placeholder="Enter your password" type="password">
					<div class="pre-icon os-icon os-icon-fingerprint"></div>
				</div>
				<div class="buttons-w"><button type="submit" class="btn btn-primary">Log me in</button>
					<div class="form-check-inline"><label class="form-check-label"><input class="form-check-input" type="checkbox">Remember Me</label></div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>
