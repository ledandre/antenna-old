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
						<li><a href="${pageContext.request.contextPath}/videos">Vídeos</a></li>
						<li class="active dropdown"><a href="${pageContext.request.contextPath}/machines">Máquinas</a></li>
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
								  	<li><a href="${pageContext.request.contextPath}/home">Principal</a> <span class="divider">/</span></li>
								  	<li><a href="${pageContext.request.contextPath}/machines">Máquinas</a> <span class="divider">/</span></li>
								  	<li class="active">Pendentes</li>
								</ul>
							</div>
						</div>
						<div class="row">
							<div class="span12 center">
								<c:if test="${empty machines}">
									Nenhuma máquina cadastrada.
								</c:if>
								<c:if test="${not empty machines}">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Hash</th>
												<th>Status</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${machines}" var="machine">
										    <tr>
										    	<td>${machine.name}</td>
										    	<td>${machine.hash}</td>
										    	<td>${machine.status}</td>
										    	<td>
													<a href="#" onclick="javascript:acceptMachine(${machine.id}, '${pageContext.request.contextPath}/machines/accept')"><i class="icon-ok-circle"></a></i>
												    <a href="#" onclick="javascript:confirmRemove(${machine.id}, '${machine.name}');"><i class="icon-remove-circle"></i></a>
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
	    <script src="${pageContext.request.contextPath}/resources/js/machines-app.js"></script>
	</body>
</html>