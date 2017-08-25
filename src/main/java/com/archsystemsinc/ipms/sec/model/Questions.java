package com.archsystemsinc.ipms.sec.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.web.multipart.MultipartFile;

import com.archsystemsinc.ipms.common.EvaluateValidation;
import com.archsystemsinc.ipms.common.INameableEntity;
import com.archsystemsinc.ipms.common.SearchValidation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * persistence domain model for Questions 
 * it defines question type, category and entity type of the question 
 * 
 * 
 * @author 
 * @since
 */
@Entity
@XmlRootElement
@XStreamAlias("CreateQuestion")
@Table(name = "questions")
public class Questions  implements INameableEntity, Comparable<Questions> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questionid")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
	/** question type*/
	@ManyToOne
	@JoinColumn(name = "questiontypeid")
	private QuestionType questionType;	

	@ManyToOne
	@JoinColumn(name = "questioncategoryid")
	private QuestionCategory questionCategory;
	
	/** answer options for the question*/
	@Column(name = "answerid_string")
	private String answerIdString;
	
	@Column(name = "question_description", nullable = true)
	private String questionDescription;
	
	@Column(name = "categoryname", nullable = true)
	private String categoryName;
	
	@Column(nullable = true)
	private String createdBy;
	
	@Column(nullable = true)
	private String updatedBy;
	
	@Column(name = "createddate", nullable = true)
	@OrderBy("createddate asc")
	private Date createdDate;
	
	@Column(name = "updateddate", nullable = true)
	@OrderBy("updateddate asc")
	private Date updatedDate;
	
	@Transient
	@NotNull(groups={EvaluateValidation.class})
	private Long questionTypeId;
	
	@Transient
	@NotNull(groups={EvaluateValidation.class})
	private Long questionCategoryId;
	
	@Transient
	private String answersList;
	
	@Transient
	private String providedAnswer;
	
	@Transient
	private ArrayList answersArrayList;
	
	@Transient
	private MultipartFile uploadFile;
	
	@Transient
	private boolean uploadFileFlag;
	
	@Transient
	private String uploadFileName;
	
	@Transient
	private Long uploadFileId;
	
	@Transient
	private ArrayList<String> firstAnswerList;
	
	@Transient
	private ArrayList<String> secondAnswerList;
	
	@Transient
	private ArrayList<String> thirdAnswerList;	
	
	@Transient
	private boolean questionSelected;	
	
	@ManyToOne
	@JoinColumn(name = "year_id")
	private YearSurvey yearSurvey;
	
	@ManyToOne
	@JoinColumn(name = "entity_type_id")
	private PqrsEntityType pqrsEntityType;
	
	@Transient
	@NotNull(groups={EvaluateValidation.class})
	private Long entityTypeId;
	
	@Transient
	@NotNull(groups={EvaluateValidation.class, SearchValidation.class})
	private Long yearId;
	
	@Column(name = "record_status",nullable = true)
	private int recordStatus;	
	
	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Long getEntityTypeId() {
		return entityTypeId;
	}

	public void setEntityTypeId(Long entityTypeId) {
		this.entityTypeId = entityTypeId;
	}

	public Long getYearId() {
		return yearId;
	}

	public void setYearId(Long yearId) {
		this.yearId = yearId;
	}

	public PqrsEntityType getPqrsEntityType() {
		return pqrsEntityType;
	}

	public void setPqrsEntityType(PqrsEntityType pqrsEntityType) {
		this.pqrsEntityType = pqrsEntityType;
	}

	public YearSurvey getYearSurvey() {
		return yearSurvey;
	}

	public void setYearSurvey(YearSurvey yearSurvey) {
		this.yearSurvey = yearSurvey;
	}

	public boolean isUploadFileFlag() {
		return uploadFileFlag;
	}

	public void setUploadFileFlag(boolean uploadFileFlag) {
		this.uploadFileFlag = uploadFileFlag;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}



	public Long getUploadFileId() {
		return uploadFileId;
	}

	public void setUploadFileId(Long uploadFileId) {
		this.uploadFileId = uploadFileId;
	}

	public boolean isQuestionSelected() {
		return questionSelected;
	}

	public void setQuestionSelected(boolean questionSelected) {
		this.questionSelected = questionSelected;
	}

	public ArrayList<String> getFirstAnswerList() {
		return firstAnswerList;
	}

	public void setFirstAnswerList(ArrayList<String> firstAnswerList) {
		this.firstAnswerList = firstAnswerList;
	}

	public ArrayList<String> getSecondAnswerList() {
		return secondAnswerList;
	}

	public void setSecondAnswerList(ArrayList<String> secondAnswerList) {
		this.secondAnswerList = secondAnswerList;
	}

	public ArrayList<String> getThirdAnswerList() {
		return thirdAnswerList;
	}

	public void setThirdAnswerList(ArrayList<String> thirdAnswerList) {
		this.thirdAnswerList = thirdAnswerList;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getProvidedAnswer() {
		return providedAnswer;
	}

	public void setProvidedAnswer(String providedAnswer) {
		this.providedAnswer = providedAnswer;
	}

	public ArrayList getAnswersArrayList() {
		return answersArrayList;
	}

	public void setAnswersArrayList(ArrayList answersArrayList) {
		this.answersArrayList = answersArrayList;
	}
    
	@Override
	public String toString() {
		return "Questions [questionid=" + id + "]";
	}

	
	
	public String getAnswersList() {
		return answersList;
	}

	public void setAnswersList(String answersList) {
		this.answersList = answersList;
	}

	public Long getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(Long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public Long getQuestionCategoryId() {
		return questionCategoryId;
	}

	public void setQuestionCategoryId(Long questionCategoryId) {
		this.questionCategoryId = questionCategoryId;
	}

	public String getAnswerIdString() {
		return answerIdString;
	}

	public void setAnswerIdString(String answerIdString) {
		this.answerIdString = answerIdString;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public QuestionCategory getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}	

	@Override
	public int compareTo(Questions o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id1) {
		id = id1;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
