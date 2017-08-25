<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
									<li><a
										href="${pageContext.request.contextPath}/app/dashboard">Home</a><span> >> </span>
									</li>
									 <!--adding link-->
									<li>
							            <a href="${pageContext.request.contextPath}/app/admin/allreport">All Reports</a>
							            <!-- All Reports --> <span> >> </span>
							        </li>	
									<li><a href="${pageContext.request.contextPath}/app/admin/groupreport-pqrsentity">Group Report</a><!-- Group Report --> <span></span>
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

												<h6>List of PQRS Entities and the surveys</h6>

											</div>
											<c:if test="${not empty success}">
												<div class="successblock">
													<spring:message code="${success}"></spring:message>
												</div>
											</c:if>

											<form:form modelAttribute="surveyentitymapping"
												action="${pageContext.request.contextPath}/app/admin/groupreport-pqrsentity"
												method="post" class="form_container left_label">
												<div class="widget_wrap">
													<div class="widget_top">
														<h6>Search Surveys Report</h6>
													</div>
													<table width="100%">
														<tr>
															<td>

																<div class="form_grid_12">
																	<div style="padding-top: 5px; padding-left: 30px">
																		<label class="field_title">Search by Source</label>
																	</div>
																	<div class="form_input"
																		style="padding-top: 5px; padding-left: 30px">
																		<form:select id="entityTypeId" name="entityTypeId"
																			path="entityTypeId" style="width: 300px">
																			<form:option value="" selected="selected">-- Choose Source --</form:option>
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
																<div class="form_grid_12"
																	style="padding-top: 5px; padding-left: 30px">
																	<div style="padding-top: 5px; padding-left: 30px">
																		<label class="field_title">Search by Year</label>
																	</div>
																	<div class="form_input"
																		style="padding-top: 5px; padding-left: 30px">
																		<form:select id="yearId" name="yearId" path="yearId"
																			style="width: 300px">
																			<form:option value="" selected="selected">-- Choose Year --</form:option>
																			<form:options items="${referenceData.yearSurveyMap}" />
																		</form:select>
																	</div>
																</div>
															</td>

														</tr>

													</table>
												</div>

											</form:form>

											<display:table id="SurveyEntityMappinglist"
												name="SurveyEntityMappinglist"
												requestURI="${pageContext.request.contextPath}/app/admin/groupreport-pqrsentity"
												pagesize="10" class="display data_tbl">
												<display:column property="pqrsEntity.id" title="Entity Id"
													sortable="true" />
												<display:column property="pqrsEntity.name" autolink="true"
													title="Entity Name" sortable="true" />
												<display:column title="Entity Survey" autolink="true">
													<span><a
														href="${pageContext.request.contextPath}/app/admin/viewreport-pqrsentity/${SurveyEntityMappinglist.pqrsEntity.id}/${SurveyEntityMappinglist.survey.id}">
															${SurveyEntityMappinglist.survey.surveyName}</a></span>
												</display:column>
												<display:column property="surveyStatus"
													title="Survey Status" sortable="true" />

											</display:table>
										</div>
									</section>

									<div class="form_grid_12" align="right">
										<div class="form_input">
											<div style="padding-right: 6em; padding-top: 3em">
												<button onclick="window.print()" value="Print this page"
													class="btn_small btn_blue" type="button"
													title="Click here to print the group report">
													<span>Print This Page</span>
												</button>
											</div>

										</div>
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
</div>