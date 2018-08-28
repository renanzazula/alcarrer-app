<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
		<tiles:insertAttribute name="titleApp" ignore="true" /> - <tiles:insertAttribute name="title" ignore="true" />
	</title>
	
	<spring:url value="/resources/jquery-ui.css" var="mainCss" />
	<link rel="stylesheet" href="${mainCss}"/>
	
	<spring:url value="/resources/formTemplate.css" var="formTemplate" />
	<link rel="stylesheet" href="${formTemplate}"/>
	
	<spring:url value="/resources/jquery.dataTables.min.css" var="jqueryDataTablesMinCss" />
	<link rel="stylesheet" href="${jqueryDataTablesMinCss}">
	
	<spring:url value="/resources/breadcrumb.css" var="breadcrumbCss" />
	<link rel="stylesheet" href="${breadcrumbCss}">

	<spring:url value="/resources/images/favicon.ico" var="favicon" />
	<link rel="shortcut icon" type="image/png" href="${favicon}"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"> </script>
	
	<script type="text/javascript">
// 		$(document).ready(function() {
// 		    $("#locales").change(function () {
// 		        var selectedOption = $('#locales').val();
// 		        if (selectedOption != ''){
// 		            window.location.replace('international?lang=' + selectedOption);
// 		        }
// 		    });
// 		});
	</script> 
		
<style>
body {
	font-family: "Trebuchet MS", sans-serif;
	margin: 50px;
}

.demoHeaders {
	margin-top: 2em;
}

#dialog-link {
	padding: .4em 1em .4em 20px;
	text -decoration: none;
	position: relative;
}

#dialog-link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
}

#icons {
	margin: 0;
	padding: 0;
}

#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

#icons span.ui-icon {
	float: left;
	margin: 0 4px;
}

.fakewindowcontain .ui-widget-overlay {
	position: absolute;
}

select {
	width: 200px;
}
 
</style>
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td height="auto" width="auto"  >
				<tiles:insertAttribute name="header" />
			</td>
		</tr>
		<tr>
			<td height="auto" style="background: #000000"  >
				<tiles:insertAttribute name="menu" />
			</td>
		</tr>
		<tr>
			<td height="auto" style="background: #000000">
<%-- 				<tiles:insertAttribute name="breadcrumb"/> --%>
			</td>
			
<!-- 			<td height="auto" style="background: #000000" width="25%" abbr="right"> -->
<!-- 				<hr align="center" width="99%" size="1" color="#a0a09f"></hr> -->
<!-- 					<ul class="breadcrumb"> -->
<!-- 						<li> -->
<!-- 							<label style="color: red"> Funcionario: Funcionario Padão</label> -->
<!-- 						</li>	 -->
<!-- 					</ul>	 -->
<!-- 				<hr align="center" width="99%" size="1" color="#a0a09f"></hr> -->
<!-- 			</td> -->
			
		</tr>
		<tr>
			<td width="auto" >
				<tiles:insertAttribute name="body" />
			</td>
		</tr>
		<tr>
			<td height="30" width="auto" style="background: #000000"  >
				<tiles:insertAttribute name="footer" />
			</td>
		</tr>
	</table>
</body>
</html>