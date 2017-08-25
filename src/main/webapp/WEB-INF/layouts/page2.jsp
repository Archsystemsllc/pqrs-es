<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>IPMS</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styleheader.css" />
<script src="${pageContext.request.contextPath}/resources/css/5grid/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>

<style>
.error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

<script type='text/javascript'>

	$(document).ready(function() {
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		$('#calendar').fullCalendar({
			header: {
				center: 'title',
				left: 'prev,next today',
				right: 'month,agendaWeek,agendaDay'
			},
			editable: true,
			events: [
             <c:forEach var="meeting" items="${meetings}">
				{
					title: '${meeting.title}',
					start: '${meeting.date}',
					end: '${meeting.endDate}',
					url: '${pageContext.request.contextPath}/app/meeting/${meeting.id}'
				},
			 </c:forEach>
				{
					id: 999,
					title: '',
					start: null,
					allDay: false
				}
			]
		});
		
	});

</script>


<style type='text/css'>
	#calendar {
		margin: 5 5 5 5;
		font-size: 10px;
		}
</style>

</head>
<body>
</body>
</html>