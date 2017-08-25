<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>

  
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
            <a href="#" style="text-decoration: none;">Send Email</a>
        </li>
    </ul>
</div>
							
						</div>
					</div>
					<div class="row">
						<div class="12u">
                              <div class="row">
								<div class="8u">
								<section id="content">
<div class="grid_12 full_block">
			<div class="widget_wrap">	
			<div class="widget_top">
			      <span class="h_icon list_image"></span>
                    <h6>Add PQRS Entity</h6>
            </div>				

            <form:form modelAttribute="surveyEntityMapping" action="${pageContext.request.contextPath}/app/admin/sendemail" method="post" class="form_container left_label">

                    
                                    <ul> 
	                                    <li>
											<div class="form_grid_12">
												<label class="field_title">Survey Name</label>
												<div class="form_input">
													<span class="uneditable-input mid">${survey.surveyName}</span>
													<input type="hidden" name="surveyId" value="${survey.id}" />
												</div>
											</div>
										</li>
                                    	<li>
                                         
                                            <div class="form_grid_12">
                                                <label class="field_title">Select Entity To Send Email</label>
                                                <div class="form_input">
                                                   <form:select id="surveyEntityId" name="surveyEntityId" path="surveyEntityId" style="width: 300px">
					                             		<form:option value=" " selected="selected">-- Choose Survey --</form:option>	
                                                 		<form:options items="${surveyAssignedList}"/>
                      								</form:select>
                      						</div>
                                            </div>
                                         
                                        </li>   
                                      <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Email Subject</label>
                                                <div class="form_input">
                                                    <form:input id="emailSubject" name="emailSubject" path="emailSubject" class="mid"></form:input>
                                                </div>
                                            </div>
                                        </li>                            
                                         <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Email Content</label>
                                                <div class="form_input">
                                                    <form:textarea id="emailContent"  path="emailContent" class="mid" rows="10" cols="80" name="Richtextarea"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                        
                                        <li>
                                        <div class="form_grid_12">
                                    		<div class="form_input">
                                       
                                        	<button type="submit" class="btn_small btn_blue" name="btnAction" value="sendEmail"><span>Send Email</span></button>
                                       
                                    		</div>
                                		</div>
                            			</li>
                        			</ul>
                      </form:form>
          </div>
          </div>
                    
</section>
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
