package com.sdi.client.model;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sdi.client.model.type.SeatStatus;

@XmlRootElement(name = "seat")
public class Seat implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User seatUser;

	private Trip seatTrip;

	private String comment;


	private SeatStatus status;


	private Set<Rating> ratingsFrom ;


	private Set<Rating> ratingsAbout;

	public Seat() {
	}
	@XmlElement
	public User getSeatUser() {
		return seatUser;
	}

	public void setSeatUser(User user) {
		this.seatUser = user;
	}
	@XmlElement
	public Trip getSeatTrip() {
		return seatTrip;
	}

	public void setSeatTrip(Trip trip) {
		this.seatTrip = trip;
	}
	@XmlElement
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	@XmlElement
	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}
	@XmlElement
	public Set<Rating> getRatingsFrom() {
		return ratingsFrom;
	}

	public void setRatingsFrom(Set<Rating> ratingsFrom) {
		this.ratingsFrom = ratingsFrom;
	}
	@XmlElement
	public Set<Rating> getRatingsAbout() {
		return ratingsAbout;
	}

	public void setRatingsAbout(Set<Rating> ratingsAbout) {
		this.ratingsAbout = ratingsAbout;
	}
	
}
