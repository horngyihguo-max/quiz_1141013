package com.example.quiz_1141013.response;

import java.util.List;

import com.example.quiz_1141013.vo.QuestionVo;

public class GetQuestionRes extends BasicRes {

	private List<QuestionVo> questionVoList;

	public GetQuestionRes(int code, String message, List<QuestionVo> questionVoList) {
		super(code, message);
		this.questionVoList = questionVoList;
	}

	public GetQuestionRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetQuestionRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public List<QuestionVo> getQuestionVoList() {
		return questionVoList;
	}

	public void setQuestionVoList(List<QuestionVo> questionVoList) {
		this.questionVoList = questionVoList;
	}

}
