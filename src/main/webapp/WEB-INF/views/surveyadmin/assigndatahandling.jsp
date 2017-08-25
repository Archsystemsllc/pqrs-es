<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

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
							            <a href="${pageContext.request.contextPath}/app/admin/surveys">Surveys</a> <span> >> </span>
							        </li>
							         <li>
							            <a href="${pageContext.request.contextPath}/app/admin/edit-survey/${surveyid}">Edit Survey</a> <span> >> </span>
							        </li>
							         <li>
							            <a >Add Data Handling Questions</a> <span> </span>
							        </li>							       
							    </ul>
							</div>
							<div id="banner">
								<div id="page-wrap">
									<c:if test="${not empty success}">
										<div class="successblock">
											<spring:message code="${success}"></spring:message>
										</div>
									</c:if>
									<form:form modelAttribute="questionsList"
												action="${pageContext.request.contextPath}/app/admin/saveassignquestion/${surveyid}/2"
												method="post" enctype="multipart/form-data"
												name="assignDataHandlingForm">
									<form:errors path="*" cssClass="errorblock" element="div" />
									<ul id='main-nav' class='tabs'>
																			
										<li id='1' class="training"><a href='${pageContext.request.contextPath}/app/admin/viewassignquestion/${surveyid}/1'>Training Information</a></li>
										<li id='2' class="current"><a href='#tab2' title="PQRS Data Handling">PQRS Data Handling</a></li>
										<li id='3' class="quality"><a href='${pageContext.request.contextPath}/app/admin/viewassignquestion/${surveyid}/3' title="Quality Assurance">Quality Assurance</a></li>										
										<li id='4' class="erx"><a href='${pageContext.request.contextPath}/app/admin/viewassignquestion/${surveyid}/4' title="Clinical Questions">Clinical Questions</a></li>										
										<li id='5' class="feedback"><a href='${pageContext.request.contextPath}/app/admin/viewassignquestion/${surveyid}/5' title="Feed Back">Feedback</a></li>
										
									</ul>


									<div id='tab2'>
										<div class="clear"></div>
										<div id="featured">
											
												<ul>
													<c:forEach var="question" items="${questionbank}"
														varStatus="counter1">
														<input type="hidden"
															name="questionsList[${counter1.index}].questionCategoryId"
															value="${question.questionCategory.id}" />
														<input type="hidden"
															name="questionsList[${counter1.index}].id"
															value="${question.id}" />
																									

															<li style="padding-left: 30px; padding-top: 20px;">
																<div class="form_grid_12">
																<label style="float:left;"><input type="checkbox"  id="checkboxquestion"
																			name="questionsList[${counter1.index}].questionSelected"
																			
																			<c:if test="${question.questionSelected=='true'}">checked='checked'</c:if>></label>
																<label  id="number-nav"> ${counter1.index+1}</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].questionSelected">${question.questionDescription}</label>																
																</div>
															</li>

																												
													</c:forEach>
													<li>
														<div class="form_grid_12"
															style="padding-top: 20px; padding-bottom: 20px;"
															align="right">
															
															<a href='${pageContext.request.contextPath}/app/admin/edit-survey/${surveyid}' title="Edit Survey">
															<button type="button" class="btn_small btn_blue"
																	name="btnAction" value="BackButton">
																	<span>Back To Survey</span>
																</button>
															</a>
																<button type="submit" class="btn_small btn_blue"
																	name="btnAction" value="Assign" >
																	<span>Assign Questions</span>
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