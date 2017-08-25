package com.archsystemsinc.ipms.sec.webapp.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.archsystemsinc.ipms.sec.model.Questions;
import com.archsystemsinc.ipms.sec.model.QuestionsList;

@Component
public class SurveyValidator implements Validator{

	@Override
	public boolean supports(final Class clazz) {
		// just validate the Program instances
		return QuestionsList.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(final Object target, final Errors errors) {

		final QuestionsList questionsList = (QuestionsList) target;
		
		if(questionsList.getQuestionsList() != null) {
			
			boolean messageAdded = false;
			for(Questions question: questionsList.getQuestionsList()) {
					if(messageAdded) {
						break;
					}
					switch(question.getQuestionTypeId().intValue()) {
					
						case 1: 
							if(question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0) {
								 
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
							}
						case 2: 
							if(question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0) {
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
							}
						case 3:  
							if((question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0)
								&& (question.getSecondAnswerList() == null || question.getSecondAnswerList().size() ==0)) {
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
							}
						case 4: 
							if(question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0) {
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
							}
						case 5: 
							if((question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0)
									&& (question.getSecondAnswerList() == null || question.getSecondAnswerList().size() ==0)) {
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
							}
						case 6:  
							if((question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0)
									&& (question.getSecondAnswerList() == null || question.getSecondAnswerList().size() ==0)) {
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
							}
						case 7:   
							if((question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0)
									&& (question.getSecondAnswerList() == null || question.getSecondAnswerList().size() ==0)) {
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
							}
						case 8:    
							if((question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0)
								&& (question.getSecondAnswerList() == null || question.getSecondAnswerList().size() ==0)
									&& (question.getThirdAnswerList() == null || question.getThirdAnswerList().size() ==0)) {
							errors.rejectValue("iAgreeFlag", "survey.requiredfields");
							messageAdded = true;
							break;
						}
						case 9:  
							if(question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0) {
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
							}
						case 10:  
							if(question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0) {
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
							}
						case 11:   
							if((question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0)
									&& (question.getSecondAnswerList() == null || question.getSecondAnswerList().size() ==0)) {
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
							}
						case 12:    
								if((question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0)
										&& (question.getSecondAnswerList() == null || question.getSecondAnswerList().size() ==0)) {
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
						}
						case 13:    
								if((question.getFirstAnswerList() == null || question.getFirstAnswerList().size() ==0)
										&& (question.getSecondAnswerList() == null || question.getSecondAnswerList().size() ==0)) {
								errors.rejectValue("iAgreeFlag", "survey.requiredfields");
								messageAdded = true;
								break;
						}
					}
			
				}
		
		
		
		}
	}
}