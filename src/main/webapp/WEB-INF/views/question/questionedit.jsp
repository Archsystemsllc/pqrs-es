<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>

$(document).ready(function()
		{
	 $("#questionTypeId").change(function()
			    {
			       
			        
			       if ($(this).val() == 1 || $(this).val() == 6 
							|| $(this).val() == 3 || $(this).val() == 7
						     || $(this).val() == 10 || $(this).val() == 11|| $(this).val() == 8) 
			               {
						       $('.optionsbutton').show();
			                  
						    }
						    else $('.optionsbutton').hide(); 
			        
			                
			    
			    
			    
			    
			    });
		        
		       if ($("#questionTypeId").val() == 1 || $("#questionTypeId").val() == 6 
						|| $("#questionTypeId").val() == 3 || $("#questionTypeId").val() == 7
					     || $("#questionTypeId").val() == 10 || $("#questionTypeId").val() == 11|| $("#questionTypeId").val() == 8) 
		               {
					       $('.optionsbutton').show();
		                  
					    }
					    else $('.optionsbutton').hide(); 
		        
		                if ($("#questionTypeId").val() == 2) {
					    
		                     $('.descriptivetext1').show();
					        $('.descriptivetext1').removeClass('no-display');
					        $('.descriptivetext1').addClass('get-display');
					    }
					    else $('.descriptivetext1').hide(); 
		        
		if ($("#questionTypeId").val() == 4 || $(this).val() == 5) {
							
		    $('.yesorno1').show();
		}
					    else $('.yesorno1').hide();
		    
		    
		    
		    

		    
		    
		    $('#add').click(function () {
			    var table = $(this).closest('table');
			    console.log(table.find('input:text').length);
			    if (table.find('input:text').length < 7) {
			        var x = $(this).closest('tr').nextAll('tr');
			        $.each(x, function (i, val) {
			            val.remove();
			        });
			        table.append('<tr><td><div style="padding-top:30px;">Option : <input type="text" id="options" name="options" value="" /></div></td></tr>');
			        $.each(x, function (i, val) {
			            table.append(val);
			        });
			    }
			});
		    
		    $('#del').click(function () {
			    var table = $(this).closest('table');
			    if (table.find('input:text').length > 1) {
			        table.find('input:text').last().closest('tr').remove();
			    }
			});
		    

		});
function callalert(){
	alert("You must remove question from attached surveys before deletion!!");
}
</script>
<body onload="load()">
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
            <a href="${pageContext.request.contextPath}/app/admin/questions">Questions</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Edit Question</a>
        </li>
    </ul>
</div>
							
						</div>
					</div>
					<div class="row">
						<div class="12u">
                              <div class="row">
								<div class="8u">
                    <span class="subheader-title">Edit Question : <c:out value='${question.id }'/></span>
                    <form:form modelAttribute="question" class="form_container left_label"
                               action="${pageContext.request.contextPath}/app/admin/edit-question" method="post">
                        <c:if test="${not empty success}">
	                        <div class="successblock"><spring:message code="${success}"></spring:message>
	                        </div>
                  		</c:if>
                  		
                  		
                  		
                        <form:errors path="*" cssClass="errorblock" element="div"/>
                        <ul>
                            <li>
                                <fieldset>
                                    <legend>Question Information</legend>
                                    <ul>
                                        <li style="display: none">
                                            <div class="form_grid_12">
                                                <label class="field_title">ID</label>
                                                <div class="form_input">
                                                    <span class="uneditable-input mid">${question.id}</span>
                                                    <form:input type="hidden" id="id" name="id" path="id"></form:input>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Question Description</label>
                                                <div class="form_input">
                                                    <form:textarea id="questionDescription" name="questionDescription" path="questionDescription" class="mid"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                      
                                           <li>
                                         
                                            <div class="form_grid_12">
                                                <label class="field_title">Question Source</label>
                                                <div class="form_input">
                                                   <form:select id="entityTypeId" name="entityTypeId" path="entityTypeId" style="width: 300px">
					                             		<form:option value="" selected="selected">-- Choose Question Source --</form:option>	
                                                 		<form:options items="${referenceData.pqrsEntityTypeMap}"/>
                      								</form:select>
                      						</div>
                                            </div>
                                         
                                        </li>
                                        <li>
                                         
                                            <div class="form_grid_12">
                                                <label class="field_title">Question Year</label>
                                                <div class="form_input">
                                                   <form:select id="yearId" name="yearId" path="yearId" style="width: 300px">
					                             		<form:option value="" selected="selected">-- Choose Question Year --</form:option>	
                                                 		<form:options items="${referenceData.yearSurveyMap}"/>
                      								</form:select>
                      						</div>
                                            </div>
                                         
                                        </li>
                 
                                        <li>
                                         
                                            <div class="form_grid_12">
                                                <label class="field_title">Question Type</label>
                                                <div class="form_input">
                                                   <form:select id="questionTypeId" name="questionTypeId" path="questionTypeId" style="width: 300px">
					                             		<form:option value="" selected="selected">-- Choose Question Type --</form:option>	
                                                 		<form:options items="${referenceData.questionTypeList}"/>
                      								</form:select>
                      						</div>
                                            </div>
                                         
                                        </li>
                                         <li>
                                        
                                            <div class="form_grid_12">
                                                <label class="field_title">Question Category</label>
                                                <div class="form_input">
                                                   <form:select id="questionCategoryId" name="questionCategoryId" path="questionCategoryId" style="width: 300px">
							                             <form:option value="" selected="selected">-- Choose Question Category --</form:option>	
		                                                 <form:options items="${referenceData.questionCategoryList}"/>
							                      </form:select>
                      						</div>
                                            </div>
                                        </li>
                                        <li>
                                        
                                         	<div class="form_grid_12" >
                                         	<span class="optionsbutton" style="display:none;">
                                                <label class="field_title">Answers List</label>
                                                <div class="form_input">
                                                    <form:textarea id="answerIdString" name="answerIdString" path="answerIdString" cols="45" rows="5"></form:textarea>
                                                    Note: Please provide all your options in a comma separated format. Ex: Answer 1, Answer 2, Answer 3   
                      							</div>
                      						</span>
                                            </div>                                    
 										</li> 
 										<c:if test="${not empty error}">
					                        <div class="successblock"><spring:message code="${error}"></spring:message>
					                        </div>
				                  		</c:if>				 										
 										<c:if test="${not empty questionSurveysMap}">												
											<li>
	                                            <div class="form_grid_12">
	                                                <label class="field_title">Attached Survey(s)</label>
	                                             </div>
                                        	</li>                                        	
		                                      
		                                    <c:forEach var="survey"
											 items="${questionSurveysMap.attachedSurveyNameLinkMap}">	
				                             <li> 							
												<div class="form_grid_12">
	                                                <label class="field_title">${survey.key}</label>
	                                                <div class="form_input">
	                                                   <a href="${survey.value}" target="_blank">${survey.value}</a>
	                      							</div>
	                      						</div>	
		                                      </li>
		                                      </c:forEach>
		                                      
		                                      <c:if test="${not empty error}">		                                        
		                                        <script>													
													window.onload=callalert;
												</script>
												<!-- <script>												
													alert("You must remove question from attached surveys before deletion!!");      
												</script> -->
											  </c:if>
												
										</c:if>
 										
 										                           
                                    </ul>
                                </fieldset>
                            </li>      
                            <li>
                                <div class="form_grid_12">
                                    <div class="form_input">
                                        <button type="submit" class="btn_small btn_blue" name="btnAction" value="Save"><span>Update</span></button>
                                        
                                    </div>
                                </div>
                            </li>
                        </ul> 	
                    </form:form>
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
</div>
</div>
</body>
