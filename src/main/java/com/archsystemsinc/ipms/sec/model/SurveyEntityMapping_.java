package com.archsystemsinc.ipms.sec.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


/**
 * static meta model properties for SurveyEntityMapping meta data access
 * no need of instantiation
 * 
 * @author 
 * @since
 */
@StaticMetamodel(SurveyEntityMapping.class)
public abstract class SurveyEntityMapping_ {

	public static volatile SingularAttribute<SurveyEntityMapping, String> yearSurvey;
	public static volatile SingularAttribute<SurveyEntityMapping, PqrsEntityType> pqrsEntityType;
	public static volatile SingularAttribute<SurveyEntityMapping, Integer> recordStatus;
	public static volatile SingularAttribute<SurveyEntityMapping, String> surveyCompleteFlag;
	
}