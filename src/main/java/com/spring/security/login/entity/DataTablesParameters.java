package com.spring.security.login.entity;

import java.util.List;

public class DataTablesParameters {

	private int draw;
	private int length;
	private int start;
	private List<Column> columns;
	
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	@Override
	public String toString() {
		return "DataTablesParameters [draw=" + draw + ", length=" + length + ", start=" + start + "]";
	}
}
