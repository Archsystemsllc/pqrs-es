<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>PQRS Electronic Survey</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/5grid/core.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/5grid/core-desktop.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/5grid/core-1200px.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/5grid/core-noscript.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style-desktop.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/stylesusersurvey.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/5grid/stylesusersurvey.css" />

<style type="text/css">
body {
	background-color: #dbdbdb;
}

.fr {
	float: right;
}

.demo {
	margin: -30px auto;
	width: 350px;
}

.login-control {
	color: #636363;
	padding: 20px;
	line-height: 86px;
	background-color: #323232;
	transition: all 300ms;
	font-family: 'Devonshire', cursive;
}

.login-control h2 {
	color: #f1f1f1;
	font-size: 36px;
	font-weight: normal;
	line-height: 50px;
	text-align: center;
	/*adjusting contrast*/
	background-color: #636363;
}

.login-control input[type="text"],.login-control input[type="password"]
	{
	width: 100%;
	height: 40px;
	padding: 0 2px;
	border: none;
	box-sizing: border-box;
	transition: all 300ms;
}

input:focus {
	outline: 0 none;
	box-shadow: 0 0 0 5px #00aec7;
}

.form-item-3 {
	text-align: left;
	line-height: 20px;
	margin-bottom: 20px;
}

.login-control input[type='checkbox'] {
	vertical-align: middle;
	margin-right: 5px;
}

.login-control input[type='submit'] {
	color: #fff;
	font-size: 36px;
	width: 100%;
	height: 70px;
	border: none;
	background-color: #00aec7;
	cursor: pointer;
	transition: all 300ms;
	font-family: 'Devonshire', cursive;
}

.login-control input[type='button']:hover {
	background-color: #006675;
}
</style>
<script
	src="${pageContext.request.contextPath}/resources/css/5grid/jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
<!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
</head>
<body class="column1">
	<div id="header-wrapper">
		<header id="header">
			<div class="5grid-layout">
				<div class="row">
					<div class="12u" id="logo">
						<!-- Logo -->
						<table>
							<tbody>
								<tr>
									<td></td>
									<td><div style="padding-left: 100px; padding-top: 30px;">
											<h1>
												<a class="mobileUI-site-name" href="#">Physician Quality
													Reporting System</a>
											</h1>
										</div></td>
								</tr>
							</tbody>
						</table>


					</div>
					<div
						style="padding-left: 860px; font-size: 33px; padding-bottom: 6px; color: white">
						Electronic Survey</div>
				</div>
			</div>
			<div id="menu-wrapper">
				<div class="5grid-layout">
					<div class="row">
						<div class="12u" id="menu">
							<nav class="mobileUI-site-nav">
								<ul>
									<li><a href="${pageContext.request.contextPath}/app/userSurvey">Homepage</a></li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</header>
	</div>
	<div id="page-wrapper">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="page" class="5grid-layout">
					<div id="page-content-wrapper">
						<div class="row">
							<div class="12u">

								<div class="4u">
									<div style="padding-left: 360px;">
										<section id="sidebar">

											<section class="demo" style="width: 441px;">

												<c:if test="${not empty error}">
													<div class="errorblock">Login failed, try
														again.Caused :
														${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
												</c:if>
											
												<div class="login-control">
													<form name='f'
														action="<c:url value='/j_spring_security_check' />"
														method='POST'>
														<div class="block_form">
															<h2>LOGIN</h2>
															<ul>
																<li class="form-item-1"><input type="text"
																	id="user" name="j_username"></li>
																<li class="form-item-2"><input type="password"
																	id="pw" name="j_password"></li>
															</ul>
															<div class="form-item-4">
																<a
																	href="${pageContext.request.contextPath}/app/dashboard"><input
																	name="" value="Login" type="submit"></a>
															</div>
															</div>
													</form>
												</div>
									</section>
									</section>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<section>
						<div class="divider"></div>
					</section>
				</div>
				<div class="row" id="onecolumn">
					<div class="12u"></div>
				</div>
			</div>
			<div class="row">
				<div class="12u">
					<div class="row" id="footer-content">
						<div class="4u" id="box1"></div>
						<div class="4u" id="box2"></div>
						<div class="4u" id="box3"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	<div id="copyright" class="5grid-layout">
		<section>
			<p>
				&copy; Arch solution | Images: <a
					href="http://www.archsystemsinc.com">Arch Systems</a> | Design: <a
					href="http://www.archsystemsinc.com">Arch Systems</a>
			</p>
		</section>
	</div>
</body>
</html>