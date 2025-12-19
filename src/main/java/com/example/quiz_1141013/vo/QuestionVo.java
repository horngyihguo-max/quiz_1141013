package com.example.quiz_1141013.vo;

import java.util.ArrayList;
import java.util.List;

import com.example.quiz_1141013.constants.ValidationMsg;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuestionVo {

	private int quizId;

	@NotNull(message = ValidationMsg.QUESSTION_ID_ERROR)
	private int questionId;

	@NotBlank(message = ValidationMsg.QUESTION_ERROR)
	private String question;

	@NotBlank(message = ValidationMsg.TYPE_ERROR)
	private String type;

	private boolean required;

	/*
	 * 不限制，因為簡答題沒有選項 // 給定空的List 可預防 這個屬性沒有 mapping 到時會是 null --> 即預設值會從 null 變成空的
	 * List
	 */
	private List<Options> optionList = new ArrayList<>();

	public QuestionVo() {
		super();
	}

	public QuestionVo(int quizId, int questionId, String question, //
			String type, boolean required, List<Options> optionList) {
		super();
		this.quizId = quizId;
		this.questionId = questionId;
		this.question = question;
		this.type = type;
		this.required = required;
		this.optionList = optionList;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public List<Options> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Options> optionList) {
		this.optionList = optionList;
	}

}
