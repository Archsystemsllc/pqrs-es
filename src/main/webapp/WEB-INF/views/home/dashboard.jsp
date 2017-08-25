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

/*highlighting links to CMS website*/
.home-links a:link {
    color:rgb(1,82,157);
    background-color:transparent;
    text-decoration:none;
}
.home-links a:visited {
    color:rgb(1,82,157);
    background-color:transparent;
    text-decoration:none;
}
.home-links a:hover {
    color:rgb(234,207,13);
    background-color:transparent;
    text-decoration:underline;
}
.home-links a:active {
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
<img width="509px" height="264px;" alt="CMS logo" src="${pageContext.request.contextPath}/resources/images/cmslogo.JPG" title="Click here to view the CMS website( Link opens in new window)" ></a>
<img width="509px" height="264px;" src="${pageContext.request.contextPath}/resources/images/pics01.jpg" alt="Picture of Lady doctor" title="Picture of Lady doctor"></div>
						</div>
						</div>
					</div>
					<div class="row">
						<div class="12u">
							<div class="row">	
								<div class="8u">
									<section id="content">
									<c:if test="${not empty success}">
										<div class="successblock">
											<spring:message code="${success}"></spring:message>
										</div>
									</c:if>
										<h2>About The Survey</h2>
<p align="justify" style="padding-top:10px;" tabindex="4">The Physician Quality Reporting System (PQRS)/Electronic Prescribing (eRx) Incentive Programs Electronic Survey for Reporting Entities (Survey) was developed under CMS' guidance as the agency continues working to improve data quality from all reporting entities. The survey is a data collection tool to facilitate identifying, creating, and deploying process improvements, as well as developing data quality recommendations for CMS, as the agency moves toward value-based purchasing and as it expands public reporting of performance information on Physician Compare
 Guidance as they continue to work towards improving data quality from the Registry reporting option.
 The survey is a data collection tool to facilitate the identification, creation and deployment of process improvements,
  as well as the development of data quality recommendations for CMS, as the agency moves towards value based purchasing 
  and as they expand public reporting of performance information on Physician Compare.</p>
  <p><div class="home-links" style="padding-top:1.2em;"><a href="http://www.cms.gov/Medicare/Quality-Initiatives-Patient-Assessment-Instruments/pqrs/index.html" target="_blank" title="Click here to open Physician Quality Reporting System (Opens in New window)"> Click here to open Physician Quality Reporting System (Opens in New window)</a> </div>
  <div class="home-links" style="padding-top:1.2em;"><a href="http://www.cms.gov/Medicare/Quality-Initiatives-Patient-Assessment-Instruments/ERxIncentive/index.html?redirect=/erxincentive" target="_blank" title="Click here to open Electronic Prescribing (eRx) Incentive Program (Opens in New window)"> Click here to open Electronic Prescribing (eRx) Incentive Program (Opens in New window) </a> </div></p>
										
									</section>
								</div>
												<div class="4u">
									<section id="sidebar">
										<h2 title="Survey Timeline">Survey Timeline</h2>
										<p class="subtitle" title="start and end dates for survey">start and end dates for survey</p>
										<ul class="style1">
											<li class="first" tabindex="5">
												<p class="date" title="March 30">March<strong>30</strong></p>
												<p style="font-size:15px;height:90px;" title="Survey period opens and invitations with survey link have been sent to Reporting Entities.">Survey period opens and invitations with survey link have been sent to Reporting Entities.</p>
											</li>
											<li tabindex="6">
												<p class="date" title="March 20">July<strong>12</strong></p>
												<p style="font-size:15px;height:90px;" title="Survey responses are due back from Reporting Entities.">Survey responses are due back from Reporting Entities.</p>
											</li>
											<li tabindex="7">
												<p class="date" title="April 20">April<strong>20</strong></p>
												<p style="font-size:15px;height:90px;" title="First reminder, via email, if completed survey is not received by March 2.">First reminder, via email, if completed survey is not received by April 20.</p>
											</li>
											<li tabindex="8">
												<p class="date" title="April 22">April<strong>22</strong></p>
												<p style="font-size:15px;height:90px;" title="Second reminder, via email, if completed survey is not received.">Second reminder, via email, if completed survey is not received.</p>
											</li>
											<li tabindex="9">
												<p class="date" title="April 24">April<strong>24</strong></p>
												<p style="font-size:15px;height:90px;" title="Third reminder, via email, if completed survey is not received.">Third reminder, via email, if completed survey is not received.</p>
											</li>
											<li tabindex="10">
												<p class="date" title="March 13">April<strong>28</strong></p>
												<p style="font-size:15px;height:90px;" title="Fourth and final reminder, via email, if completed survey is not received.">Fourth and final reminder, via email, if completed survey is not received.</p>
											</li>
											<li tabindex="10">
												<p class="date" title="March 20">July<strong>12</strong></p>
												<p style="font-size:15px;height:90px;" title="Survey period closes for all participants.">Survey period closes for all participants.</p>
											</li>
										</ul>
										</section>
								</div>
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
