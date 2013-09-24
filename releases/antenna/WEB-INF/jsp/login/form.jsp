<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>${applicationInfo.name} - ${applicationInfo.version}</title>
		<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" media="screen">
	</head>
	<body>
		<div id="menu" class="container-fluid">
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<a class="brand" href="home">
						<img src="${pageContext.request.contextPath}/resources/images/logo-mini.png">
					</a>
					<ul class="nav">
						<li><a href="channels">Canais</a></li>
						<li><a href="videos">Vídeos</a></li>
						<li class="active dropdown"><a href="users">Usuários</a></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="content">
				<div class="row">
					<div class="span12 center">
						<div class="row">
							<div class="span12 center">
								<ul class="breadcrumb">
								  	<li><a href="home">Principal</a> <span class="divider">/</span></li>
								  	<li><a href="channels">Usuários</a> <span class="divider">/</span></li>
								  	<li class="active">Criar novo</li>
								</ul>
							</div>
						</div>
						<div class="row">
							<div class="span12 center">
								<form id="userForm" class="form" action="${pageContext.request.contextPath}/users" method="post">
									<input type="hidden" name="user.id" value="${user.id}">
									<input type="hidden" id="editMethod" name="_method" value="post">
									<label>Nome do usuário</label>
									<input type="text" class="input input-block-level" name="user.name" id="name" value="${user.name}"><br>
									<label>Login</label>
									<input type="text" class="input input-block-level" name="user.username" id="username" value="${user.username}"><br>
									<label>Senha</label>
									<input type="password" class="input input-block-level" name="user.password" id="password" value="${user.password}"><br>
									<c:if test="${empty channel.id}">
										<button type="submit" class="btn btn-medium btn-success">Criar</button>
									</c:if>
									<c:if test="${not empty channel.id}">
										<button type="button" onclick="javascript:sendEditForm();" class="btn btn-medium btn-success">Alterar</button>
									</c:if>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- jquery/bootstrap js -->
		<script src="http://code.jquery.com/jquery.js"></script>
	    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/channel-app.js"></script>
	</body>
</html>