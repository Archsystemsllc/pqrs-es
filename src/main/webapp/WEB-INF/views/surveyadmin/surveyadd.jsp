<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>

$(document).ready(function() {
	
    $('#btnAction').submit(function() {
    	alert("test");
        $("#entitySelectedList").find('option').attr('selected',true);
    });
});

$(function(){
    $("#add").on("click", function(){
        $(".entityOptions option:selected").each(function(){
           $("#entitySelectedList").append('<option selected=\'selected\' value=\''+$(this).val()+'\'>'+$(this).text()+'</option>'); 
            $('option:selected', "#entityList").remove();
        });  
    });  
    $("#delete").on("click", function(){
        $(".entitySelectedOptions option:selected").each(function(){
           $("#entityList").append('<option value=\''+$(this).val()+'\'>'+$(this).text()+'</option>');
            $('option:selected', "#entitySelectedList").remove();
        });  
    });  
});

</script>

  <style>
  
  .blue.button{
	color:#0f4b6d !important;
	
	border:1px solid #84acc3 !important;
	
	/* A fallback background color */
	background-color: #48b5f2;
	
	/* Specifying a version with gradients according to */
	
	background-image:	url('button_bg.png'), url('button_bg.png'),
						-moz-radial-gradient(	center bottom, circle,
												rgba(89,208,244,1) 0,rgba(89,208,244,0) 100px),
						-moz-linear-gradient(#4fbbf7, #3faeeb);

	background-image:	url('button_bg.png'), url('button_bg.png'),
						-webkit-gradient(	radial, 50% 100%, 0, 50% 100%, 100,
											from(rgba(89,208,244,1)), to(rgba(89,208,244,0))),
						-webkit-gradient(linear, 0% 0%, 0% 100%, from(#4fbbf7), to(#3faeeb));
}
  
  
  </style>
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
            <a href="#" style="text-decoration: none;">New Survey</a>
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
                    <h6>Add Survey</h6>
            </div>				

            <form:form modelAttribute="survey" action="${pageContext.request.contextPath}/app/admin/new-survey" method="post" class="form_container left_label">

                    <form:errors path="*" cssClass="errorblock" element="div"/>
                                    <ul>                                        
                                        <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Survey Name</label>
                                                <div class="form_input">
                                                    <form:input id="surveyName" name="surveyName" path="surveyName" class="form_container left_label"></form:input>
                                                </div>
                                            </div>
                                        </li>
                                         <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Survey Description</label>
                                                <div class="form_input">
                                                    <form:textarea id="surveyDescription" name="surveyDescription" path="surveyDescription" class="mid"></form:textarea>
                                                </div>
                                            </div>
                                        </li>  
                                         <li>
                                         
                                            <div class="form_grid_12">
                                                <label class="field_title">Survey Source</label>
                                                <div class="form_input">
                                                   <form:select id="entityTypeId" name="entityTypeId" path="entityTypeId" style="width: 300px">
					                             		<form:option value="" selected="selected">-- Choose Survey Source --</form:option>	
                                                 		<form:options items="${referenceData.pqrsEntityTypeMap}"/>
                      								</form:select>
                      						</div>
                                            </div>
                                         
                                        </li>
                                        <li>                                         
                                            <div class="form_grid_12">
                                                <label class="field_title">Survey Year</label>
                                                <div class="form_input">
                                                   <form:select id="yearId" name="yearId" path="yearId" style="width: 300px">
					                             		<form:option value="" selected="selected">-- Choose Survey Year --</form:option>	
                                                 		<form:options items="${referenceData.yearSurveyMap}"/>
                      								</form:select>
                      						</div>
                                            </div>
                                         
                                        </li>
                                       
                                      <li>
		                              	<div class="form_grid_12">
		                                    <div class="form_input" style="padding-left:4em;">
		                                        <button type="submit" class="btn_small btn_blue" name="btnAction" value="SaveSurvey" id="SaveSurvey"><span>Save</span></button>
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
