package com.spring.security.login.entity;

public class Column {
	
	private String data;
	private String name;
	private boolean orderable;
	private boolean searchable;
	private Search search;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOrderable() {
		return orderable;
	}
	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}
	public boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	@Override
	public String toString() {
		return "Column [data=" + data + ", name=" + name + ", orderable=" + orderable + ", searchable=" + searchable
				+ ", search=" + search + "]";
	}
}
