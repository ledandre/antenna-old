<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	</head>
	<body>
		<div class="container-fluid">
			<div class="container">
				<form class="form-signin">
					<div class="input-prepend">
						<spam class="add-on">
							<i class="icon-user"></i>
						</spam>
						<input type="text" autofocus required maxlength="12" class="input input-block-level">
					</div>
					<br>
					<div class="input-prepend">
						<spam class="add-on">
							<i class="icon-lock"></i>
						</spam>
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
	</body>
</html>