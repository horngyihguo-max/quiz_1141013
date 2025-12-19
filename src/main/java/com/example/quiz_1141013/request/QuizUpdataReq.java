package com.example.quiz_1141013.request;

import com.example.quiz_1141013.constants.ValidationMsg;

import jakarta.validation.constraints.Min;

public class QuizUpdataReq extends QuizCreateReq{
	
	/* 因為更新是更新已存在的問卷， quiz_id 至少是 1 */
	@Min(value = 1, message = ValidationMsg.QUIZ_ID_ERROR)
	private int quizId;

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public QuizUpdataReq() {
		super();
	}

	public QuizUpdataReq(@Min(value = 1, message = "Quiz Id error!!") int quizId) {
		super();
		this.quizId = quizId;
	}
	
	
}
