<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.misc
{
padding-left:30px;
}

a:link {
    color:rgb(1,82,157);
    background-color:transparent;
    text-decoration:none;
}
a:visited {
    color:rgb(1,82,157);
    background-color:transparent;
    text-decoration:none;
}
a:hover {
    color:rgb(234,207,13);
    background-color:transparent;
    text-decoration:underline;
}
a:active {
    color:rgb(234,207,13);
    background-color:transparent;
    text-decoration:underline;
}
</style>
<div id="page-wrapper">
	<div id="page-bgtop">
		<div id="page-bgbtm">
			<div id="page" class="5grid-layout">
				<div id="page-content-wrapper">
					<div class="row">
<div class="12u">
<div align="top" style="padding-left: 39px; padding-right: 35px;">
<a href="http://www.cms.gov" target="_blank">
<img width="1109px"  alt="CMS logo" src="${pageContext.request.contextPath}/resources/images/PQRS-eRX-Electronic-Survey-web-header.gif" title="Picture of Lady doctor"></a>
</div>
						</div>
						</div>
					</div>
					<div class="row">
						<div class="12u">
							<div class="row">	
								<div class="11u">
									<section id="content">
									<c:if test="${not empty success}">
										<div class="successblock">
											<spring:message code="${success}"></spring:message>
										</div>
									</c:if>
										<h2>About The Survey</h2>
<p align="justify" style="padding-top:10px;" tabindex="4" title="The Physician Quality Reporting System (PQRS)/Electronic Prescribing (eRx) Incentive Programs Electronic Survey (Survey) for Registries was developed under CMS&#39;
 guidance as they continue to work towards improving data quality from the EHR reporting option.
 The survey is a data collection tool to facilitate the identification, creation and deployment of process improvements,
  as well as the development of data quality recommendations for CMS, as the agency moves towards value based purchasing 
  and as they expand public reporting of performance information on Physician Compare." onmousemove="">The Physician Quality Reporting System (PQRS)/Electronic Prescribing (eRx) Incentive Programs Electronic Survey (Survey) for EHR was developed under CMS&#39;
 guidance as they continue to work towards improving data quality from the EHR reporting option.
 The survey is a data collection tool to facilitate the identification, creation and deployment of process improvements,
  as well as the development of data quality recommendations for CMS, as the agency moves towards value based purchasing 
  and as they expand public reporting of performance information on Physician Compare.</p>
  <p><div style="padding-top:1.2em;font-weight:bold;" title="Click here to open the Physician Quality Reporting System (PQRS) page on CMS&#39; website in a new window."><a href="http://www.cms.gov/Medicare/Quality-Initiatives-Patient-Assessment-Instruments/pqrs/index.html" target="_blank" title="Click here to open the Physician Quality Reporting System (PQRS) page on CMS&#39; website in a new window.">Click here to open the Physician Quality Reporting System (PQRS) page on CMS&#39; website in a new window.</a></div>
  <div style="padding-top:1.2em;font-weight:bold;" title="Survey Schedule"><p>Your customized survey link will remain active for 14 days.  If you need more time to complete the survey please contact surveyadmin@archsystemsinc.com</p></div>
								
									</section>
								
							</div>
						</div>
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
