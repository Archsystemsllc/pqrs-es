<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
							            <a href="${pageContext.request.contextPath}/app/admin/groupreport-pqrsentity">Group Report</a> >> <span></span>
							        </li>
							         <li>
							            Individual Report<span></span>
							        </li>							       
							    </ul>
							</div>
							<div id="banner">
								<div id="page-wrap">

									<form:form modelAttribute="surveyEntityMapping"
										action="${pageContext.request.contextPath}/app/submitsurvey"
										method="post" enctype="multipart/form-data"
										name="reviewAndSubmitForm">
										<div id='tab6'>
											<div class="clear"></div>
											<div id="featured">

												<ul class="TFul">
													<li tabindex="8"><span style="padding-left: 2px;">
															Entity Id:</span> <span> ${surveyEntityMapping.pqrsEntity.id}</span>
													</li>	
													<li tabindex="9"><span style="padding-left: 2px;"> 
															Entity Name: </span> <span style="align: right">${surveyEntityMapping.pqrsEntity.name}</span>
													</li>
													<li tabindex="10"><span style="padding-left: 2px;">
															Entity Point of Contact Name:</span> <span> ${surveyEntityMapping.pqrsEntity.alternateName}</span>
													</li>	
																							


												</ul>
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
																			<c:if
																				test="${surveyEntityMapping.erxParticipationFlag=='yes1'}">

																				<td><c:out
																						value="${question.questionCategory.name}" /></td>
																				<td><c:out
																						value="${question.questionDescription}"
																						escapeXml="false" /><br /> <c:if
																						test="${not empty question.firstAnswerList}">
																			Answer 1:<c:out value="${question.firstAnswerList}" />
																						<br />
																					</c:if> <c:if
																						test="${not empty question.secondAnswerList}">
																			Answer 2:<c:out value="${question.secondAnswerList}" />
																						<br />
																					</c:if> <c:if test="${not empty question.thirdAnswerList}">
																		Answer 3:<c:out value="${question.thirdAnswerList}" />
																						<br />
																					</c:if> <c:if test="${question.uploadFileFlag==true}">
																		Uploaded File Name<a
																							href="${pageContext.request.contextPath}/app/downloadFile/${question.uploadFileId}"
																							target="_blank">${question.uploadFileName}</a>
																					</c:if></td>

																			</c:if>
																			<c:if
																				test="${surveyEntityMapping.erxParticipationFlag=='no1'}">
																				<c:if test="${question.questionCategory.id!='4'}">
																					<td><c:out
																							value="${question.questionCategory.name}" /></td>
																					<td><c:out
																							value="${question.questionDescription}"
																							escapeXml="false" /><br /> <c:if
																							test="${not empty question.firstAnswerList}">
																			Answer 1:<c:out value="${question.firstAnswerList}" />
																							<br />
																						</c:if> <c:if
																							test="${not empty question.secondAnswerList}">
																			Answer 2:<c:out value="${question.secondAnswerList}" />
																							<br />
																						</c:if> <c:if
																							test="${not empty question.thirdAnswerList}">
																		Answer 3:<c:out value="${question.thirdAnswerList}" />
																							<br />
																						</c:if> <c:if test="${question.uploadFileFlag==true}">
																		Uploaded File Name<a
																								href="${pageContext.request.contextPath}/app/downloadFile/${question.uploadFileId}"
																								target="_blank">${question.uploadFileName}</a>
																						</c:if></td>
																				</c:if>
																			</c:if>

																		</tr>
																	</c:forEach>


																</tbody>

															</table>

														</div>
													</li>
													<li tabindex="25">
														<div class="form_grid_12"
															style="padding-top: 20px; padding-bottom: 20px;"
															align="right">

															<button type="button" class="btn_small btn_blue"
																value="Print this page" onClick="window.print()">
																<span>Print This Page</span>
															</button>
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