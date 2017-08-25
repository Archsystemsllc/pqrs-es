<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script>
function myFunction()
{
	var iAgree = document.getElementById("iAgreeFlagBoolean").checked;
	if(iAgree==false) {
    	alert("Please check the certification box before submitting the survey");
    	return false;
    }
	if(confirm("Are you sure you want to submit the survey? Once the survey is submitted you must contact the survey administrator to re-open the survey."))
	    document.forms[0].submit();
	  else
	    return false;
}


</script>
	

<div id="page-wrapper">
	<div id="page-bgtop">
		<div id="page-bgbtm">
			<div id="page" class="5grid-layout">
				<div id="page-content-wrapper">
					<div class="row">
					<div style="font-family: helvetica;
	font-size:1.2em;
	line-height:1.5em;
	padding-right: 20px; 
	padding-left: 127px;">Note:&nbsp;&nbsp; Please do not enter any Personally Identifiable Information (PII) or Protected Health Information (PHI) in this survey. Save your responses before navigating away from this page. All questions must be answered before the survey can be successfully submitted.</div>
						<div class="12u">
							<div id="banner">
								<div id="page-wrap">
									<c:if test="${not empty errorMsg}">
										<div class=errorblock>
											<c:out value="${errorMsg}"/>
										</div>
									</c:if>
									<c:if test="${not empty success}">
										<div class="successblock">
											<spring:message code="${success}"></spring:message>
										</div>
									</c:if>
									<div id="confirm" style="display:none;">Please confirm that you want to submit</div>
									<form:form modelAttribute="surveyEntityMapping"
												action="${pageContext.request.contextPath}/app/submitsurvey"
												method="post" enctype="multipart/form-data"
												name="reviewAndSubmitForm">
									<form:errors path="*" cssClass="errorblock" element="div" />
									<ul id='main-nav' class='tabs'>

										<li id='1' class="corporate" tabindex="4"><a href='${pageContext.request.contextPath}/app/new-usersurvey/${pqrsEntity.id}/${surveyId}' 
											title="click here to view questions in Corporate Information">Corporate
												Information</a></li>
										<c:if test="${surveyEntityMapping.surveyCompleteFlag==0}">
										<li id='2' class="training" tabindex="5"><a   id="navigationLink" href='${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/2' 
										title="click here to view questions in Training">Training
												Information</a></li>
										<li id='3' class="datahandling" tabindex="6"><a href='${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/3' 
											title="click here to view questions in Data handling"><span style="padding-right:21px;">Data Handling</span></a></li>
										<li id='4' class="quality" tabindex="7"><a href='${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/4' 
											title="click here to view questions in Quality Assurance">Quality Assurance</a></li>
										<c:if test="${surveyEntityMapping.erxParticipationFlag=='yes1'}">
										<li id='5' class="erx" tabindex="8"><a href='${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/5' 
											title="click here to view questions in Clinical Questions">Clinical Questions</a></li>
										</c:if>
										<li id='6' class="feedback" tabindex="9"><a href='${pageContext.request.contextPath}/app/view-usersurvey/${pqrsEntity.id}/${surveyId}/6'
											title="click here to view questions in Feedback">Feedback</a></li>
										<li id='7' class="current" tabindex="10"><a
											href='#tab7' title="click here to view questions in Review And Submit">Review And Submit</a></li>
										</c:if>
									</ul>


									<div id='tab6'>
										<div class="clear"></div>
										<div id="featured">
											
												<input type="hidden" name="entityId"
													value="${pqrsEntity.id}" />
												<input type="hidden" name="surveyId"
													value="${surveyId}" />
												<input type="hidden" name="pageName"
													value="SubmitSurvey" />


												<ul>
												
									                 <li style="padding-left: 1px; padding-top: 20px;" tabindex="11">
									                 <div class="form_grid_12">
									                    <table class="myTable">
									                        <thead>
									                        	<tr>
																	<th>Category</th>
																	<th>Question And Answers</th>
																</tr>
															</thead>
															<tbody>
															
															    <c:forEach var="question" items="${questionbank}">
																<tr>
																	<c:if test="${surveyEntityMapping.erxParticipationFlag=='yes1'}">
																	 
																		<td><c:out value="${question.questionCategory.name}" /></td>
																		
																		<td>																		
																			<c:if test="${(not empty question.firstAnswerList) or  (not empty question.secondAnswerList) or (not empty question.thirdAnswerList)}">
																				<c:out value="${question.questionDescription}" escapeXml="false"/><br/>
																			</c:if>
																			<c:if test="${(empty question.firstAnswerList) and  (empty question.secondAnswerList) and (empty question.thirdAnswerList)}">
																				<div style="color:red;"><c:out value="${question.questionDescription}" escapeXml="false"/></div><br/>
																			</c:if>
																		<c:if test="${not empty question.firstAnswerList}">
																			<div style="color:green;font-weight:bold">Answer 1.&nbsp;&nbsp;<c:out value="${question.firstAnswerList}" /></div><br/>
																		</c:if>
																		<c:if test="${not empty question.secondAnswerList}">
																			<div style="color:green;font-weight:bold">Answer 2.&nbsp;&nbsp;<c:out value="${question.secondAnswerList}" /></div><br/>
																		</c:if>
																		<c:if test="${not empty question.thirdAnswerList}">
																		<div style="color:green;font-weight:bold">Answer 3.&nbsp;&nbsp;<c:out value="${question.thirdAnswerList}" /></div><br/>
																		</c:if>
																		<c:if test="${question.uploadFileFlag==true}">
																		Uploaded File Name: &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/app/previewfile/${question.uploadFileId}" target="_blank">${question.uploadFileName}</a>
																		</c:if>
																		</td>	
																			
																	</c:if>
																	<c:if test="${surveyEntityMapping.erxParticipationFlag=='no1'}">
																	 <c:if test="${question.questionCategory.id!='4'}">
																		<td><c:out value="${question.questionCategory.name}" /></td>
																		<td>
																			<c:if test="${(not empty question.firstAnswerList) or  (not empty question.secondAnswerList) or (not empty question.thirdAnswerList)}">
																				<c:out value="${question.questionDescription}" escapeXml="false"/><br/>
																			</c:if>
																			<c:if test="${(empty question.firstAnswerList) and  (empty question.secondAnswerList) and (empty question.thirdAnswerList)}">
																				<div style="color:red;"><c:out value="${question.questionDescription}" escapeXml="false"/></div><br/>
																			</c:if><br/>
																		<c:if test="${not empty question.firstAnswerList}">
																			<div style="color:green;font-weight:bold">Answer 1.&nbsp;&nbsp;<c:out value="${question.firstAnswerList}" /></div><br/>
																		</c:if>
																		<c:if test="${not empty question.secondAnswerList}">
																			<div style="color:green;font-weight:bold">Answer 2.&nbsp;&nbsp;<c:out value="${question.secondAnswerList}" /></div><br/>
																		</c:if>
																		<c:if test="${not empty question.thirdAnswerList}">
																			<div style="color:green;font-weight:bold">Answer 3.&nbsp;&nbsp;<c:out value="${question.thirdAnswerList}" /></div><br/>
																		</c:if>
																		<c:if test="${question.uploadFileFlag==true}">
																		Uploaded File Name:&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/app/previewfile/${question.uploadFileId}" target="_blank">${question.uploadFileName}</a>
																		</c:if>
																		</td>	
																		</c:if>	
																	</c:if>
									                               
																</tr>
																</c:forEach>
																
																
															</tbody>
															
														</table>
													
													</div>
													</li>
													<li tabindex="24">
														<div class="form_grid_12"
															style="padding-top: 20px; padding-bottom: 20px;"
															align="right">
															<div class="form_input">
															<div style="padding-left: 28px; padding-top: 16px;">
															<form:checkbox path="iAgreeFlagBoolean" name="iAgreeFlagBoolean" id="iAgreeFlagBoolean" style="float:left;padding-right:20px;" />
                                                            <label for="iAgreeFlagBoolean">I hereby certify that the information supplied herein contains no PII/PHI and that all information </label><label style="padding-right:305px;">provided is true and accurate to the best of my knowledge.</label>
                                                     	</div>
                                                     	</div>
														</div>
                                                     </li><li tabindex="25">
														<div class="form_grid_12"
															style="padding-top: 20px; padding-bottom: 20px;"
															align="right">
															<div class="form_input">
																<button type="button" class="btn_small btn_blue" 
																	value="Print this page" onClick="window.print()" tabindex="26"><span>Print This Page</span></button>
																
																<input type="submit" class="btn_small btn_blue"
																	id="btnAction" name="btnAction" value="Submit Survey" tabindex="27" onclick="{return myFunction();}"/>																	
															</div>
														</div>
													</li>
                                                     
                                                    
												</ul>


											
										</div>
									</div>

</form:form>


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
		
		</div>
	</div>
</div>