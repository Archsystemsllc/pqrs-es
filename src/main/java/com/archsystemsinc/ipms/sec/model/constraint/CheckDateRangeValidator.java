package com.archsystemsinc.ipms.sec.model.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckDateRangeValidator implements ConstraintValidator<CheckDateRange,Object> {
	
	private ObjectTypeEnum objectTypeEnum;
	
	@Override
	public void initialize(CheckDateRange constraintAnnotation) {
		this.objectTypeEnum = constraintAnnotation.value();
		
	}

	@Override
	public boolean isValid(Object arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		return false;
	}	

    /*public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {

        boolean isValid = true;
    	if (object == null)
            return isValid;
        
        if (objectTypeEnum == ObjectTypeEnum.Program) {
        	Program program = (Program) object;
        	if (program.getEndDate().before(program.getStartDate()))
        		isValid = false;
            else
            	isValid = true;        	
        }
        
        if (objectTypeEnum == ObjectTypeEnum.Project) {
        	Project project = (Project) object;
        	if (project.getEndDate().before(project.getStartDate()))
        		isValid = false;
            else
            	isValid = true;        	
        }
        
        if(!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate(objectTypeEnum + " End date cannot be greater than the Start date!").addConstraintViolation();
        }
        
        return isValid;
        
    }*/

	
}