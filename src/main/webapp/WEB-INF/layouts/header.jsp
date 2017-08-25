<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/5grid/survey-custom.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/5grid/button-style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/5grid/styleusersurvey.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/themes.css" />

<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="jquery.timepicker.js"></script>
<script type="text/javascript" src="lib/base.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom-scripts.js"></script>
<script type="text/javascript">
$(document).ready(function(){
 
    $('#user_nav ul li a').append('<span></span>');
 
    $('#user_nav ul li a').hover(
        function(){ 
            $(this).find('span').animate({opacity:'show', top: '-70'}, 'slow');
 
            var hoverTexts = $(this).attr('title');
             $(this).find('span').text(hoverTexts);
        },
 
        function(){ 
            $(this).find('span').animate({opacity:'hide', top: '-90'}, 'fast');
        }
    );   
    
});


</script>
<body>	
<div id="header-wrapper">
	<header id="header">
		<div class="5grid-layout">
			<div class="row">
				<!-- logo header -->
				<div class="12u" id="logo"> <!-- Logo -->
                 	<div id="top_up" style="color: white; font-size: 36px;  padding-top: 20px;">
				 	<a href="http://www.cms.gov" target="_blank">
                 	<img alt="CMS logo" src="${pageContext.request.contextPath}/resources/images/logo.jpg"  title="Click here to view the CMS website( Link opens in new window)" height="154px" width="1000px"></a></div>					
				</div>
				</div>
			</div>
		</div>
		<div id="menu-wrapper">
			<div class="5grid-layout">
				<div class="row">
					<div class="12u" id="menu">
						<nav class="mobileUI-site-nav">
							<ul>
							
							<c:if test="${navigationstatus=='dashboard'}">
							    <li class="current_page_item" value="1"><a href="${pageContext.request.contextPath}/app/dashboard">Home Page</a></li>
							 </c:if>
							 <c:if test="${navigationstatus !='dashboard' }">
							    <li value="1"><a href="${pageContext.request.contextPath}/app/dashboard">Home Page</a></li>
							 </c:if>
							 
							 <c:if test="${navigationstatus=='questionadmin'}">
							    <li value="2" class="current_page_item"><a href="${pageContext.request.contextPath}/app/admin/questions">Question Admin</a></li>
							 </c:if>
							 <c:if test="${navigationstatus !='questionadmin' }">
							   <li value="2"><a href="${pageContext.request.contextPath}/app/admin/questions">Question Admin</a></li>
							 </c:if>
							 
							 <c:if test="${navigationstatus=='surveyadmin'}">
							   <li value="3" class="current_page_item"><a href="${pageContext.request.contextPath}/app/admin/surveys">Survey Admin</a></li>
							 </c:if>
							 <c:if test="${navigationstatus !='surveyadmin' }">
							   <li value="3"><a href="${pageContext.request.contextPath}/app/admin/surveys">Survey Admin</a></li>
							 </c:if>
							 
							 <c:if test="${navigationstatus=='entityadmin'}">
							   <li value="4" class="current_page_item"><a href="${pageContext.request.contextPath}/app/admin/pqrsentitys">Entity Admin</a></li>
							 </c:if>
							 <c:if test="${navigationstatus !='entityadmin' }">
							   <li value="4"><a href="${pageContext.request.contextPath}/app/admin/pqrsentitys">Entity Admin</a></li>
							 </c:if>							 
							 
							  <!--<c:if test="${navigationstatus=='reports'}">
							   <li value="5" class="current_page_item"><a href="${pageContext.request.contextPath}/app/admin/groupreport-pqrsentity">Reports</a></li>
							 </c:if>-->
							 
							 <c:if test="${navigationstatus =='reports' }">
							   <li value="5" class="current_page_item"><a href="${pageContext.request.contextPath}/app/admin/allreport">Reports</a></li>
							 </c:if>
							 
							 <c:if test="${navigationstatus !='reports' }">
							   <li value="5"><a href="${pageContext.request.contextPath}/app/admin/allreport">Reports</a></li>
							 </c:if>
							
							 <c:if test="${navigationstatus =='filesupload' }">
							   <li value="6"class="current_page_item"><a href="${pageContext.request.contextPath}/app/admin/filesupload">FILE Upload</a></li>
							 </c:if>
							 
							 <c:if test="${navigationstatus !='filesupload' }">
							   <li value="6"><a href="${pageContext.request.contextPath}/app/admin/filesupload">FILE Upload</a></li>
							 </c:if>
							 
							  <!--<c:if test="${navigationstatus !='dashboard' }">
							    <li value="7"><c:url value="/j_spring_security_logout" var="logoutUrl" /><a href="${logoutUrl}">Log Out</a></li>
							  </c:if>-->
							  
							  <sec:authorize access="isAuthenticated()">
							  	<li value="7"><c:url value="/j_spring_security_logout" var="logoutUrl" /><a href="${logoutUrl}" style="color:#489ce1">Log Out</a></li>
							  </sec:authorize>
							  
							  <c:if test="${not empty success}">
							   <li value="7" style="color:#489ce1"><spring:message code="${success}"></spring:message></li>
							  </c:if>
								
								<!-- <li value="4"><a href="reportsscreen.html">Reports</a></li> -->
                           </ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</header>
</div>