package com.paidy.restaurant.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	
	private String errorId;
	private String messeage;
	
	
	public ErrorMessage() {
		super();
	}
	public ErrorMessage(String errorId, String messeage) {
		super();
		this.errorId = errorId;
		this.messeage = messeage;
	}
	public String getErrorId() {
		return errorId;
	}
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}
	public String getMesseage() {
		return messeage;
	}
	public void setMesseage(String messeage) {
		this.messeage = messeage;
	}

}
