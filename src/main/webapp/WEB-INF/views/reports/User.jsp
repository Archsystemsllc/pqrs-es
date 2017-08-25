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
            <a href="${pageContext.request.contextPath}/app/questions">PQRS Entitys</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">New PQRS Entity</a>
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

            <form:form modelAttribute="pqrsEntity" action="${pageContext.request.contextPath}/app/new-pqrsentity" method="post" class="form_container left_label">

                    
                                    <ul>                                        
                                        <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Entity Name</label>
                                                <div class="form_input">
                                                    <form:input id="name" name="name" path="name" class="form_container left_label"></form:input>
                                                </div>
                                            </div>
                                        </li>
                                         <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Entity Alternate Name</label>
                                                <div class="form_input">
                                                    <form:input id="alternateName" name="alternateName" path="alternateName" class="mid"></form:input>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Entity Type</label>
	                                         <form:select id="pqrsEntityTypeId" name="pqrsEntityTypeId" path="pqrsEntityTypeId" style="width: 300px">
						                             <form:option value="0" selected="selected">-- Choose Entity Type --</form:option>	
	                                                 <form:options items="${referenceData.pqrsEntityTypeList}"/>
	                      					</form:select>
	                      					</div>
	                      				</li>                                       
                                        <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Entity Email Addresses</label>
                                                <div class="form_input">
                                                    <form:textarea id="emailAddresses" name="emailAddresses" path="emailAddresses" class="mid"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Entity Contact Numbers</label>
                                                <div class="form_input">
                                                    <form:textarea id="contactNumbers" name="contactNumbers" path="contactNumbers" class="mid"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Comments</label>
                                                <div class="form_input">
                                                    <form:textarea id="comments" name="comments" path="comments" class="mid"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                        
                                         <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Email Content</label>
                                                <div class="form_input">
                                                    <form:textarea id="emailcontent" name="emailcontent" path="emailContent" class="mid"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                  
                                      <li>
                                        <div class="form_grid_12">
                                    <div class="form_input">
                                        <button type="submit" class="btn_small btn_blue" name="btnAction" value="Update"><span>Save</span></button>
                                        <button type="submit" class="btn_small btn_blue" name="btnAction" value="Update"><span>Save & Send Email</span></button>
                                        <button type="reset" class="btn_small btn_blue" name="btnAction" value="Reset"><span>Reset</span></button>
                                    </div>
                                </div>
                            </li>
                            
                            <li>
                                        <div class="form_grid_12">
                                    <div class="form_input">
                                       <span><a href="${pageContext.request.contextPath}/app/htmlreport">user Report</a></span>
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