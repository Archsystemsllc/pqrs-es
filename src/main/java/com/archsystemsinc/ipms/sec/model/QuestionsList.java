package com.archsystemsinc.ipms.sec.model;

import java.util.List;


/**
 * model for QuestionsList
 * it has list of questions and entity response agreement certification information 
 * 
 * @author 
 * @since
 */
public class QuestionsList   {
	
	private List<Questions> questionsList;
	
	/** certification agreement flag for survey submission*/
	private boolean iAgreeFlag;

	public List<Questions> getQuestionsList() {
		return questionsList;
	}

	public void setQuestionsList(List<Questions> questionsList) {
		this.questionsList = questionsList;
	}

	public boolean isiAgreeFlag() {
		return iAgreeFlag;
	}

	public void setiAgreeFlag(boolean iAgreeFlag) {
		this.iAgreeFlag = iAgreeFlag;
	}	
}
