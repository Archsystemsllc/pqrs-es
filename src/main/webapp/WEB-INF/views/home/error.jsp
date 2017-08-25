<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
	

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

<script type="text/javascript" charset="UTF-8" src="http://www.w3cplus.com/demo/css3/prefixfree.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Bilbo+Swash+Caps|Devonshire' rel='stylesheet' type='text/css'>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.bpopup-0.9.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom-scripts.js"></script>
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
				<div class="12u" id="logo"> <!-- Logo -->
				<div style="color: white; font-size: 36px; padding-left: 100px; padding-top: 32px;">
				 <div style="padding-left:160px;">Physician Quality Reporting System (PQRS)</div><div style="padding-left: 123px;">Electronic Prescribing (eRx) Incentive  Programs</div><div style="padding-left:220px;"> Electronic Survey for Registries
				</div></div>

					
				</div>

			</div>
		</div>
		<div id="menu-wrapper">
			<div class="5grid-layout">
				<div class="row">
					<div class="12u" id="menu">
						error page
					</div>
				</div>
			</div>
		</div>
	</header>
</div>