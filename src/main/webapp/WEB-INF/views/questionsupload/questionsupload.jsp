<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
		    
		    
		   /*  $('#add').click(function () {
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
			}); */
			

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
        <li>
            <a href="${pageContext.request.contextPath}/app/dashboard">Home</a> <span> >> </span>
        </li>
        <!--adding link-->
        <li>
            <a href="${pageContext.request.contextPath}/app/admin/questionsupload">Questions Upload</a> 
        </li>
        <!--<li>
            <a href="#" style="text-decoration: none;">Questions Upload</a>
        </li>-->
    </ul>
</div>
							
						</div>
					</div>
					<div class="row">
						<div class="12u">
                              <div class="row">
								<div class="8u">
					

 						<form:form modelAttribute="questionsFileUpload"
													action="${pageContext.request.contextPath}/app/admin/questionsupload"
													method="post" class="form_container left_label"
													enctype="multipart/form-data">
													<c:if test="${not empty success}">
													<br/>
														<div class="successblock">
															<spring:message code="${success}"></spring:message>
														</div>
													</c:if>
													<form:errors path="*" cssClass="errorblock" element="div" />
													<br/>
													<table width="100%">
														<tr>
															<td>
																<ul>
																	<li>
																		<div class="form_grid_12">
																			<label class="field_title">Questions File</label>
																			<div class="form_input">
																				<form:input type="file" id="questionsFile"
																					name="questionsFile" path="questionsFile" />
																			</div>
																		</div>
																	</li>
																	
																</ul>
															</td>		
														 </tr>	
													</table>
													<ul>
														<li style="padding-left: 370px;">
															<div class="form_grid_12">
																<table>
																	<tr>
																		<td valign="top"><div class="form_input">
																				<button type="submit" class="btn_small btn_blue"
																					name="btnAction" value="Upload">
																					<span>Upload</span>
																				</button>
																			</div></td>
																		<td valign="top"><div class="form_input">
																				<button type="reset" class="btn_small btn_blue"
																					name="btnAction" value="Reset">
																					<span>Reset</span>
																				</button>
																			</div></td>
																	</tr>
																</table>
															</div>

														</li>
													</ul>
												</form:form>
					
					
					
					<form:form modelAttribute="pqrsEntityFileUpload"
													action="${pageContext.request.contextPath}/app/admin/pqrsentityupload"
													method="post" class="form_container left_label"
													enctype="multipart/form-data">
													<c:if test="${not empty success}">
													<br/>
														<div class="successblock">
															<spring:message code="${success}"></spring:message>
														</div>
													</c:if>
													<form:errors path="*" cssClass="errorblock" element="div" />
													<br/>
													<table width="100%">
														<tr>
															<td>
																<ul>
																	<li>
																		<div class="form_grid_12">
																			<label class="field_title">ENTITIES File</label>
																			<div class="form_input">
																				<form:input type="file" id="pqrsEntityFile"
																					name="pqrsEntityFile" path="pqrsEntityFile" />
																			</div>
																		</div>
																	</li>
																	
																</ul>
															</td>		
														 </tr>
													</table>
													<ul>
														<li style="padding-left: 370px;">
															<div class="form_grid_12">
																<table>
																	<tr>
																		<td valign="top"><div class="form_input">
																				<button type="submit" class="btn_small btn_blue"
																					name="btnAction" value="Upload">
																					<span>Upload</span>
																				</button>
																			</div></td>
																		<td valign="top"><div class="form_input">
																				<button type="reset" class="btn_small btn_blue"
																					name="btnAction" value="Reset">
																					<span>Reset</span>
																				</button>
																			</div></td>
																	</tr>
																</table>
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
