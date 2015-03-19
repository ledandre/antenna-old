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
						<li><a href="${pageContext.request.contextPath}/channels">Canais</a></li>
						<li class="active dropdown"><a href="${pageContext.request.contextPath}/videos">Vídeos</a></li>
						<li><a href="${pageContext.request.contextPath}/machines">Máquinas</a></li>
						<li><a href="${pageContext.request.contextPath}/logout">Sair</a></li>
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
								  	<li><a href="/">Principal</a> <span class="divider">/</span></li>
								  	<li class="active">Vídeos</li>
								</ul>
							</div>
						</div>
						<div class="row">
							<div class="span12 center">
								<button class="btn" onclick="javascript:location.href='${pageContext.request.contextPath}/videos/form'"><i class="icon-plus"></i> Novo vídeo</button><br><br>
								<c:if test="${empty videos}">
									Nenhum vídeo cadastrado.
								</c:if>
								<c:if test="${not empty videos}">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Descrição</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${videos}" var="video">
										    <tr>
										    	<td>${video.name}</td>
										    	<td>${video.description}</td>
										    	<td>
													<a href="${pageContext.request.contextPath}/videos/edit/${video.id}"><i class="icon-pencil"></a></i>
												    <a href="#" onclick="javascript:confirmRemove(${video.id}, '${video.name}');"><i class="icon-remove"></i></a>
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
	    <script src="${pageContext.request.contextPath}/resources/js/video-app.js"></script>
	</body>
</html>