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
						<li><a href="${pageContext.request.contextPath}/channels">Canais</a></li>
						<li class="active dropdown"><a href="${pageContext.request.contextPath}/videos">Vídeos</a></li>
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
								  	<li><a href="${pageContext.request.contextPath}/home">Principal</a> <span class="divider">/</span></li>
								  	<li><a href="${pageContext.request.contextPath}/videos">Vídeos</a> <span class="divider">/</span></li>
								  	<li class="active">Criar novo</li>
								</ul>
							</div>
						</div>
						<div class="row">
							<div class="span12">
								<div class="progress progress-striped active" style="display: none">
									<div id="progressbar" class="bar" style="width: 0%;"></div>
								</div>
								<div id="videoFormDiv">
									<form id="videoForm" action="${pageContext.request.contextPath}/videos" method="post" enctype="multipart/form-data">
										<input type="hidden" id="id" name="video.id" value="${video.id}">
										<input type="hidden" id="file" name="video.file" value="${video.file}">
										<label>Nome do vídeo</label>
										<input class="input input-block-level" name="video.name" id="name" value="${video.name}"><br>
										<label>Arquivo do vídeo <c:if test="${not empty video.id}"><small>[<strong> ${video.file}</strong> - para substituir este arquivo, selecione um novo.]</small></c:if></label>
										<input id="videoFile" type="file" name="videoFile" <c:if test="${empty video.id}">required</c:if>>
										<label>Descrição:</label>
										<textarea id="description" rows="5" cols="10" class="input-block-level" name="video.description">${video.description}</textarea><br>
										<c:if test="${empty video.id}">
											<button type="submit" class="btn btn-medium btn-success">Criar</button>
										</c:if>
										<c:if test="${not empty video.id}">
											<button type="button" onclick="javascript:sendEditForm();" class="btn btn-medium btn-success">Alterar</button>
										</c:if>
									</form>
								</div>
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