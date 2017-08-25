<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript">
$(document).ready(function(){
	 
    $('html, body').animate({
        scrollTop: $('#top_up').offset().top
    }, 'fast');
});
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
									<li><a
										href="${pageContext.request.contextPath}/app/dashboard">Home</a>
										<span> >> </span></li>
									<li><a
										href="${pageContext.request.contextPath}/app/admin/surveys">Surveys</a>
										<span> </span></li>
								</ul>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="12u">
							<div class="row">
								<div class="12u">

									<section id="content">
										<form:form modelAttribute="Survey"
											action="${pageContext.request.contextPath}/app/admin/survey/search"
											method="post" class="form_container left_label">
											<div class="widget_wrap">
												<div class="widget_top" id="top_up">
													<h6>Search Survey</h6>
												</div>
												<table width="100%">
													<tr>
														<td>

															<div class="form_grid_12">
																<div style="padding-top: 5px; padding-left: 30px">
																	<label class="field_title">Search by Source</label>
																</div>
																<div class="form_input" style="padding-top: 5px; padding-left: 30px">
																	<form:select id="entityTypeId" name="entityTypeId"
																		path="entityTypeId" style="width: 300px">
																		<form:option value="0" selected="selected">-- Choose Survey Source --</form:option>
																		<form:options
																			items="${referenceData.pqrsEntityTypeMap}" />
																	</form:select>
																</div>

																<div
																	style="padding-top: 10px; padding-left: 32px; padding-bottom: 20px">
																	<button type="submit" class="btn_small btn_blue"
																		name="btnAction" value="search" id="add">
																		<span>Search</span>
																	</button>
																</div>
															</div>
														</td>
														<td>
															<div class="form_grid_12" style="padding-top: 5px; padding-left: 30px">
																<div style="padding-top: 5px; padding-left: 30px">
																	<label class="field_title">Search by Year</label>
																</div>
																<div class="form_input" style="padding-top: 5px; padding-left: 30px">
																	<form:select id="yearId" name="yearId" path="yearId"
																		style="width: 300px">
																		<form:option value="" selected="selected">-- Choose Survey Year --</form:option>
																		<form:options items="${referenceData.yearSurveyMap}" />
																	</form:select>
																	<form:errors path="yearId" style="color:red;"></form:errors>
																</div>															
															</div>
														</td>

													</tr>

												</table>
											</div>

										</form:form>
										<div class="widget_wrap">
											<div class="widget_top">

												<h6>Survey's List</h6>

											</div>
											<c:if test="${not empty success}">
												<div class="successblock">
													<spring:message code="${success}"></spring:message>
												</div>
											</c:if>
											<!-- requestURI="${pageContext.request.contextPath}/app/admin/surveys" changed to requestURI="" to map to the search request -->
											<display:table id="surveys" name="surveys"
												requestURI=""
												pagesize="10" class="display data_tbl">
												<display:column property="surveyName" title="Survey Name"
													sortable="true" />
												<display:column property="surveyDescription"
													title="Survey Description" sortable="true" />
												<display:column property="pqrsEntityType.name"
													title="Source" sortable="true" />
												<display:column property="yearSurvey.name" title="Year"
													sortable="true" />
												<display:column title="Actions">
													<span><a class="action-icons c-edit"
														href="${pageContext.request.contextPath}/app/admin/edit-survey/${surveys.id}"
														title="Edit">Edit</a></span>
													<span><a class="action-icons c-approve"
														href="${pageContext.request.contextPath}/app/admin/new-survey"
														title="Create">Create</a></span>
													<span><a class="action-icons c-delete" 
														href="${pageContext.request.contextPath}/app/admin/delete-survey/${surveys.id}"
														title="Delete" onclick="return confirm('Survey Name: ${surveys.surveyName}, Do you want to delete this survey?')" >Delete</a></span>
													<!--  <span><a class="action-icons c-delete" href="${pageContext.request.contextPath}/app/admin/sendemail/${survey.id}" title="Send Email">Send Email</a></span>-->
												</display:column>
											</display:table>
										</div>

									</section>

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
</div>