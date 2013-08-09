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
								  	<li><a href="${pageContext.request.contextPath}/home">Principal</a> <span class="divider">/</span></li>
								  	<li><a href="${pageContext.request.contextPath}/channels">Canais</a> <span class="divider">/</span></li>
								  	<li class="active">Programação</li>
								</ul>
							</div>
						</div>
						<div class="row">
							<div class="span12">
								<h3>Programação do canal ${channel.name}</h3>
								<c:if test="${empty schedule}">
									<div class="alert">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<strong>Atenção!</strong> A programação deste canal ainda não foi criada.
									</div>
								</c:if>
								
								<div class="row">
									<form id="hidden-schedule-form" method="post" action="${pageContext.request.contextPath}/schedules">
										<div class="span6">
											<c:if test="${empty videos}">
												Não há nenhum vídeo cadastrado.
											</c:if>
											<c:if test="${not empty videos}">
										  		<h4>Vídeos disponíveis</h4>
										  		<table class="table table-bordered table-striped">
										  			<thead></thead>
										  			<tbody>
														<c:forEach items="${videos}" var="video">
															<tr>
																<td>
																	<strong>${video.name}</strong>
																</td>
																<td style="text-align: right">
																	<a href="#" class="options-link" data-toggle="tooltip" title="Preview" data-original-title="Preview">
																		<i class="icon-facetime-video"></i>
																	</a>
																	<a href="#" onclick="javascript:addVideo(${video.id}, '${video.name}');" class="options-link" data-toggle="tooltip" title="Adicionar à lista de reprodução" data-original-title="Adicionar à lista de reprodução">
																		<i class="icon-plus-sign"></i>
																	</a>
																</td>
															</tr>
														</c:forEach>
										  			</tbody>
												</table>
											</c:if>
										</div>
										<div class="span6">
									  		<h4>Programação atual</h4>
										  		<table id="schedule-table" class="table table-bordered table-striped">
										  			<thead></thead>
										  			<tbody>
													<c:if test="${not empty schedule}">
														<c:forEach items="${schedule.videoList}" var="videoList" varStatus="status">
															<tr id="tr-${status.index}">
																<td>
																	<a href="#" onclick="javscript:removeVideo(${status.index})" class="options-link" data-toggle="tooltip" title="Remover da lista de reprodução" data-original-title="Remover da lista de reprodução">
																		<i class="icon-minus-sign"></i>
																	</a>
																	<a href="#" class="options-link" data-toggle="tooltip" title="Preview" data-original-title="Preview">
																		<i class="icon-facetime-video"></i>
																	</a>
																</td>
																<td>
																	<strong>${videoList.video.name}</strong>
																</td>
															</tr>
															<input type="hidden" id="vd-${status.index}" name="schedule.videoList.video.id" value="${videoList.video.id}">
														</c:forEach>
													</c:if>
										  			</tbody>
												</table>
											<button type="submit" class="submit-hidden-form btn btn-medium btn-success pull-right disabled" disabled>Salvar programação</button>
										</div>
										<input type="hidden" name="schedule.id" value="${schedule.id}">
										<input type="hidden" name="schedule.channel.id" value="${channelId}">
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
	    <script src="${pageContext.request.contextPath}/resources/js/schedules-app.js"></script>
	</body>
</html>