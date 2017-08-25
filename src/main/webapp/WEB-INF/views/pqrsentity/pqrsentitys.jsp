<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
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
							        <li>
							            <a href="${pageContext.request.contextPath}/app/dashboard">Home</a> <span> >> </span>
							        </li>
							        <li>
							            <a href="${pageContext.request.contextPath}/app/admin/pqrsentitys">PQRS Entities</a> <span> </span>
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
						<form:form modelAttribute="entity"
											action="${pageContext.request.contextPath}/app/admin/entity/search"
											method="post" class="form_container left_label">
											<div class="widget_wrap">
												<div class="widget_top">
													<h6>Search Entities</h6>
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
																		<form:option value="0" selected="selected">-- Choose Entity Participated Source --</form:option>
																		<form:options
																			items="${referenceData.pqrsEntityTypeList}" />
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
																		<form:option value="" selected="selected">-- Choose Entity Participated Year --</form:option>
																		<form:options items="${referenceData.yearSurveyList}" />
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
					
					<h6>PQRS Entities List</h6>
					
				</div>
				<c:if test="${not empty success}">
                        <div class="successblock"><spring:message code="${success}"></spring:message>
                        </div>
                  </c:if>
                 <c:if test="${not empty error}">											   
					<div class="successblock">
						<spring:message code="${error}"></spring:message>													
					</div>					
				</c:if>
                  <!-- requestURI="${pageContext.request.contextPath}/app/admin/pqrsentitys" is changed to requestURI="" to map to search request -->
                  <display:table id="entities" name="entities"
												requestURI=""
												pagesize="10" class="display data_tbl">												
												<display:column property="name"
													title="Name" sortable="true" />
													<display:column property="alternateName"
													title="Point of Contact" sortable="true" />
													<display:column property="emailAddresses"
													title="Email Addresses" sortable="true" />
													<display:column property="contactNumbers"
													title="Contact Numbers" sortable="true" />
												<display:column property="pqrsEntityType.name"
													title="Source" sortable="true" />
												<display:column property="yearSurvey.name" title="Year"
													sortable="true" />
												<display:column title="Actions">
													<span><a class="action-icons c-edit"
														href="${pageContext.request.contextPath}/app/admin/edit-pqrsentity/${entities.id}"
														title="Edit">Edit</a></span>
													<span><a class="action-icons c-approve"
														href="${pageContext.request.contextPath}/app/admin/new-pqrsentity"
														title="Create">Create</a></span>													
													<span><a class="action-icons c-delete"
														href="${pageContext.request.contextPath}/app/admin/delete-pqrsentity/${entities.id}"
														onclick="return confirm('Entity Name: ${entities.name}, Do you want to delete this entity?')" title="Delete">Delete</a></span>
													<!--  <span><a class="action-icons c-delete" href="${pageContext.request.contextPath}/app/admin/sendemail/${survey.id}" title="Send Email">Send Email</a></span>-->
												</display:column>
											</display:table>						
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