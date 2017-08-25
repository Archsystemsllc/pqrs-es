<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<div id="page-wrapper">
	<div id="page-bgtop">
		<div id="page-bgbtm">
			<div id="page" class="5grid-layout">
				<div id="page-content-wrapper">
					<div class="row">
						<div class="12u">
							<div id="breadcrumb">
							    <ul>
							        <li>
							            <a href="${pageContext.request.contextPath}/app/dashboard">Home</a> <span> >> </span>
							        </li>	
							         <!--adding link-->				
							        <li>
							            <a href="${pageContext.request.contextPath}/app/admin/allreport">All Reports</a>
							            <!-- All Reports --> <span></span>
							        </li>					       
							    </ul>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="12u">
                              <div class="row">
								<div class="12u">
				
				<section id="content">
				<div class="widget_wrap">
				<div class="widget_top">
					
					<h6>List of Reports</h6>
					
				</div>
				<c:if test="${not empty success}">
                        <div class="successblock"><spring:message code="${success}"></spring:message>
                        </div>
                  </c:if>
                         
				<div style="padding-left:30px;padding-top:10px;padding-bottom:20px;"><a href="${pageContext.request.contextPath}/app/admin/groupreport-pqrsentity">Group Report</a></div>
				<div style="padding-left:30px;padding-top:10px;"><a href="${pageContext.request.contextPath}/app/admin/questionandanswer">Q & A Report</a></div>
				</div>
				</section>

            </div>

								
							
				</div>
				<div class="row">
					<div class="12u">
						<div class="row" id="footer-content">
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>