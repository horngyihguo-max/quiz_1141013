package com.example.quiz_1141013.constants;

public class ValidationMsg {

	// 加上 final 是因為使用在 @Validation 中的 messsge 限制
	// 加上 static 是為了方便直接透過ValidationMsg，來呼叫此常數變數
	public static final String TITLE_ERROR = "Title error!!";

	public static final String DESCRIPTION_ERROR = "Description error!!";

	public static final String START_DATE_ERROR = "Start Date error!!";

	public static final String END_DATE_ERROR = "End Date error!!";

	public static final String QUESTION_ERROR = "Question error!!";

	public static final String TYPE_ERROR = "Type error!!";
	
	public static final String QUIZ_ID_ERROR = "Quiz Id error!!";
	
	public static final String USER_NAME_IS_EMPTY = "User Name Is Empty!!";
	
	public static final String EMAIL_IS_EMPTY = "Email Is Empty!!";
	
	public static final String USER_AGE_ERROR = "User Age Error!!";
	
	public static final String QUESSTION_ID_ERROR = "Question Id error!!";
	
	public static final String ANSWERVO_IS_EMPTY = "AnswerVo Is Empty!!";
}
