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
						<li class="active dropdown"><a href="channels">Canais</a></li>
						<li><a href="videos">Vídeos</a></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="content">
				<div class="row">
					<div class="span3">
						<ul class="nav nav-pills nav-stacked">
							<li><a href="channels/categories">Categorias</a></li>
						</ul>
					</div>
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
								<button class="btn"><i class="icon-plus"></i> Novo canal</button><br><br>
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Nome</th>
											<th>Descrição</th>
											<th>Categoria</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Canal teste</td>
											<td>Canal para realizar testes no sistema</td>
											<td>-</td>
											<td>
												<i class="icon-pencil"></i>
												<i class="icon-ban-circle"></i>
												<i class="icon-remove"></i>
											</td>
										</tr>
										<tr>
											<td>Anúncios de shopping</td>
											<td>Canal com anúncios para clientes de shoppings.</td>
											<td>Compras</td>
											<td>
												<i class="icon-pencil"></i>
												<i class="icon-ban-circle"></i>
												<i class="icon-remove"></i>
											</td>
										</tr>
										<tr>
											<td>Imóveis TV</td>
											<td>Canal com anúncios e informações sobre o mercado imobiliário.</td>
											<td>Imóveis</td>
											<td>
												<i class="icon-pencil"></i>
												<i class="icon-ban-circle"></i>
												<i class="icon-remove"></i>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- jquery/bootstrap js -->
		<script src="http://code.jquery.com/jquery.js"></script>
	    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>