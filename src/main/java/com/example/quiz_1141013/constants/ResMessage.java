package com.example.quiz_1141013.constants;

public enum ResMessage {

	SUCCESS(200, "Success!!"), //
	DATE_ERROR(400, "Date Error!!"), //
	TYPE_ERROR(400, "typeError!!"), //
	PLEASE_LOGIN_FIRST(400, "Please Login First!!"), //
	OPTIONS_SIZE_ERROR(400, "Options Size Error!!"),//
	QUIZ_ID_MISMATCH(400, "Quiz Id Mismatch!!"),//
	QUIZ_NOT_FOUND(404, "Quiz Not Found!!");//

	private int code;

	private String message;

	private ResMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
