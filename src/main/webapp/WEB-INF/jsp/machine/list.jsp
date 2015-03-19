<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="300">
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
								  	<li><a href="/">Principal</a> <span class="divider">/</span></li>
								  	<li class="active">Máquinas novas (pendentes)</li>
								</ul>
							</div>
						</div>
						<!-- pending machines -->
						<div class="row">
							<div class="span12 center">
								<c:if test="${empty pending}">
									Nenhuma nova máquina.<br><br>
								</c:if>
								<c:if test="${not empty pending}">
									<table class="table table-striped table-condensed table-bordered table-hover">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Hash</th>
												<th>Status</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${pending}" var="machine">
										    <tr>
										    	<td>${machine.name}</td>
										    	<td>${machine.hash}</td>
										    	<td>${machine.statusDescription}</td>
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
						<!-- /pending machines -->
						
						<div class="row">
							<div class="span12 center">
								<ul class="breadcrumb">
								  	<li><a href="home">Principal</a> <span class="divider">/</span></li>
								  	<li class="active">Máquinas cadastradas</li>
								</ul>
							</div>
						</div>
						
						<!-- accepted machines -->
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
												<th>Conectado no canal</th>
												<th>Status</th>
												<th>Última sincronização</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${machines}" var="machine">
										    <tr>
										    	<td>${machine.name}</td>
										    	<td>${machine.channel.name}</td>
										    	<td>${machine.statusDescription}</td>
										    	<td><fmt:formatDate value="${machine.lastUpdated}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
										    	<td>
													<div class="btn-group">
														<a class="btn dropdown-toggle btn-info" data-toggle="dropdown" href="#">
															Comandos
														  	<span class="caret"></span>
														</a>
														<ul class="dropdown-menu">
															<li><a href="#changeChannelModal" onclick = "javascript:setMachineId(${machine.id})" role="button" data-toggle="modal">Alterar Canal</a></li>
														</ul>
													</div>
													<!-- 
												    <button class="btn btn-danger"><i class="icon-remove-circle"></i> Bloquear Máquina</button>
													 -->
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
						<!-- /accepted machines -->
					</div>
				</div>
			</div>
		</div>
		
		<!-- change channel command modal -->
		<div id="changeChannelModal" class="modal hide fade">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    <h3>Alterar canal</h3>
		  </div>
		  <div class="modal-body">
	    	<form id = "changeChannelForm" method = "post" action = "${pageContext.request.contextPath}/machines/setChannel">
	    		<select id = "channelId" name = "channel.id">
	    			<c:forEach items = "${channels}" var = "ch">
	    				<option value = "${ch.id}">${ch.name }</option>
	    			</c:forEach>
	    		</select>
	    		<input id = "machineId" type = "hidden" name = "machine.id">
	    		<input type = "hidden" name = "_method" value = "put">
	    	</form>
		  </div>
		  <div class="modal-footer">
		    <a href="#" class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Cancelar</a>
		    <a href="#" onclick="javascript:changeChannel()" class="btn btn-primary">Confirmar</a>
		  </div>
		</div>
		
		<!-- jquery/bootstrap js -->
		<script src="http://code.jquery.com/jquery.js"></script>
	    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/machines-app.js"></script>
	</body>
</html>