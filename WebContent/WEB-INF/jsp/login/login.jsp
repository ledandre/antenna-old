<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %><%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${appInfo.name} - ${appInfo.version }</title>
<!-- CSS -->
<link href="${pageContext.request.contextPath}/resources/css/transdmin.css" rel="stylesheet" type="text/css" media="screen" />
<!--[if IE 6]><link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/css/ie6.css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/css/ie7.css" /><![endif]-->

<!-- JavaScripts-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jNice.js"></script>
</head>
<body>
	<div id="wrapper">
    	<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
    	<h1><a href="void();"><span>Antenna</span></a></h1>
        
        <div id="containerHolder">
			<div id="container_login">
                <div id="main_login">
                	<c:if test="${not empty Erro}">
                		<div id = "login_error">
	                		<img src="${pageContext.request.contextPath}/resources/img/error.png"/><c:out value="${Erro }" />
                		</div>
                	</c:if>
                	<form method="post" action="login" id="loginControllerForm" name="loginControllerForm" class="form">
                		<fieldset>
		                	<label>Usuário</label>
							<input name="user.username" maxlength="12" type = "text" class="text-long" required autofocus />
							<br /><br />
							<label>Senha</label>
							<input name = "user.password" maxlength="12" type = "password" class="text-long" required />
							<br /><br />
							<input class="button-submit" type = "submit" value = "OK">
                		</fieldset>
					</form> 
                </div>
                <!-- // #main -->
                <div class="clear"></div>
            </div>
            <!-- // #container -->
        </div>	
        <!-- // #containerHolder -->
        
        <p id="footer">Antenna - Sistema de mídia digital<br />by OrangeScript</p>
    </div>
    <!-- // #wrapper -->
</body>
</html>