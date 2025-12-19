package com.example.quiz_1141013.constants;

public enum Type {

	SINGLE("Single"), //
	MULTI("Multiple"), //
	TEXT("Short");

	private String type;

	private Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static boolean checkType(String input) {
		for (Type type : values()) {
			if (input.equalsIgnoreCase(type.getType())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isChosenType(String input) {
		return SINGLE.getType().equalsIgnoreCase(input) || MULTI.getType().equalsIgnoreCase(input);
	}

}
