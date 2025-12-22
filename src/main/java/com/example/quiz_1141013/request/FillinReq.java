package com.example.quiz_1141013.request;

import java.util.List;

import com.example.quiz_1141013.constants.ValidationMsg;
import com.example.quiz_1141013.vo.Answers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class FillinReq {
	public static final String QUESSTION_ID_ERROR = "Question Id error!!";

	@NotBlank(message = ValidationMsg.USER_NAME_IS_EMPTY)
	private String name;

	private String phone;

	@NotBlank(message = ValidationMsg.EMAIL_IS_EMPTY)
	private String email;

	@Min(value = 18, message = ValidationMsg.USER_AGE_ERROR)
	private int age;

	@Min(value = 1, message = ValidationMsg.QUIZ_ID_ERROR)
	private int quizId;

	@NotEmpty(message = ValidationMsg.ANSWERVO_IS_EMPTY)

	@Valid
	private List<Answers> answersList;

	public FillinReq() {
		super();
	}

	public FillinReq(String name, String phone, String email, int age, int quizId, //
			List<Answers> answersList) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.age = age;
		this.quizId = quizId;
		this.answersList = answersList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public List<Answers> getAnswersList() {
		return answersList;
	}

	public void setAnswersVoList(List<Answers> answersList) {
		this.answersList = answersList;
	}

	public static String getQuesstionIdError() {
		return QUESSTION_ID_ERROR;
	}

}
