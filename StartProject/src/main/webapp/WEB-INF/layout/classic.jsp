<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<%@ include file="../layout/taglib.jsp"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.ico">

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="justified-nav.css" rel="stylesheet">

<title><tiles:getAsString name="title" /></title>
</head>

<body>

	<!-- Основной контент страницы -->
	<div class="container">
		<tiles:insertAttribute name="body" />
	</div>

	<div class="footer">

		<!-- футер страницы -->
		<tiles:insertAttribute name="footer" />

	</div>
</body>
</html>