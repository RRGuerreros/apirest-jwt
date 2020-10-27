package com.spring.security.login.entity;

public class Search {
	
	private boolean regex;
	private String value;
	public boolean isRegex() {
		return regex;
	}
	public void setRegex(boolean regex) {
		this.regex = regex;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Search [regex=" + regex + ", value=" + value + "]";
	}
}
