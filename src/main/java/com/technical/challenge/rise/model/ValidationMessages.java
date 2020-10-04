package com.technical.challenge.rise.model;

import java.util.List;

public class ValidationMessages {

	private String message;
	private Integer totalNumberOfRecords;
	private Integer successfulRecords;
	private List<String> errorList ;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}
	public void setTotalNumberOfRecords(Integer totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}
	public Integer getSuccessfulRecords() {
		return successfulRecords;
	}
	public void setSuccessfulRecords(Integer successfulRecords) {
		this.successfulRecords = successfulRecords;
	}
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}



	  
}
