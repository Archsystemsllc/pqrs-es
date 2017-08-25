package com.archsystemsinc.ipms.sec.util;

public final class GenericConstants {

	/**
	 * Constants <br/>
	 * - Constants Used across the application in various classes
	 */
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

	public final static String ANSWER_SEPERATOR = "::::";
	public final static String IN_ANSWER_SEPERATOR = "####";
	public final static String COMMA_STRING = ",";
	public final static String ACTIVE_FLAG = "1";	
	public final static String IN_ACTIVE_FLAG = "0";
	public final static String NULL_STRING = "null";
	public static final Integer ACTIVE_INTEGER = 1;
	
	public final static int ACTIVE_INT = 1;
	public final static int IN_ACTIVE_INT = 0;
	
	public static final Long OY2_YEAR_ID = 4L;
	
	/* Survey Report Constants */
	
	public final static String SURVEY_QUESTION_AND_ANSWER_REPORT = "Survey Question & Answer Report";
	
	/* Survey Status Constants */
	public final static String SURVEY_READY_STATUS = "Ready";
	public final static String SURVEY_IN_PROGRESS_STATUS = "In Progress";
	public final static String SURVEY_COMPLETE_STATUS = "Complete";
	
	/* Emailer Releated Constants */
	public static final String EMAIL_SMTP_HOST = "ProvideHost";	
	public static final String EMAIL_SMTP_PORT = "25";
	public static final String SMTP_CONNECTION_TIMEOUT = "3000";

	/* Excel Export Names */
	public static final String QUESTIONS_EXCEL = "questions";	
	
	public final static Integer TRAINING_INTEGER = 2;
	public final static Integer DATA_HANDLING_INTEGER = 3;
	public final static Integer QA_INTEGER = 4;
	public final static Integer ERX_INTEGER = 5;
	public final static Integer FEEDBACK_INTEGER = 6;
	public final static Integer REVIEW_INTEGER = 7;
	
	public final static Long REGISTRY_LONG = 1L;
	public final static Long EHR_DSV_LONG = 2L;
	public final static Long EHR_DIRECT_LONG = 3L;
	public final static Long CLAIMS_LONG = 4L;
	public final static Long GPRO_LONG = 5L;
	public final static Long QCDR_LONG = 6L;
	
	private GenericConstants() {
		throw new AssertionError();
	}
}
