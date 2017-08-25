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
	
	$(document).ready( function(){

	  // bind "click" event for links with title="submit" 
	  $("a[id=navigationLink]").click( function(){
	    // it submits the form it is contained within   
	    link = $(this).attr('media');	   
	    $(this).parents("form").attr('action',link);
	    $(this).parents("form").submit();
	  });
	
	});	
	
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
									<c:if test="${not empty success}">
										<div class="successblock">
											<spring:message code="${success}"></spring:message>
										</div>
									</c:if>
									<form:form modelAttribute="questionsList"
												action="${pageContext.request.contextPath}/app/addpqrsentityresponses/4"
												method="post" enctype="multipart/form-data"
												name="pqrsDataHandlingForm">
									<form:errors path="*" cssClass="errorblock" element="div" />
									<ul id='main-nav' class='tabs'>

										<li id='1' class="corporate" tabindex="4" ><a  id="navigationLink" href="#" media="${pageContext.request.contextPath}/app/addpqrsentityresponses/1"
											 title="click here to view questions in Corporate Information">Corporate Information</a></li>
										<c:if test="${surveyEntityMapping.surveyCompleteFlag==0}">
										<li id='2' class="training" tabindex="5" ><a   id="navigationLink" href="#" media='${pageContext.request.contextPath}/app/addpqrsentityresponses/2'
											title="click here to view questions in Training">Training Information</a></li>
										<li id='3' class="current" tabindex="5" ><a href='#tab3'
											 title="click here to view questions in Data Handling"><span style="padding-right:21px;">Data Handling</span></a></li>
										<li id='4' class="quality" tabindex="6"><a id="navigationLink" href="#" media='${pageContext.request.contextPath}/app/addpqrsentityresponses/4'
											title="click here to view questions in Quality Assurance">Quality Assurance</a></li>
										<c:if test="${surveyEntityMapping.erxParticipationFlag=='yes1'}">
										<li id='5' class="erx" tabindex="7"><a id="navigationLink" href="#" media='${pageContext.request.contextPath}/app/addpqrsentityresponses/5'
											title="click here to view questions in Clinical Questions">Clinical Questions</a></li>
										</c:if>
										<li id='6' class="feedback" tabindex="8"><a id="navigationLink" href="#" media='${pageContext.request.contextPath}/app/addpqrsentityresponses/6'
											title="click here to view questions in Feed Back">Feedback</a></li>
										<li id='7' class="reviewAndSubmit" tabindex="9"><a id="navigationLink" href="#" media='${pageContext.request.contextPath}/app/addpqrsentityresponses/7'
											title="click here to view questions in Review And Submit">Review And Submit</a></li>
										</c:if>
									</ul>


									<div id='tab3'>
										<div class="clear"></div>
										<div id="featured">
											
											<input type="hidden" name="entityId"
													value="${pqrsEntity.id}" />
											<input type="hidden" name="surveyId"
													value="${surveyId}" />
											<input type="hidden" name="pageName"
													value="DataHandling" />

												<ul>
													<c:forEach var="question" items="${questionbank}"
														varStatus="counter1">
														<input type="hidden"
															name="questionsList[${counter1.index}].questionCategoryId"
															value="${question.questionCategory.id}" />
														<input type="hidden"
															name="questionsList[${counter1.index}].id"
															value="${question.id}" />
														<input type="hidden"
															name="questionsList[${counter1.index}].questionTypeId"
															value="${question.questionType.id}" />

														<c:if test="${question.questionType.id==1}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="9">
																<div class="form_grid_12">
																<label  id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">
																		<select
																			name="questionsList[${counter1.index}].firstAnswerList" class="questionselectbox">
																			<option value="">Select</option>
																			<c:forEach var="answer"
																				items="${question.answersArrayList}">
																				<option value="${answer }"
																					<c:forEach var="singleAnswer" items="${question.firstAnswerList }">
																					<c:if test="${singleAnswer==answer}">selected="selected"</c:if>
																					</c:forEach>>
																					${answer }</option>
																			</c:forEach>
																		</select>
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==2}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="10">
																<div class="form_grid_12">
																<label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">
																		<textarea rows="3" cols="40" name="questionsList[${counter1.index}].firstAnswerList" class="questiontextarea">${question.firstAnswerList[0]}</textarea>
																	<div style="padding-top:10px;">Note:&nbsp;&nbsp;Limit 4000 characters</div>
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==3}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="11">
																<div class="form_grid_12">
																<label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">
																		<select
																			name="questionsList[${counter1.index}].firstAnswerList" class="questionselectbox">
																			<option value="">Select</option>
																			<c:forEach var="answer"
																				items="${question.answersArrayList}">																				
																				<option value="${answer }"
																					<c:forEach var="singleAnswer" items="${question.firstAnswerList }">
																					<c:if test="${singleAnswer==answer}">selected="selected"</c:if>
																					</c:forEach>>
																					${answer }</option>
																			</c:forEach>
																		</select>

																	</div>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 10px;">
																		<label
																			for="questionsList[${counter1.index}].secondAnswerList">Other:</label>
																		<textarea rows="3" cols="38" name="questionsList[${counter1.index}].secondAnswerList" class="questiontextarea">${question.secondAnswerList[0]}</textarea>
																	<div style="padding-top:10px;">Note:&nbsp;&nbsp;Limit 4000 characters</div>
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==4}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="12">
																<div class="form_grid_12">
																<label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">

																		    <span>
																		<input type="radio"  id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="Yes"
																			<c:if test="${question.firstAnswerList[0]=='Yes'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonlabel">Yes</label>
																		<input type="radio" id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="No"
																			<c:if test="${question.firstAnswerList[0]=='No'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonnolabel">No</label>
																		</span>
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==5}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="13">
																<div class="form_grid_12">
																<label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">

																		    <span>
																		<input type="radio"  id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="Yes"
																			<c:if test="${question.firstAnswerList[0]=='Yes'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonlabel">Yes</label>
																		<input type="radio" id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="No"
																			<c:if test="${question.firstAnswerList[0]=='No'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonnolabel">No</label>
																		</span>
																		<div id="fileuploadoption">
																			<label
																				for="questionsList[${counter1.index}].uploadFile">Upload
																				File Name:</label> <input
																				name="questionsList[${counter1.index}].uploadFile"
																				type="file" />
																		</div>
																		<c:if test="${question.uploadFileFlag=='true'}">
																		<div id="fileuploadoption" style="padding-top: 20px;">
																				<label id="uploadFileName">Preview:&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/app/previewfile/${question.uploadFileId}" target="_blank"> ${question.uploadFileName}</a></label>
																		</div>
																		</c:if>
																		
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==6}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="14">
																<div class="form_grid_12">
																   <label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">

																		<select
																			name="questionsList[${counter1.index}].firstAnswerList"
																			multiple="multiple" class="questionselectbox" title="Hold down the Ctrl key while clicking to select multiple answers." >

																			<c:forEach var="answer"
																				items="${question.answersArrayList}">
																				<option value="${answer }"
																					<c:forEach var="singleAnswer" items="${question.firstAnswerList }">
																					<c:if test="${singleAnswer==answer}">selected="selected"</c:if>
																					</c:forEach>>
																					${answer }</option>
																			</c:forEach>
																		</select>
<div style="padding-top:10px;">Note:&nbsp;&nbsp;Hold down the Ctrl key while clicking to select multiple answers.</div>
																	</div>
																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 10px;">
																		
																		<label
																			for="questionsList[${counter1.index}].secondAnswerList">Other:</label>
																		<textarea rows="3" cols="38" name="questionsList[${counter1.index}].secondAnswerList" class="questiontextarea">${question.secondAnswerList[0]}</textarea>
																	<div style="padding-top:10px;">Note:&nbsp;&nbsp;Limit 4000 characters</div>
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==7}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="15">
																<div class="form_grid_12">
																   <label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">
																		<select
																			name="questionsList[${counter1.index}].firstAnswerList"
																			multiple="multiple"  title="Hold down the Ctrl key while clicking to select multiple answers." class="questionselectbox">
																			<c:forEach var="answer"
																				items="${question.answersArrayList}">
																				<option value="${answer }"
																					<c:forEach var="singleAnswer" items="${question.firstAnswerList }">
																					<c:if test="${singleAnswer==answer}">selected="selected"</c:if>
																					</c:forEach>>
																					${answer }</option>
																			</c:forEach>
																		</select>
																	<div style="padding-top:10px;">Note:&nbsp;&nbsp;Hold down the Ctrl key while clicking to select multiple answers.</div>
																	</div>
																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 10px;">
																		<label
																			for="questionsList[${counter1.index}].secondAnswerList">
																			Other:</label>
																		<textarea rows="3" cols="38" name="questionsList[${counter1.index}].secondAnswerList" class="questiontextarea">${question.secondAnswerList[0]}</textarea>
																	<div style="padding-top:10px;">Note:&nbsp;&nbsp;Limit 4000 characters</div>
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==8}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="16">
																<div class="form_grid_12">
																    <label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">

																		    <span>
																		<input type="radio"  id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="Yes"
																			<c:if test="${question.firstAnswerList[0]=='Yes'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonlabel">Yes</label>
																		<input type="radio" id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="No"
																			<c:if test="${question.firstAnswerList[0]=='No'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonnolabel">No</label>
																		</span>
																	</div>
																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">
																		<select
																			name="questionsList[${counter1.index}].secondAnswerList" style="width:335px">
																			<option value="">Select</option>
																			<c:forEach var="answer"
																				items="${question.answersArrayList}">
																				<option value="${answer }"
																					<c:forEach var="singleAnswer" items="${question.secondAnswerList }">
																					<c:if test="${singleAnswer==answer}">selected="selected"</c:if>
																					</c:forEach>>
																					${answer }</option>
																			</c:forEach>
																		</select>

																	</div>
																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 10px;">
																		
																		<label
																			for="questionsList[${counter1.index}].thirdAnswerList">
																			Other:</label>
																		<textarea rows="3" cols="38" name="questionsList[${counter1.index}].thirdAnswerList" class="questiontextarea">${question.thirdAnswerList[0]}</textarea>
																	<div style="padding-top:10px;">Note:&nbsp;&nbsp;Limit 4000 characters</div>
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==9}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="17">
																<div class="form_grid_12">
																    <label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">

																		<input type="text"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="${question.firstAnswerList[0] }" class="questionselectbox" />
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==10}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="18">
																<div class="form_grid_12">
																   <label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">
																		<select
																			name="questionsList[${counter1.index}].firstAnswerList" style="width:335px">
																			<option value="">Select</option>
																			<c:forEach var="answer"
																				items="${question.answersArrayList}">																				
																				<option value="${answer }"
																					<c:forEach var="singleAnswer" items="${question.firstAnswerList}">
																					<c:if test="${singleAnswer==answer}">selected="selected"</c:if>
																					</c:forEach>>
																					${answer }</option>
																			</c:forEach>
																		</select>

																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==11}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="19">
																<div class="form_grid_12">
																   <label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">
																		<select
																			name="questionsList[${counter1.index}].firstAnswerList" style="width:335px">
																			<option value="">Select</option>
																			<c:forEach var="answer"
																				items="${question.answersArrayList}">
																				<option value="${answer }"
																					<c:forEach var="singleAnswer" items="${question.firstAnswerList}">
																					<c:if test="${singleAnswer==answer}">selected="selected"</c:if>
																					</c:forEach>>
																					${answer }</option>
																			</c:forEach>
																		</select>

																	</div>
																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">

																		    <span>
																		<input type="radio"  id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="Yes"
																			<c:if test="${question.firstAnswerList[0]=='Yes'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonlabel">Yes</label>
																		<input type="radio" id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="No"
																			<c:if test="${question.firstAnswerList[0]=='No'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonnolabel">No</label>
																		</span>
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==12}">

															<li style="padding-left: 30px; padding-top: 20px;" tabindex="20">
																<div class="form_grid_12">
																   <label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">

                                                                        <span>
																		<input type="radio"  id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="Yes"
																			<c:if test="${question.firstAnswerList[0]=='Yes'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonlabel">Yes</label>
																		<input type="radio" id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="No"
																			<c:if test="${question.firstAnswerList[0]=='No'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonlabel">No</label>
																		<input type="radio" id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="Yes"
																			<c:if test="${question.firstAnswerList[0]=='NA'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonnolabel">NA</label>
																		</span>
																		
																		
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==13}">
														
														<li style="padding-left: 30px; padding-top: 20px;" tabindex="21">
																<div class="form_grid_12">
																<label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">
																		<textarea cols="60" rows="8" name="questionsList[${counter1.index}].firstAnswerList" class="questiontextarea">${question.firstAnswerList[0]}</textarea>
																		<div style="padding-top:10px;">Note:&nbsp;&nbsp;Limit 4000 characters</div>
																	</div>
																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">
																	<div id="fileuploadoption">
																			<label
																				for="questionsList[${counter1.index}].uploadFile">Upload
																				File Name:</label> <input
																				name="questionsList[${counter1.index}].uploadFile"
																				type="file" />
																		</div>
																		<c:if test="${question.uploadFileFlag=='true'}">
																		<div id="fileuploadoption" style="padding-top: 20px;">
																				<label id="uploadFileName">Preview:&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/app/previewfile/${question.uploadFileId}"> ${question.uploadFileName}</a></label>
																		</div>
																		</c:if>
																	</div>
																</div>
															</li>

														</c:if>
														<c:if test="${question.questionType.id==14}">
														
														<li style="padding-left: 30px; padding-top: 20px;" tabindex="21">
																<div class="form_grid_12">
																<label id="number-nav">${counter1.index+1}.&nbsp;&nbsp;</label>
																	<label id="question-nav"
																		for="questionsList[${counter1.index}].firstAnswerList">${question.questionDescription}</label>
																		
																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">

																			<span>
																		<input type="radio"  id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="Yes"
																			<c:if test="${question.firstAnswerList[0]=='Yes'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonlabel">Yes</label>
																		<input type="radio" id="radiobuttonquestion"
																			name="questionsList[${counter1.index}].firstAnswerList"
																			value="No"
																			<c:if test="${question.firstAnswerList[0]=='No'}">checked="checked"</c:if>>
																		<label
																			for="questionsList[${counter1.index}].firstAnswerList" id="radiobuttonnolabel">No</label>
																		</span>
																	</div>

																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 10px;">
																		
																		<textarea rows="3" cols="38" name="questionsList[${counter1.index}].secondAnswerList" class="questiontextarea">${question.secondAnswerList[0]}</textarea>
																	<div style="padding-top:10px;">Note:&nbsp;&nbsp;Limit 4000 characters</div>
																	</div>																	
																	<div class="form_input"
																		style="padding-left: 20px; padding-top: 20px;">
																	<div id="fileuploadoption">
																			<label
																				for="questionsList[${counter1.index}].uploadFile">Upload
																				File Name:</label> <input
																				name="questionsList[${counter1.index}].uploadFile"
																				type="file" />
																	</div>
																	<c:if test="${question.uploadFileFlag=='true'}">
																		<div id="fileuploadoption" style="padding-top: 20px;">
																				<label id="uploadFileName">Preview:&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/app/previewfile/${question.uploadFileId}"> ${question.uploadFileName}</a></label>
																		</div>
																		</c:if>
																	</div>
																</div>
															</li>

														</c:if>
													</c:forEach>
													<li tabindex="22">
														<div class="form_grid_12"
															style="padding-top: 20px; padding-bottom: 20px;"
															align="right">
															<div class="form_input">
																<input  type="submit" class="btn_small btn_blue"
																	id="btnAction" name="btnAction" value="Save And Exit" tabindex="23"/>
																
																<input type="submit" class="btn_small btn_blue"
																	id="btnAction" name="btnAction" value="Save And Next" tabindex="24"/>																	
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