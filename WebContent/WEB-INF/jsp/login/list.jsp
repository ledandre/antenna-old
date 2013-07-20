<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<div class="span12">
						<div class="row">
							<div class="span12">
								<ul class="breadcrumb">
								  	<li><a href="home">Principal</a> <span class="divider">/</span></li>
								  	<li class="active">Usuários</li>
								</ul>
							</div>
						</div>
						<div class="row">
							<div class="span12">
								<button class="btn" onclick="javascript:location.href='users/form'"><i class="icon-plus"></i> Novo usuário</button><br><br>
								<c:if test="${empty users}">
									Nenhum usuário cadastrado.
								</c:if>
								<c:if test="${not empty users}">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Login</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${users}" var="user">
										    <tr>
										    	<td>${user.name}</td>
										    	<td>${user.username}</td>
										    	<td>
														<a href="users/edit/${user.id}"><i class="icon-pencil"></a></i>
														<i class="icon-ban-circle"></i>
													    <a href="#" onclick="javascript:confirmRemove(${user.id}, '${user.username}');"><i class="icon-remove"></i></a>
												</td>
										</c:forEach>
										</tbody>
									</table>
									<form id="deleteForm" action="" method="POST">
										<input type="hidden" name="_method" value="DELETE">
									</form>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- jquery/bootstrap js -->
		<script src="http://code.jquery.com/jquery.js"></script>
	    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/users-app.js"></script>
	</body>
</html>