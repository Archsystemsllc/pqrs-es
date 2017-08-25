<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

		<script>
			// Wait until the DOM has loaded before querying the document
			$(document).ready(function(){
				
				$( "input[type=radio]" ).click(function(){
					
					
					
					if ($(this).is(':checked') && $(this).val() == "no1" ) {
						
					
						$('#5').hide();
					    }
					if ($(this).is(':checked') && $(this).val() == "yes1" ){
					    	$('#5').show();
					    }
				});

				  
				$('ul.tabs').each(function()
						{
					// For each set of tabs, we want to keep track of
					// which tab is active and it's associated content
					var $active, $content, $links = $(this).find('a');
					
					
                    
					// If the location.hash matches one of the links, use that as the active tab.
					// If no match is found, use the first link as the initial active tab.
					$active = $($links.filter('[href="'+location.hash+'"]')[0] || $links[0]);
					$active.addClass('active')
					$content = $($active.attr('href'));
					//$(this).find('li').addClass('current');
					

					// Hide the remaining content
					$links.not($active).each(function () {
						$($(this).attr('href')).hide();
					});

					// Bind the click event handler
					$(this).on('click', 'a', function(e){
						// Make the old tab inactive.
						$active.removeClass('active');
						$content.hide();

						// Update the variables with the new link and content
						$active = $(this);
						$content = $($(this).attr('href'));

						// Make the tab active.
						$active.addClass('active');
						$content.show();

						// Prevent the anchor's default click action
						e.preventDefault();
					});
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
<div id="page-wrap" >
<c:if test="${not empty success}">
                        <div class="successblock"><spring:message code="${success}"></spring:message>
                        </div>
                  </c:if>
<ul id='main-nav' class='tabs' >
			
			<li id='1'><a href='#tab1'>Corporate Information</a></li>
			<li id='2'><a href='#tab2'>Training Information</a></li>
			<li id='3'><a href='#tab3'>PQRS Data Handling</a></li>
			<li id='4'><a href='#tab4'>Quality Assurance</a></li>
			<li id='5'><a href='#tab5'>Clinical Measure Questions</a></li>
			<li id='6'><a href='#tab6'>Feedback</a></li>
		</ul>
			
		<div id='tab1'>
		  
		<div class="clear"></div>
		<div id="featured">
		<form:form modelAttribute="pqrsEntity" action="${pageContext.request.contextPath}/app/new-usersurvey/${pqrsEntity.id}#tab2" method="post" name="corporateForm">
		<ul class="TFul">
		
			<li>
			<span style="padding-left:2px;"> Entity Name:
					</span>
					<span style="align:right">${pqrsEntity.name}</span>
			</li>
			<li>
			<span style="padding-left:2px;">Entity Alternate Name:</span>
			<span>
			${pqrsEntity.alternateName}</span>
				</li>
			
			<li>
			<span style="padding-left:2px;">Email Addresses:</span>
			<span>${pqrsEntity.emailAddresses}</span>
			</li>
			<li>
			<span style="padding-left:2px;">Contact Numbers:</span>
					<span>${pqrsEntity.contactNumbers}</span>
			</li>

				<!--  <li>Did you participate in eRx Program for 2012?
				 <div style="padding-bottom:12px;padding-top:8px;"><input type="radio" checked="" value="yes1" name="tabs-2" style="float: left;"><label for="tabs-2">Yes</label></div> 
                 <div><input type="radio" style="float: left;" value="no1" name="tabs-2"><label for="tabs-2">No</label> </div>
				</li>-->
	
			</ul>
			
		<div style="padding-left:30px;padding-top: 20px">Note: The following demographic information is what is currently on file for your Registry. If any of this information is not correct, please contact surveyadmin@archsystemsinc.com.</div>
							
		<div align="left" style="padding-left:30px;padding-top: 20px;padding-bottom:20px;">
			<c:if test="${sessionScope.InitialAccess=='True'}">
				<a href="${pageContext.request.contextPath}/app/new-usersurvey/${pqrsEntity.id}#tab2">
					<button class="btn_small btn_blue">Start Survey</button>
				</a>
			</c:if>
			<c:if test="${sessionScope.InitialAccess=='False'}">
				<a href="${pageContext.request.contextPath}/app/new-usersurvey/${pqrsEntity.id}#tab2">
					<button class="btn_small btn_blue">Re-Start Survey</button>
				</a>
			</c:if>
		</div>		
	</form:form>		
         </div>
         </div>
        <div id='tab2'>
		<div class="clear"></div>
			<div id="featured">
			<form:form modelAttribute="questionsList" action="${pageContext.request.contextPath}/app/addpqrsentityresponses" 
				method="post" enctype="multipart/form-data" name="trainingInformationForm">
		<input type="hidden" name="entityId" value="${pqrsEntity.id}" />
		<c:forEach var="questionList" items="${questionbank}">
			<c:if test="${questionList.key==1}">
			<ul>
				<c:forEach var="question" items="${questionList.value}" varStatus="counter1">
					<input type="hidden" name="questionsList[${counter1.index}].questionCategoryId" value="${question.questionCategory.id}" />
					<input type="hidden" name="questionsList[${counter1.index}].id" value="${question.id}" />
                    <input type="hidden" name="questionsList[${counter1.index}].questionTypeId" value="${question.questionType.id}" />
					
					<c:if test="${question.questionType.id==1}">
					              
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter1.index}].providedAnswer">${counter1.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter1.index}].providedAnswer" >
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==2}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter1.index}].providedAnswer" title="questionsList[${counter1.index}].providedAnswer">${counter1.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<textarea rows="3" cols="40" name="questionsList[${counter1.index}].providedAnswer" >
									${question.firstAnswer}
									</textarea>
                               		
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==3}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter1.index}].providedAnswer">${counter1.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                              
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter1.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               			<option value="other"
                               					<c:if test="${question.firstAnswer==other}">Selected="Selected"</c:if>                               					
                               			>
                               			Other</option>
                               		</select>
                               		
                               </div>
                              
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">
                               	 <label for="questionsList[${counter1.index}].providedAnswer">Other:</label>	
                               	<input type="text" name="questionsList[${counter1.index}].providedAnswer" value="${question.secondAnswer}"/>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==4}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter1.index}].providedAnswer">${counter1.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter1.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer=='Yes'}">checked="checked"</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter1.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer=='No'}">checked="checked"</c:if>
									>No
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==5}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter1.index}].providedAnswer">${counter1.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter1.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>
                               		<label for="questionsList[${counter1.index}].providedAnswer">Yes</label><br>
									<input type="radio" name="questionsList[${counter1.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									><label for="questionsList[${counter1.index}].providedAnswer">No</label>
								<div id="fileuploadoption">
								<label for="questionsList[${counter1.index}].uploadFile">Upload File Name:</label>
								<input name="questionsList[${counter1.index}].uploadFile" type="file" /></div>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==6}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter1.index}].providedAnswer">${counter1.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                              
                               		<select name="questionsList[${counter1.index}].providedAnswer" multiple="multiple">
                               		
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.thirdAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		 
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">    
                               <label for="questionsList[${counter1.index}].providedAnswer">Other:</label>                            		
                               	<input type="text" name="questionsList[${counter1.index}].providedAnswer" />
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==7}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter1.index}].providedAnswer">${counter1.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter1.index}].providedAnswer" multiple="multiple">
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.thirdAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">
                               		<label for="questionsList[${counter1.index}].providedAnswer"> Other:</label> 
		                           <input type="text" name="questionsList[${counter1.index}].providedAnswer" value="${question.secondAnswer}"/>
		                       </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==8}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter1.index}].providedAnswer">${counter1.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter1.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter1.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter1.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">                               		
		                               	Other:<input type="text" name="questionsList[${counter1.index}].providedAnswer" value="${question.thirdAnswer }"/>
		                       </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==9}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter1.index}].providedAnswer">${counter1.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="text" name="questionsList[${counter1.index}].providedAnswer" value="${question.firstAnswer }"/>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==10}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter1.index}].providedAnswer">${counter1.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                                <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter1.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==11}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter1.index}].providedAnswer">${counter1.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter1.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter1.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.secondAnswer==Yes}">Checked</c:if>
                               		><label class="field_title" for="questionsList[${counter1.index}].providedAnswer">Yes<br></label>
									<input type="radio" name="questionsList[${counter1.index}].providedAnswer" value="No"
									<c:if test="${question.secondAnswer==No}">Checked</c:if>
									><label class="field_title" for="questionsList[${counter1.index}].providedAnswer">No<br></label>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
				</c:forEach>
				<li >
                                <div class="form_grid_12" style="padding-top: 20px;padding-bottom: 20px;" align="right">
                                    <div class="form_input">
                                       <button type="submit" class="btn_small btn_blue" name="btnAction" value="Save1"><span>Save</span></button>
                                        <button type="submit" class="btn_small btn_blue" name="btnAction" value="SaveNext1"><span>Save And Next</span></button>
                                    </div>
                                </div>
                            </li>
			</ul>
			</c:if>
		</c:forEach>
		
		</form:form>
		</div>
		</div>
		<div id='tab3'>
		<div class="clear"></div>
			<div id="featured">
			<form:form modelAttribute="questionsList" action="${pageContext.request.contextPath}/app/addpqrsentityresponses" 
				method="post" enctype="multipart/form-data" name="pqrsdatahandlingform" >
		<input type="hidden" name="entityId" value="${pqrsEntity.id}" />
		<c:forEach var="questionList" items="${questionbank}">
			<c:if test="${questionList.key==2}">
			<ul>
				<c:forEach var="question" items="${questionList.value}" varStatus="counter2">
					<input type="hidden" name="questionsList[${counter2.index}].questionCategoryId" value="${question.questionCategory.id}" />
					<input type="hidden" name="questionsList[${counter2.index}].id" value="${question.id}" />
                    <input type="hidden" name="questionsList[${counter2.index}].questionTypeId" value="${question.questionType.id}" />
					
					<c:if test="${question.questionType.id==1}">
					              
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionDescription11">${counter2.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter2.index}].providedAnswer" >
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==2}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter2.index}].providedAnswer">${counter2.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<textarea rows="3" cols="40" name="questionsList[${counter2.index}].providedAnswer" >
									${question.firstAnswer}
									</textarea>
                               		
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==3}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter2.index}].providedAnswer">${counter2.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                              
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter2.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               			<option value="other"
                               					<c:if test="${question.firstAnswer==other}">Selected="Selected"</c:if>                               					
                               			>
                               			Other</option>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">
                               		<label for="questionsList[${counter2.index}].providedAnswer"></label> 
                               	Other:<input type="text" name="questionsList[${counter2.index}].providedAnswer" value="${question.secondAnswer}"/>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==4}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter2.index}].providedAnswer">${counter2.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter2.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter2.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==5}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter2.index}].providedAnswer">${counter2.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter2.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter2.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
								<div id="fileuploadoption">
								<label for="questionsList[${counter2.index}].uploadFile"></label>
								Upload File Name:<input name="questionsList[${counter2.index}].uploadFile" type="file" /></div>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==6}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter2.index}].providedAnswer">${counter2.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter2.index}].providedAnswer" multiple="multiple">
                               		
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.thirdAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		 
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;"> 
                               <label for="questionsList[${counter2.index}].providedAnswer"></label>                               		
                               	Other:<input type="text" name="questionsList[${counter2.index}].providedAnswer" />
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==7}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter2.index}].providedAnswer">${counter2.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter2.index}].providedAnswer" multiple="multiple">
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.thirdAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">
                               		<label for="questionsList[${counter2.index}].providedAnswer"></label> 
		                            Other:<input type="text" name="questionsList[${counter2.index}].providedAnswer" value="${question.secondAnswer}"/>
		                       </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==8}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter2.index}].providedAnswer">${counter2.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter2.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter2.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               <label for="questionsList[${counter2.index}].providedAnswer"></label>
                               		<select name="questionsList[${counter2.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">   
                               <label for="questionsList[${counter2.index}].providedAnswer"></label>                            		
		                               	Other:<input type="text" name="questionsList[${counter2.index}].providedAnswer" value="${question.thirdAnswer }"/>
		                       </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==9}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionDescription20">${counter2.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<label for="questionsList[${counter2.index}].providedAnswer"></label>
                               		<input type="text" name="questionsList[${counter2.index}].providedAnswer" value="${question.firstAnswer }"/>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==10}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter2.index}].providedAnswer">${counter2.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                                <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter2.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==11}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter2.index}].providedAnswer">${counter2.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter2.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<label class="field_title" for="questionsList[${counter2.index}].providedAnswer"></label>
                               		<input type="radio" name="questionsList[${counter2.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.secondAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter2.index}].providedAnswer" value="No"
									<c:if test="${question.secondAnswer==No}">Checked</c:if>
									>No
                               </div>
                           </div>
                       </li>
                    
					</c:if>
				</c:forEach>
				<li >
                                <div class="form_grid_12" style="padding-top: 20px;padding-bottom: 20px;" align="right">
                                    <div class="form_input">
                                       <button type="submit" class="btn_small btn_blue" name="btnAction" value="Save2"><span>Save</span></button>
                                        <button type="submit" class="btn_small btn_blue" name="btnAction" value="SaveNext2"><span>Save And Next</span></button>
                                    </div>
                                </div>
                            </li>
			</ul>
			</c:if>
		</c:forEach>
		
		</form:form>
		</div>
		</div>
		<div id='tab4'>
		<div class="clear"></div>
			<div id="featured">
			<form:form modelAttribute="questionsList" action="${pageContext.request.contextPath}/app/addpqrsentityresponses" 
				method="post" enctype="multipart/form-data" form="qualityassuranceform">
		<input type="hidden" name="entityId" value="${pqrsEntity.id}" />
		<c:forEach var="questionList" items="${questionbank}">
			<c:if test="${questionList.key==3}">
			<ul>
				<c:forEach var="question" items="${questionList.value}" varStatus="counter3">
					<input type="hidden" name="questionsList[${counter3.index}].questionCategoryId" value="${question.questionCategory.id}" />
					<input type="hidden" name="questionsList[${counter3.index}].id" value="${question.id}" />
                    <input type="hidden" name="questionsList[${counter3.index}].questionTypeId" value="${question.questionType.id}" />
					
					<c:if test="${question.questionType.id==1}">
					              
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter3.index}].providedAnswer">${counter3.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter3.index}].providedAnswer" >
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==2}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter3.index}].providedAnswer">${counter3.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<textarea rows="3" cols="40" name="questionsList[${counter3.index}].providedAnswer" >
									${question.firstAnswer}
									</textarea>
                               		
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==3}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter3.index}].providedAnswer">${counter3.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                              
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter3.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               			<option value="other"
                               					<c:if test="${question.firstAnswer==other}">Selected="Selected"</c:if>                               					
                               			>
                               			Other</option>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">
                               		<label for="questionsList[${counter3.index}].providedAnswer"></label>
                               		<label class="field_title" for="questionsList[${counter3.index}].providedAnswer"></label> 
                               	Other:<input type="text" name="questionsList[${counter3.index}].providedAnswer" value="${question.secondAnswer}"/>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==4}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter3.index}].providedAnswer">${counter3.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter3.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter3.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==5}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter3.index}].providedAnswer">${counter3.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter3.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter3.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
								<div id="fileuploadoption">
								<label for="questionsList[${counter3.index}].uploadFile"></label>
								Upload File Name:<input name="questionsList[${counter3.index}].uploadFile" type="file" /></div>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==6}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter3.index}].providedAnswer">${counter3.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter3.index}].providedAnswer" multiple="multiple">
                               		
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.thirdAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		 
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">  
                               <label class="field_title" for="questionsList[${counter3.index}].providedAnswer"></label>                              		
                               	Other:<input type="text" name="questionsList[${counter3.index}].providedAnswer" />
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==7}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter3.index}].providedAnswer">${counter3.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter3.index}].providedAnswer" multiple="multiple">
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.thirdAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">
                               		<label class="field_title" for="questionsList[${counter3.index}].providedAnswer"></label> 
		                            Other:<input type="text" name="questionsList[${counter3.index}].providedAnswer" value="${question.secondAnswer}"/>
		                       </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==8}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter3.index}].providedAnswer">${counter3.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter3.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter3.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               <label for="questionsList[${counter3.index}].providedAnswer"></label>
                               		<select name="questionsList[${counter3.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">                               		
		                               	Other:<input type="text" name="questionsList[${counter3.index}].providedAnswer" value="${question.thirdAnswer }"/>
		                       </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==9}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionDescription30">${counter3.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="text" name="questionsList[${counter3.index}].providedAnswer" value="${question.firstAnswer }"/>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==10}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionDescription31">${counter3.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                                <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                                <label for="questionsList[${counter3.index}].providedAnswer"></label>
                               		<select name="questionsList[${counter3.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==11}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter3.index}].providedAnswer">${counter3.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter3.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<label for="questionsList[${counter3.index}].providedAnswer"></label>
                               		<input type="radio" name="questionsList[${counter3.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.secondAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter3.index}].providedAnswer" value="No"
									<c:if test="${question.secondAnswer==No}">Checked</c:if>
									>No
                               </div>
                           </div>
                       </li>
                    
					</c:if>
				</c:forEach>
				<li >
                                <div class="form_grid_12" style="padding-top: 20px;padding-bottom: 20px;" align="right">
                                    <div class="form_input">
                                       <button type="submit" class="btn_small btn_blue" name="btnAction" value="Save3"><span>Save</span></button>
                                        <button type="submit" class="btn_small btn_blue" name="btnAction" value="SaveNext3"><span>Save And Next</span></button>
                                    </div>
                                </div>
                            </li>
			</ul>
			</c:if>
		</c:forEach>
		
		</form:form>
		</div>
		</div>
		<div id='tab5'>
		<div class="clear"></div>
			<div id="featured">
			<form:form modelAttribute="questionsList" action="${pageContext.request.contextPath}/app/addpqrsentityresponses" 
				method="post" enctype="multipart/form-data" name="erxform">
		<input type="hidden" name="entityId" value="${pqrsEntity.id}" />
		<c:forEach var="questionList" items="${questionbank}">
			<c:if test="${questionList.key==4}">
			<ul>
				<c:forEach var="question" items="${questionList.value}" varStatus="counter4">
					<input type="hidden" name="questionsList[${counter4.index}].questionCategoryId" value="${question.questionCategory.id}" />
					<input type="hidden" name="questionsList[${counter4.index}].id" value="${question.id}" />
                    <input type="hidden" name="questionsList[${counter4.index}].questionTypeId" value="${question.questionType.id}" />
					
					<c:if test="${question.questionType.id==1}">
					              
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter4.index}].providedAnswer">${counter4.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter4.index}].providedAnswer" >
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==2}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter4.index}].providedAnswer">${counter4.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<textarea rows="3" cols="40" name="questionsList[${counter4.index}].providedAnswer" >
									${question.firstAnswer}
									</textarea>
                               		
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==3}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter4.index}].providedAnswer">${counter4.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                              
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter4.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               			<option value="other"
                               					<c:if test="${question.firstAnswer==other}">Selected="Selected"</c:if>                               					
                               			>
                               			Other</option>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">
                               
                               	<label class="field_title" for="questionsList[${counter4.index}].providedAnswer"></label> 	
                               	Other:<input type="text" name="questionsList[${counter4.index}].providedAnswer" value="${question.secondAnswer}"/>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==4}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter4.index}].providedAnswer">${counter4.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter4.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter4.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==5}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter4.index}].providedAnswer">${counter4.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter4.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter4.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
								<div id="fileuploadoption">
								<label for="questionsList[${counter4.index}].uploadFile"></label>
								Upload File Name:<input name="questionsList[${counter4.index}].uploadFile" type="file" /></div>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==6}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter4.index}].providedAnswer">${counter4.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter4.index}].providedAnswer" multiple="multiple">
                               		
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.thirdAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		 
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;"> 
                                  <label class="field_title" for="questionsList[${counter4.index}].providedAnswer"></label>                            		
                               	Other:<input type="text" name="questionsList[${counter4.index}].providedAnswer" />
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==7}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter4.index}].providedAnswer">${counter4.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter4.index}].providedAnswer" multiple="multiple">
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.thirdAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">
                               		<label class="field_title" for="questionsList[${counter4.index}].providedAnswer"></label> 
		                            Other:<input type="text" name="questionsList[${counter4.index}].providedAnswer" value="${question.secondAnswer}"/>
		                       </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==8}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter4.index}].providedAnswer">${counter4.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter4.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter4.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               <label for="questionsList[${counter4.index}].providedAnswer"></label>
                               		<select name="questionsList[${counter4.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">                               		
		                               	Other:<input type="text" name="questionsList[${counter4.index}].providedAnswer" value="${question.thirdAnswer }"/>
		                       </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==9}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter4.index}].providedAnswer">${counter4.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="text" name="questionsList[${counter4.index}].providedAnswer" value="${question.firstAnswer }"/>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==10}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter4.index}].providedAnswer">${counter4.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                                <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter4.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==11}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter4.index}].providedAnswer">${counter4.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter4.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<label for="questionsList[${counter4.index}].providedAnswer"></label>
                               		<input type="radio" name="questionsList[${counter4.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.secondAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
									<input type="radio" name="questionsList[${counter4.index}].providedAnswer" value="No"
									<c:if test="${question.secondAnswer==No}">Checked</c:if>
									>No
                               </div>
                           </div>
                       </li>
                    
					</c:if>
				</c:forEach>
				<li >
                                <div class="form_grid_12" style="padding-top: 20px;padding-bottom: 20px;" align="right">
                                    <div class="form_input">
                                       <button type="submit" class="btn_small btn_blue" name="btnAction" value="Save4"><span>Save</span></button>
                                        <button type="submit" class="btn_small btn_blue" name="btnAction" value="SaveNext4"><span>Save And Next</span></button>
                                    </div>
                                </div>
                            </li>
			</ul>
			</c:if>
		</c:forEach>
		
		</form:form>
		</div>
		</div>
		<div id='tab6'>
		  
			<div class="clear"></div>
		<div id="featured">
		<form:form modelAttribute="questionsList" action="${pageContext.request.contextPath}/app/addpqrsentityresponses" method="post" name="feedbackform">
		<input type="hidden" name="entityId" value="${pqrsEntity.id}" />
		<c:forEach var="questionList" items="${questionbank}">
			<c:if test="${questionList.key==5}">
			<ul>
				<c:forEach var="question" items="${questionList.value}" varStatus="counter5">
					<input type="hidden" name="questionsList[${counter5.index}].questionCategoryId" value="${question.questionCategory.id}" />
					<input type="hidden" name="questionsList[${counter5.index}].id" value="${question.id}" />
                    <input type="hidden" name="questionsList[${counter5.index}].questionTypeId" value="${question.questionType.id}" />
					
					<c:if test="${question.questionType.id==1}">
					              
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer">${counter5.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter5.index}].providedAnswer" >
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==2}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer">${counter5.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<textarea rows="3" cols="40" name="questionsList[${counter5.index}].providedAnswer" >
									${question.firstAnswer}
									</textarea>
                               		
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==3}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer">${counter5.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                              
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter5.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               			<option value="other"
                               					<c:if test="${question.firstAnswer==other}">Selected="Selected"</c:if>                               					
                               			>
                               			Other</option>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">
                               		 <label for="questionsList[${counter5.index}].providedAnswer">	Other:</label> 
                               <input type="text" name="questionsList[${counter5.index}].providedAnswer" value="${question.secondAnswer}"/>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==4}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer">${counter5.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter5.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
                               		<label for="questionsList[${counter5.index}].providedAnswer"></label>
									<input type="radio" name="questionsList[${counter5.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==5}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer">${counter5.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter5.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
                               		<label for="questionsList[${counter5.index}].providedAnswer"></label>
									<input type="radio" name="questionsList[${counter5.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
								<div id="fileuploadoption">
								<label for="questionsList[${counter5.index}].uploadFile"></label>
								Upload File Name:<input name="questionsList[${counter5.index}].uploadFile" type="file" /></div>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==6}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer">${counter5.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter5.index}].providedAnswer" multiple="multiple">
                               		
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.thirdAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		 
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">   
                               <label for="questionsList[${counter5.index}].providedAnswer">Other:</label>                            		
                               	<input type="text" name="questionsList[${counter5.index}].providedAnswer" />
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==7}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer">${counter5.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                                
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter5.index}].providedAnswer" multiple="multiple">
                               			<c:forEach var="answer" items="${question.answersArrayList}">
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>
                               					<c:if test="${question.thirdAnswer==answer.answerName}">Selected="Selected"</c:if>
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">
                               		<label class="field_title" for="questionsList[${counter5.index}].providedAnswer"></label>
		                            Other:<input type="text" name="questionsList[${counter5.index}].providedAnswer" value="${question.secondAnswer}"/>
		                       </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==8}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer">${counter5.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="radio" name="questionsList[${counter5.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.firstAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
                               		<label for="questionsList[${counter5.index}].providedAnswer"></label>
									<input type="radio" name="questionsList[${counter5.index}].providedAnswer" value="No"
									<c:if test="${question.firstAnswer==No}">Checked</c:if>
									>No
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               <label for="questionsList[${counter5.index}].providedAnswer"></label>
                               		<select name="questionsList[${counter5.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.secondAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 10px;">  
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer"></label>                             		
		                               	Other:<input type="text" name="questionsList[${counter5.index}].providedAnswer" value="${question.thirdAnswer }"/>
		                       </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==9}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer">${counter5.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		
                               		<input type="text" name="questionsList[${counter5.index}].providedAnswer" value="${question.firstAnswer }"/>
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==10}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer9">${counter5.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                                <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter5.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                           </div>
                       </li>
                    
					</c:if>
					<c:if test="${question.questionType.id==11}">
					
	                   <li style="padding-left:30px;padding-top: 20px;">
                           <div class="form_grid_12">
                               <label class="field_title" for="questionsList[${counter5.index}].providedAnswer">${counter5.index+1}.&nbsp;&nbsp;${question.questionDescription}</label>
                               
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<select name="questionsList[${counter5.index}].providedAnswer">
                               			<c:forEach var="answer" items="${question.answersArrayList}" >
                               				<option value="${answer.answerName }" 
                               					<c:if test="${question.firstAnswer==answer.answerName}">Selected="Selected"</c:if>                               					
                               				>
                               				${answer.answerName }
                               				</option>
                               			</c:forEach>
                               		</select>
                               		
                               </div>
                               <div class="form_input" style="padding-left:20px;padding-top: 20px;">
                               		<label for="questionsList[${counter5.index}].providedAnswer"></label>
                               		<input type="radio" name="questionsList[${counter5.index}].providedAnswer" value="Yes"
                               			<c:if test="${question.secondAnswer==Yes}">Checked</c:if>
                               		>Yes<br>
                               		<label for="questionsList[${counter5.index}].providedAnswer"></label>
									<input type="radio" name="questionsList[${counter5.index}].providedAnswer" value="No"
									<c:if test="${question.secondAnswer==No}">Checked</c:if>
									>No
                               </div>
                           </div>
                       </li>
                    
					</c:if>
				</c:forEach>
				<li >
                    <div class="form_grid_12" style="padding-top: 20px;padding-bottom: 20px;" align="right">
                       <div class="form_input">
                           <div style="padding-left:200px;padding-top:10px;"><input type="checkbox" style="float:left;padding-right:20px;" name="icertifybox">
                                <label style="float:left;" for="icertifybox">I certify information provided is valid to my knowledge</label></div>
                          <button value="SubmitSurvey" name="btnAction" class="btn_small btn_blue" type="submit"><span>Submit Survey</span></button>
                            </div>
                    </div>
                </li>
			</ul>
			</c:if>
		</c:forEach>
		
		</form:form>
		</div>
		</div>
</div>

						   </div>

                        </div>


								
								<div class="4u">
									<section id="sidebar">
										
									</section>
								</div>
							
					<div class="row">
						<section>
							<div class="divider"></div>
						</section>
					</div>
					<div class="row" id="onecolumn">
						<div class="12u">
							<section>
								
							</section>
						</div>	
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
