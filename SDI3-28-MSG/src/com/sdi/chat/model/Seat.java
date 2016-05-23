package com.sdi.chat.model;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seat")
public class Seat implements Serializable{


	private User user;

	private Trip trip;

	private String comment;


	private SeatStatus status;


	private Set<Rating> ratingsFrom ;


	private Set<Rating> ratingsAbout;

	public Seat() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	public Set<Rating> getRatingsFrom() {
		return ratingsFrom;
	}

	public void setRatingsFrom(Set<Rating> ratingsFrom) {
		this.ratingsFrom = ratingsFrom;
	}

	public Set<Rating> getRatingsAbout() {
		return ratingsAbout;
	}

	public void setRatingsAbout(Set<Rating> ratingsAbout) {
		this.ratingsAbout = ratingsAbout;
	}
	
}
