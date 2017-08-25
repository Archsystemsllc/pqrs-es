<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en" title="Physician Quality Reporting System (PQRS) Electronic Prescribing (eRx) Incentive  Programs Electronic Survey for Reporting Entities">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width"/>
<meta name="description" content="TThe Physician Quality Reporting System (PQRS)/Electronic Prescribing (eRx) Incentive Programs Electronic Survey for Reporting Entities (Survey) was developed under CMS' guidance as the agency continues working to improve data quality from all reporting entities. The survey is a data collection tool to facilitate identifying, creating, and deploying process improvements, as well as developing data quality recommendations for CMS, as the agency moves toward value-based purchasing and as it expands public reporting of performance information on Physician Compare."/>
 <meta name="Keywords" content="Registry Survey Application from CMS">
 <meta name="author" content="Arch Systems">
<meta charset="UTF-8">
<title>Physician Quality Reporting System (PQRS) Electronic Prescribing (eRx) Incentive  Programs Electronic Survey for Reporting Entities</title>
<link href="${pageContext.request.contextPath}/resources/css/jquery.jqplot.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/jquery-ui-1.8.18.custom.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styleheader.css" />
<link rel='stylesheet' href='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.css' />
<link href="${pageContext.request.contextPath}/resources/css/reset.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/themes.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/typography.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/shCore.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/jquery.jqplot.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/jquery-ui-1.8.18.custom.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/data-table.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/ui-elements.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/wizard.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/sprite.css" rel="stylesheet" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/gradient.css" rel="stylesheet" type="text/css" media="screen">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.ui.touch-punch.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chosen.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/uniform.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-dropdown.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-colorpicker.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sticky.full.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.noty.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/selectToUISlider.jQuery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/fg.menu.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.tagsinput.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.cleditor.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.tipsy.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.peity.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.simplemodal.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.jBreadCrumb.1.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.colorbox-min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.idTabs.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.multiFieldExtender.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.confirm.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/elfinder.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/accordion.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/autogrow.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/addrow.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/check-all.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/data-table.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/ZeroClipboard.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/TableTools.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jeditable.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/duallist.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/easing.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/full-calendar.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/input-limiter.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/inputmask.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/iphone-style-checkbox.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/meta-data.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/quicksand.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/raty.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/smart-wizard.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/stepy.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/treeview.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/ui-accordion.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/vaidation.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/mosaic.1.0.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.collapse.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.autocomplete.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/localdata.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.jqplot.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.dateAxisRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.cursor.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.logAxisRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.canvasTextRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.highlighter.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.pieRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.barRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.pointLabels.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.meterGaugeRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom-scripts.js"></script>
<script src='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.js'></script>
<script src="${pageContext.request.contextPath}/resources/css/5grid/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/data-table.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
</head>

<body>
<body id="theme-default" class="full_block"> 

    <tiles:insertTemplate template="userheader.jsp" />  
    <tiles:insertTemplate template="toolbar.jsp" />
    <tiles:insertAttribute name="content" />
    <tiles:insertTemplate template="footer.jsp" />
    
</body>

</html>
