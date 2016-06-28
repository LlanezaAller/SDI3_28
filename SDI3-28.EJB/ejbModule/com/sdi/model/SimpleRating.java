package com.sdi.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SimpleRating")
public class SimpleRating implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private User about;
	private User from;
	private Trip aboutTrip;
	private String comment;
	private int rating;

	public SimpleRating() {
	}

	public SimpleRating(Rating r) {
		id = r.getId();
		about = r.getAboutSeat().getSeatUser();
		from = r.getFromSeat().getSeatUser();
		aboutTrip = r.getAboutSeat().getSeatTrip();
		comment = r.getComment();
		rating = r.getValue();
	}

	public SimpleRating(Long id, User about, User from, Trip trip,
			String comment, int rating) {
		super();
		this.id = id;
		this.about = about;
		this.from = from;
		this.aboutTrip = trip;
		this.comment = comment;
		this.rating = rating;
	}

	@XmlElement
	public User getAbout() {
		return about;
	}

	public void setAbout(User about) {
		this.about = about;
	}

	@XmlElement
	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}


	@XmlElement
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@XmlElement
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement
	public Trip getAboutTrip() {
		return aboutTrip;
	}

	public void setAboutTrip(Trip aboutTrip) {
		this.aboutTrip = aboutTrip;
	}

}
