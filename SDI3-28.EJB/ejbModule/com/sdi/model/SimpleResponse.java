package com.sdi.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class SimpleResponse<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private T response;
	
	public SimpleResponse(){}
	public SimpleResponse(T value){
		response=value;
	}
	
	@XmlElement
	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}
	
}
