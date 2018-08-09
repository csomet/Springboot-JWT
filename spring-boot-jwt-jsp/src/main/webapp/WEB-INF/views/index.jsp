<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo Web</title>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="#">Spring Boot demo App</a>
		<button class="navbar-toggler" type="button">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#about">About</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<h1 class="display-3">
				<c:out value="${title}" />
			</h1>
			<h2>${title}</h2>
		</div>
	</div>

	<div class="container">
		<hr>
		<footer>
			<p>&copy; Carlos H Somet 2018</p>
		</footer>
	</div>
</body>
</html>