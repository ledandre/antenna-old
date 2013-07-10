<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	</head>
	<body>
		<div class="container-fluid">
			<div class="span3 offset6 content">
				<form class="form-signin">
					<div class="input-prepend">
						<span class="add-on">
							<i class="icon-user"></i>
						</span>
						<input type="text" autofocus required maxlength="12" class="input input-block-level">
					</div>
					<br>
					<div class="input-prepend">
						<span class="add-on">
							<i class="icon-lock"></i>
						</span>
						<input type="password" required maxlength="8" class="input input-block-level">
					</div>
					<br>
					<button type="button" class="btn btn-success">Entrar</button>
				</form>
			</div>
		</div>

		<!-- jquery/bootstrap js -->
		<script src="http://code.jquery.com/jquery.js"></script>
	    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
		<script scr="${pageContext.request.contextPath}/resources/js/main.css"></script>
	</body>
</html>