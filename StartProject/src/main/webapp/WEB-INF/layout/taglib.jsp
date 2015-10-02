<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<script src="/iHome-war/js/jquery.js" type="text/javascript"
	charset="utf-8"></script>
<link rel="stylesheet" href="/iHome-war/styles/prettyPhoto.css"
	type="text/css" media="screen" charset="utf-8" />
<script src="/iHome-war/js/jquery.prettyPhoto.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("#soForm\\:commandLink[rel^='prettyPhoto']").prettyPhoto();
	});
</script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>