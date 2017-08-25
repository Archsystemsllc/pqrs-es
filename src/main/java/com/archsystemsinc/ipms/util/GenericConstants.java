package com.archsystemsinc.ipms.util;

public final class GenericConstants {

	/**
	 * Constants <br/>
	 * - Constants Used across the application in various classes
	 */
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

	public static final String ISSUES = "Issues";
	public static final String RISKS = "Risks";
	public static final String PROGRAMS = "Programs";
	public static final String PROJECTS = "Projects";
	public static final String MEETINGS = "Meetings";
	public static final String ACTION_ITEMS = "ActionItems";
	public static final String LESSONS_LEARNED = "LessonsLearned";
	public static final String TASKS = "Tasks";
	public static final String REQUIREMENTS = "Requirements";
	//public static final String SELECTED_CHARTS = "Selected Chart IDS";
	
	public static final String YES_STRING = "Yes";
	public static final String NO_STRING = "No";
	public static final String PASS_STRING = "Pass";
	public static final String FAIL_STRING = "Fail";
	public static final Integer ACTIVE_INTEGER = 1;
	public static final Integer IN_ACTIVE_INTEGER = 0;
	/* Emailer Releated Constants */
	public static final String EMAIL_SMTP_HOST = "ProvideHost";	
	public static final String EMAIL_SMTP_PORT = "25";
	public static final String SMTP_CONNECTION_TIMEOUT = "3000";
	
	public final static String SURVEY_QUESTION_AND_ANSWER_REPORT = "Survey Question & Answer Report";
	//Reporting Method Constants
	
	public static final String REGISTRY = "Registry";
	public static final String GPRO = "Gpro";
	public static final String CLAIMS = "Claims";
	public static final String EHR_DSV = "EHR-Dsv";
	public static final String EHR_DIRECT = "EHR-Direct";
	
	//EP INFO DATA Constants Start
	public static final String INTRODUCTORY_LABEL = "Introductory Call Information:";
	public static final String EP_NAME = "EP Name";
	public static final String ENTITY_ASCTED = "Entity Associated";
	public static final String INTRODUCTORY_CALL_DATE = "Introductory Call Date";
	public static final String INTRODUCTORY_CALL_ATTENDEES = "Introductory Call Attendees";
	public static final String INTRODUCTORY_CALL_NOTES = "Introductory Call Notes";
							
	public static final String PROVIDER_INFORMATION_LABEL = "Provider Information/Organizational Structure:";
	public static final String EP_SPECIALTY = "EP Specialty";
	public static final String EP_SOLE_PRACTITIONER = "Is EP A Sole Practitioner Or Were There Other Providers In The Practice?";
	public static final String HOW_MANY_PROVIDERS = "How Many Providers Were In The Practice?";	
	public static final String WERE_THERE_OTHER_SPECIALTIES = "In 2013, Were There Other Specialties In The Practice?";
	
	public static final String PQRS_SUBMSN_LABEL = "PQRS Submission Information:";
	public static final String REPORTING_METHOD = "Reporting Method";
	public static final String REPORTING_PERIOD = "Reporting Period";
	public static final String MEASURE_REPORTED_PSV = "Measure Reported For PSV";
	public static final String MEASURE_DESC = "Measure Desc";
	public static final String VALID_EXCLUSIONS = "Total # of Valid Exclusions";
	public static final String PERF_DENOM = "Performance Denominator";
	public static final String PERF_MET = "Performance Met (Numerator)";
	public static final String PERF_NOT_MET = "Performance Not Met";
	public static final String PERF_RATE = "Performance Rate";
	public static final String ERX_INC_PYMNT = "Did the EP Participate in eRx Incentive Progam in 2013?";
	
	public static final String DATA_CLCTN_LABEL = "Data Collection Information:";
	public static final String PRACTICE_USED_CERTIFIED_EHR = "Was Your Practice Using A Certified EHR System In 2013? What Is The System?";
	public static final String PRACTICE_MANAGEMENT_SYSTEM = "Did You Use A Practice Management System In 2013? Did You Use The Functionality With The EHR Or Practice Management System";
	public static final String PMS_REASON = "If You Used The Practice Management System, Is It An Embedded Process? Can You Describe It To Me?";
	public static final String DESCRIBE_LOGIC = "Can You Describe The Logic You Used And How You Validated The Data Before Sending It?";
		
	//EP INFO DATA Constants Stop	
	
	//Patient Type Constants
	
	public static final String NUMERATOR_STRING = "Numerator";
	public static final String DENOMINATOR_STRING = "Denominator";
	public static final String EXCLUSION_STRING = "Exclusion";
	public static final String PATIENT_TYPE_HEADER = "Patient Type";
	public static final int PATIENT_TYPE = 2;	
	
	//Excel Export Report Type Constants
	
	public static final String EP_WEEKLY_REPORT = "EP WEEKLY REPORT";
	public static final String EP_AGING_REPORT = "EP AGING REPORT";
	public static final String EP_INFO_DATA = "EP INFO DATA";
	public static final String CHART_ID_REPORT = "Selected Chart IDS";
	public static final String PQRS_PSV_EP_STATUS_TO_DATE = "PQRS PSV EP Status To-Date Report";
	
	// EP Info Excel Spread Sheet Row Numbers
	public static final String ORIGINAL_ACTIVE = "Original-Active";
	public static final String SPARE_ACTIVE = "Spare-Active";
	public static final String ORIGINAL_INACTIVE = "Original-Inactive";
	public static final String SPARE_INACTIVE = "Spare-Inactive";
	
	
	
	public static final String REGISTRY_REPORTING = "Registry";
	public static final String CLAIMS_REPORTING = "Claims";
	public static final String EHR_DSV_REPORTING = "Ehr-Dsv";
	public static final String EHR_DIRECT_REPORTING = "Ehr-Direct";
	public static final String GPRO_REPORTING = "Gpro";
	//Claims Upload Sheet -- Start
	public static final String 	CLAIMS_UPLOAD_RECORD_NUM = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24";
	public static final int CLAIMS_PRVDR_FIRST_NAME  		= 0;
	public static final int CLAIMS_PRVDR_LAST_NAME 	 		= 1;
	public static final int CLAIMS_PRVDR_NPI	 			= 2;
	public static final int CLAIMS_SPECIALITY_NAME 	 		= 3;
	public static final int CLAIMS_PRVDR_MAIL_ADDR1	 		= 4;
	public static final int CLAIMS_PRVDR_MAIL_ADDR2	 		= 5;
	public static final int CLAIMS_PRVDR_MAIL_CITY	 		= 6;
	public static final int CLAIMS_PRVDR_MAIL_STATE	 		= 7;
	public static final int CLAIMS_PRVDR_MAIL_ZIP	 		= 8;
	public static final int CLAIMS_PRVDR_TEL_NUM	 		= 9;
	public static final int CLAIMS_PRVDR_EMAIL_ADDR	 		= 10;	
	public static final int CLAIMS_PRVDR_EMAIL_ADDR_2	 	= 11;	
	public static final int CLAIMS_TOTAL_VALID_EXCLUS 		= 12;	                              
	public static final int CLAIMS_PERF_DENOM_CNT	 		= 13;
	public static final int CLAIMS_PERF_MET_CNT_OR_NUMER_CNT= 14;
	public static final int CLAIMS_PERF_NOT_MET_CNT	 		= 15;
	public static final int CLAIMS_PERF_RATE 				= 16;		
	public static final int CLAIMS_MSR_ID				 	= 17;
	public static final int CLAIMS_STRATUM				 	= 18;	
	public static final int CLAIMS_TOTAL_PARTB_PFS		 	= 19;
	public static final int CLAIMS_TOTAL_PQRS_INC_AMT	 	= 20;	
	public static final int CLAIMS_PGM_PRTCPTN_OPTN 	 	= 21;	
	public static final int CLAIMS_RECORD_STATUS 		 	= 22;
	
	public static final int CLAIMS_ERX_PARTICIPATION	 	= 23;
	public static final int CLAIMS_ERX_INCENTIVE_AMOUNT  	= 24;
	
	//public static final int CLAIMS_PRVDR_NAME 	 			= 3;
	
	//Claims Upload Sheet -- End
	//EHR Upload Sheet -- Start
	public static final String 	EHR_UPLOAD_RECORD_NUM = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,20,21,22,23,24,25";
	public static final int EHR_PRVDR_FIRST_NAME  		= 0;
	public static final int EHR_PRVDR_LAST_NAME 	 	= 1;	
	public static final int EHR_PRVDR_NPI	 			= 2;
	public static final int EHR_SPECIALITY_NAME 	 	= 3;
	public static final int EHR_PRVDR_MAIL_ADDR1	 	= 4;
	public static final int EHR_PRVDR_MAIL_ADDR2	 	= 5;
	public static final int EHR_PRVDR_MAIL_CITY	 		= 6;
	public static final int EHR_PRVDR_MAIL_STATE	 	= 7;
	public static final int EHR_PRVDR_MAIL_ZIP	 		= 8;
	public static final int EHR_PRVDR_TEL_NUM	 		= 9;
	public static final int EHR_PRVDR_EMAIL_ADDR	 	= 10;	
	public static final int EHR_PRVDR_EMAIL_ADDR_2	 	= 11;
	public static final int EHR_TOTAL_VALID_EXCLUS 		= 12;	                              
	public static final int EHR_PERF_DENOM_CNT	 		= 13;
	public static final int EHR_PERF_MET_CNT_OR_NUMER_CNT= 14;
	public static final int EHR_PERF_NOT_MET_CNT	 	= 15;
	public static final int EHR_PERF_RATE 				= 16;		
	public static final int EHR_MSR_ID				 	= 17;	
	public static final int EHR_STRATUM				 	= 18;			
	public static final int EHR_TOTAL_PARTB_PFS		 	= 19;
	public static final int EHR_TOTAL_PQRS_INC_AMT	 	= 20;
	public static final int EHR_PGM_PRTCPTN_OPTN 	 	= 21;	
	public static final int EHR_RECORD_STATUS 		 	= 22;
	
	public static final int EHR_ERX_PARTICIPATION	 	= 23;
	public static final int EHR_ERX_INCENTIVE_AMOUNT  	= 24;
	public static final int EHR_ASCTD 	 				= 25;
	
	//public static final int EHR_PRVDR_NAME 	 			= 3;		
	//EHR Upload Sheet -- End
	
	//REGISTRY Upload Sheet -- Start
	public static final String 	REGISTRY_UPLOAD_RECORD_NUM = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30";
	public static final int REGISTRY_PRVDR_FIRST_NAME  		= 0;
	public static final int REGISTRY_PRVDR_LAST_NAME 	 	= 1;
	//public static final int REGISTRY_PRVDR_NAME 	 		= 3;
	public static final int REGISTRY_PRVDR_NPI	 			= 2;
	public static final int REGISTRY_SPECIALITY_NAME 	 	= 3;
	public static final int REGISTRY_PRVDR_MAIL_ADDR1	 	= 4;
	public static final int REGISTRY_PRVDR_MAIL_ADDR2	 	= 5;
	public static final int REGISTRY_PRVDR_MAIL_CITY	 	= 6;
	public static final int REGISTRY_PRVDR_MAIL_STATE	 	= 7;
	public static final int REGISTRY_PRVDR_MAIL_ZIP	 		= 8;
	public static final int REGISTRY_PRVDR_TEL_NUM	 		= 9;
	public static final int REGISTRY_PRVDR_EMAIL_ADDR	 	= 10;	
	public static final int REGISTRY_PRVDR_EMAIL_ADDR_2	 	= 11;
	public static final int REGISTRY_TOTAL_VALID_EXCLUS 	= 12;	                              
	public static final int REGISTRY_PERF_DENOM_CNT	 		= 13;
	public static final int REGISTRY_PERF_MET_CNT_OR_NUMER_CNT= 14;
	public static final int REGISTRY_PERF_NOT_MET_CNT	 	= 15;
	public static final int REGISTRY_PERF_RATE 				= 16;		
	public static final int REGISTRY_MSR_ID				 	= 17;
	public static final int REGISTRY_STRATUM				= 18;		
	public static final int REGISTRY_TOTAL_PARTB_PFS		= 19;
	public static final int REGISTRY_TOTAL_PQRS_INC_AMT	 	= 20;	
	public static final int REGISTRY_PGM_PRTCPTN_OPTN 	 	= 21;	
	public static final int REGISTRY_RECORD_STATUS 		 	= 22;	
	
	
	public static final int REGISTRY_ERX_PARTICIPATION	 	= 23;	
	public static final int REGISTRY_ERX_INCENTIVE_AMOUNT  	= 24;
	public static final int REGISTRY_ASCTD 	 				= 25;
	
	//MSR_GRP Constants
	public static final int REGISTRY_MSRGRP_TOTAL_VALID_EXCLUS= 26;	
	public static final int REGISTRY_MSRGRP_PERF_DENOM_CNT	 = 27;
	public static final int REGISTRY_MSRGRP_PERF_MET_CNT_OR_NUMER_CNT	 = 28;
	public static final int REGISTRY_MSRGRP_PERF_NOT_MET_CNT = 29;                              
	public static final int REGISTRY_MSRGRP_PERF_RATE = 30;			
		
	//REGISTRY Upload Sheet -- End
	
	//GPRO WI Upload Sheet -- Start
	public static final String 	GPRO_WI_UPLOAD_RECORD_NUM = "0,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,20,21,24,25,40";
	public static final int GPRO_WI_PRVDR_FIRST_NAME  		= 0;
	public static final int GPRO_WI_PRVDR_LAST_NAME 	 	= 2;
	public static final int GPRO_WI_PRVDR_NAME 	 			= 3;
	public static final int GPRO_WI_PRVDR_NPI	 			= 4;
	public static final int GPRO_WI_SPECIALITY_NAME 	 	= 5;
	public static final int GPRO_WI_PRVDR_MAIL_ADDR1	 	= 6;
	public static final int GPRO_WI_PRVDR_MAIL_ADDR2	 	= 7;
	public static final int GPRO_WI_PRVDR_MAIL_CITY	 		= 8;
	public static final int GPRO_WI_PRVDR_MAIL_STATE	 	= 9;
	public static final int GPRO_WI_PRVDR_MAIL_ZIP	 		= 10;
	public static final int GPRO_WI_PRVDR_TEL_NUM	 		= 11;
	public static final int GPRO_WI_PRVDR_EMAIL_ADDR	 	= 12;	
	public static final int GPRO_WI_TOTAL_VALID_EXCLUS 		= 13;	                              
	public static final int GPRO_WI_PERF_DENOM_CNT	 		= 14;
	public static final int GPRO_WI_PERF_MET_CNT_OR_NUMER_CNT= 15;
	public static final int GPRO_WI_PERF_NOT_MET_CNT	 	= 16;
	public static final int GPRO_WI_PERF_RATE 				= 17;		
	public static final int GPRO_WI_MSR_CODE				= 18;
	public static final int GPRO_WI_STRATUM				 	= 20;	
	public static final int GPRO_NAME				 		= 21;
	public static final int GPRO_WI_TOTAL_PQRS_INC_AMT	 	= 24;	
	public static final int GPRO_WI_PGM_PRTCPTN_OPTN 	 	= 25;	
	public static final int GPRO_WI_RECORD_STATUS 		 	= 40;	
		
	//GPRO_WI Upload Sheet -- End
	
	private GenericConstants() {
		throw new AssertionError();
	}
}
