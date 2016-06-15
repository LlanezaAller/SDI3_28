package com.sdi.client.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rating")
public class Rating implements Serializable{


	private Long id;


	private Seat fromSeat;


	private Seat aboutSeat;

	private String comment;
	private Integer value = 0;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Seat getFromSeat() {
		return fromSeat;
	}
	public void setFromSeat(Seat fromSeat) {
		this.fromSeat = fromSeat;
	}
	public Seat getAboutSeat() {
		return aboutSeat;
	}
	public void setAboutSeat(Seat aboutSeat) {
		this.aboutSeat = aboutSeat;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
}
