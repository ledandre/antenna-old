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
						<li class="active dropdown"><a href="${pageContext.request.contextPath}/channels">Canais</a></li>
						<li><a href="${pageContext.request.contextPath}/videos">Vídeos</a></li>
						<li><a href="${pageContext.request.contextPath}/logout">Sair</a></li>
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
								  	<li class="active">Canais</li>
								</ul>
							</div>
						</div>
						<div class="row">
							<div class="span12">
								<button class="btn" onclick="javascript:location.href='${pageContext.request.contextPath}/channels/form'"><i class="icon-plus"></i> Novo canal</button><br><br>
								<c:if test="${empty channels}">
									Nenhum canal cadastrado.
								</c:if>
								<c:if test="${not empty channels}">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Descrição</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${channels}" var="channel">
										    <tr>
										    	<td>${channel.name}</td>
										    	<td>${channel.description}</td>
										    	<td>
													<a class="options-link" data-toggle="tooltip" title="Editar" data-original-title="Editar" href="${pageContext.request.contextPath}/channels/edit/${channel.id}"><i class="icon-pencil"></a></i>
													<a class="options-link" data-toggle="tooltip" title="Gerenciar programação" data-original-title="Gerenciar programação" href="${pageContext.request.contextPath}/schedules/${channel.id}"><i class="icon-film"></i></a>
												    <a class="options-link" data-toggle="tooltip" title="Remover" data-original-title="Remover" href="#" onclick="javascript:confirmRemove(${channel.id}, '${channel.name}');"><i class="icon-remove"></i></a>
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
	    <script src="${pageContext.request.contextPath}/resources/js/channel-app.js"></script>
	</body>
</html>