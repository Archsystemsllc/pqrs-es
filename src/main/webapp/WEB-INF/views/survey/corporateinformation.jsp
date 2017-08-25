<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script>
	// Wait until the DOM has loaded before querying the document
	$(document).ready(function() {

		$("input[type=radio]").click(function() {

			if ($(this).is(':checked') && $(this).val() == "no1") {
				$('#surveyEntityMappingForm').submit();			
			}
			if ($(this).is(':checked') && $(this).val() == "yes1") {
				$('#surveyEntityMappingForm').submit();						
			}
		});

	});
</script>

<div id="page-wrapper">
	<div id="page-bgtop">
		<div id="page-bgbtm">
			<div id="page" class="5grid-layout">
				<div id="page-content-wrapper">
					<div class="row">
						<div class="12u">
							<div id="banner">
								<div id="page-wrap">
									<c:if test="${not empty success}">
										<div class="successblock">
											<spring:message code="${success}"></spring:message>
										</div>
									</c:if>
									<ul id='main-nav' class='tabs'>

										<li id='1' class="current" tabindex="4"><a href='#tab1'>Corporate
												Information</a></li>
										<c:if test="${surveyEntityMapping.surveyCompleteFlag==0}">
										<li id='2' class="training" tabindex="5"><a
											href='${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/2' title="click here to view question in Training Information">Training
												Information</a></li>
										<li id='3' class="datahandling" tabindex="5"><a
											href='${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/3' title="click here to view question in Data Handling"><span style="padding-right:21px;">Data Handling</span></a></li>
										<li id='4' class="quality" tabindex="6"><a
											href='${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/4' title="click here to view question in Quality Assurance"> Quality
												Assurance</a></li>
										<c:if test="${surveyEntityMapping.erxParticipationFlag=='yes1'}">
										<li id='5' class="erx"><a
											href='${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/5' title="click here to view questions in Clinical Questions section">Clinical Questions</a></li>
										</c:if>
										<li id='6' class="feedback" tabindex="7"><a
											href='${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/6' title="click here to view question in Feedback">Feedback</a></li>
										<li id='7' class="reviewandsubmit" tabindex="8"><a
											href='${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/7' title="click here to view question in Review And Submit">Review And Submit</a></li>
										</c:if>
									</ul>
									<div id='tab1'>

										<div class="clear"></div>
										<div id="featured">
										<input type="hidden" name="entityId"
													value="${pqrsEntity.id}" />
										<input type="hidden" name="surveyId"
													value="${surveyId}" />
											
											<form:form modelAttribute="surveyEntityMapping"
												action="${pageContext.request.contextPath}/app/new-usersurvey/${pqrsEntity.id}/${surveyId}"
												method="post" name="surveyEntityMappingForm" id="surveyEntityMappingForm">
												<ul class="TFul">
													<c:if test="${surveyEntityMapping.surveyCompleteFlag==1}">
													<li tabindex="8" title="Your survey has been submitted. Please contact the survey administrator to re-open the survey."><span style="padding-left: 2px;"> 
															Your survey has been submitted. Please contact the survey administrator to re-open the survey.</span>
													</li>
													</c:if>
													<li tabindex="9" title="Reporting Entity Name"><span style="padding-left: 2px;"> 
															Reporting Entity Name:&nbsp;&nbsp; </span> <span style="align: right">${surveyEntityMapping.pqrsEntity.name}</span>
													</li>
													<li tabindex="10" title="Reporting Entity Point of Contact Name"><span style="padding-left: 2px;">
															Reporting Entity Point of Contact Name:&nbsp;&nbsp;</span> <span> ${surveyEntityMapping.pqrsEntity.alternateName}</span>
													</li>

													<li tabindex="11" title="Addresses" ><span style="padding-left: 2px;">Email
															Addresse(s):&nbsp;&nbsp;</span> <span>${surveyEntityMapping.pqrsEntity.emailAddresses}</span></li>
													<li tabindex="12" title="Numbers"><span style="padding-left: 2px;">Contact
															Number(s):&nbsp;&nbsp;</span> <span>${surveyEntityMapping.pqrsEntity.contactNumbers}</span></li>
																			


												</ul>

												<div style="padding-left: 30px; padding-top: 20px; padding-bottom:5px" title="If any of the Reporting Entity corporate information listed above is incorrect, please contact surveyadmin@archsystemsinc.com to request changes.">Note:&nbsp;&nbsp;
													If any of the Reporting Entity corporate information listed above is incorrect, please contact surveyadmin@archsystemsinc.com to request changes. </div>
												
											</form:form>
											<c:if test="${surveyEntityMapping.surveyCompleteFlag==0}">
											<form:form modelAttribute="surveyEntityMapping"
												action="${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/2"
												method="post" name="surveyEntityMappingForm2" id="surveyEntityMappingForm2">
											<div align="left" style="padding-left: 30px; padding-top: 20px; padding-bottom: 20px;">
											<input type="hidden" name="initialAccess" value="${initialAccess}" />
												<c:if test="${initialAccess==true}">													
														<button type="submit" class="btn_small btn_blue" tabindex="14" value="StartSurvey" name="btnAction">Start Survey</button>													
												</c:if>
												<c:if test="${initialAccess==false}">													
														<button type="submit" class="btn_small btn_blue" tabindex="15" value="ReStartSurvey" name="btnAction">Re-Start Survey</button>													
												</c:if>
											</div>
											</form:form>
											</c:if>

										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

				</div>



				<div class="4u">
					<section id="sidebar"></section>
				</div>

				<div class="row">
					<section>
						<div class="divider"></div>
					</section>
				</div>
				<div class="row" id="onecolumn">
					<div class="12u">
						<section></section>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="12u">
					<div class="row" id="footer-content"></div>
				</div>
			</div>
		</div>
	</div>
</div>
