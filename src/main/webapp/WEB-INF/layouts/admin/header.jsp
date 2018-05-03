<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	<meta charset="utf-8">
	<meta content="ie=edge" http-equiv="x-ua-compatible">
	<meta content="template language" name="keywords">
	<meta content="Tamerlan Soziev" name="author">
	<meta content="Admin dashboard html template" name="description">
	<meta content="width=device-width, initial-scale=1" name="viewport">
	<link href="${pageContext.servletContext.contextPath}/resources/favicon.png" rel="shortcut icon"/>
	<link href="${pageContext.servletContext.contextPath}/resources/apple-touch-icon.png" rel="apple-touch-icon"/>
	<link href="http://fast.fonts.net/cssapi/487b73f1-c2d1-43db-8526-db577e4c822b.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.servletContext.contextPath}/resources/bower_components/select2/dist/css/select2.min.css" rel="stylesheet"/>
	<link href="${pageContext.servletContext.contextPath}/resources/bower_components/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet"/>
	<link href="${pageContext.servletContext.contextPath}/resources/bower_components/dropzone/dist/dropzone.css" rel="stylesheet"/>
	<link href="${pageContext.servletContext.contextPath}/resources/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.servletContext.contextPath}/resources/bower_components/fullcalendar/dist/fullcalendar.min.css" rel="stylesheet">
	<link href="${pageContext.servletContext.contextPath}/resources/bower_components/perfect-scrollbar/css/perfect-scrollbar.min.css" rel="stylesheet">
	<link href="${pageContext.servletContext.contextPath}/resources/css/main.css" rel="stylesheet">
	<link href="${pageContext.servletContext.contextPath}/resources/css/style.css"  rel="stylesheet" type="text/css"/>
	
</head>