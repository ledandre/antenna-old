<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>${applicationInfo.name} - ${applicationInfo.version}</title>
		<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" media="screen">
	</head>
	<body>
		<div class="container-fluid">
			<br><br>
			<div class="row">
				<div class="span3 center">
					<img src="${pageContext.request.contextPath}/resources/images/logo.png">
				</div>
				<div class="span3 center content">
					<c:if test="${not empty error}">
						<div class="alert alert-error">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Falha na autenticação</strong> ${error}
						</div>
					</c:if>
					<form class="form-signin" method="post" action="">
						<div class="input-prepend">
							<span class="add-on">
								<i class="icon-user"></i>
							</span>
							<input name="user.username" type="text" autofocus required maxlength="12" class="input input-block-level">
						</div>
						<br>
						<div class="input-prepend">
							<span class="add-on">
								<i class="icon-lock"></i>
							</span>
							<input name="user.password" type="password" required maxlength="8" class="input input-block-level">
						</div>
						<br>
						<button type="submit" class="btn btn-success">Entrar</button>
					</form>
				</div>
			</div>
		</div>

		<!-- jquery/bootstrap js -->
		<script src="http://code.jquery.com/jquery.js"></script>
	    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>