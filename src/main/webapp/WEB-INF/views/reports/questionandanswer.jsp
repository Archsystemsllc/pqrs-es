<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script>
function callalert(){
	alert("Question Year and Question Source are required fields!");
}
</script>
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
							            <!-- All Reports --> <span> >> </span>
							        </li>						
							        <li> <a href="${pageContext.request.contextPath}/app/admin/questionandanswer">Question & Answer Report</a>
							            <!-- Question & Answer Report --> <span></span>
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
				<form:form modelAttribute="surveyEntityMapping"
											action="${pageContext.request.contextPath}/app/admin/questionandanswerreport/search"
											method="post" class="form_container left_label">
											<div class="widget_wrap">
												<div class="widget_top">
													<h6>Search Entities</h6>
												</div>
												<table width="100%">
													<tr>
														<td>

															<div class="form_grid_12">															
															    <div>
															    <c:if test="${not empty fileuploaderror}">											                        
											                        <script>	
											                         	window.onload=callalert;																		     
																	</script> 
																	<c:set var="fileuploaderror" value="" scope="session"  />
									                  			</c:if>	
															    </div>
															    
																<div style="padding-top: 5px; padding-left: 30px">
																	<label class="field_title">Search by Source</label>
																</div>
																<div class="form_input">
																	<form:select id="entityTypeId" name="entityTypeId"
																		path="entityTypeId" style="width: 300px">
																		<form:option value="" selected="selected">-- Choose Question Source --</form:option>
																		<form:options
																			items="${referenceData.pqrsEntityTypeMap}" />
																	</form:select>
																</div>

																<div
																	style="padding-top: 10px; padding-left: 32px; padding-bottom: 20px">
																	<button type="submit" class="btn_small btn_blue"
																		name="btnAction" value="search" id="add">
																		<span>Download</span>
																	</button>
																</div>
															</div>
														</td>
														<td>
															<div class="form_grid_12">
																<div style="padding-top: 5px; padding-left: 30px">
																	<label class="field_title">Search by Year</label>
																</div>
																<div class="form_input">
																	<form:select id="yearId" name="yearId" path="yearId"
																		style="width: 300px">
																		<form:option value="" selected="selected">-- Choose Question Year --</form:option>
																		<form:options items="${referenceData.yearSurveyMap}" />
																	</form:select>
																</div>

																
															</div>

														</td>

													</tr>

												</table>
											</div>

										</form:form>
				<div class="widget_wrap">
			
                  
                  <%--   <table class="display data_tbl">
                        <thead>
                        <tr>
								<th>Entity Id</th>
								<th>Entity Name</th>
								<th>Entity Survey</th>
								<th>Survey Status</th>
								
								
							</tr>
						</thead>
						<tbody>
						<c:forEach var="SurveyEntityMapping" items="${SurveyEntityMappinglist}">
						    
							<tr>
							    <td><a href="${pageContext.request.contextPath}/app/admin/viewreport-pqrsentity/${SurveyEntityMapping.pqrsEntity.id}/${SurveyEntityMapping.survey.id}" title="Click here to view the individual report"><c:out value="${SurveyEntityMapping.pqrsEntity.id}" /></a></td>
								<td><a href="${pageContext.request.contextPath}/app/admin/viewreport-pqrsentity/${SurveyEntityMapping.pqrsEntity.id}/${SurveyEntityMapping.survey.id}" title="Click here to view the individual report"><c:out value="${SurveyEntityMapping.pqrsEntity.name}" /></a></td>
								<td><a href="${pageContext.request.contextPath}/app/admin/viewreport-pqrsentity/${SurveyEntityMapping.pqrsEntity.id}/${SurveyEntityMapping.survey.id}" title="Click here to view the individual report"><c:out value="${SurveyEntityMapping.survey.surveyName}" /></a></td>
								<td><c:out value="${SurveyEntityMapping.surveyStatus}" /></td>	
		                 
							</tr>
							
							</c:forEach>
						</tbody>
					</table> --%>
				
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