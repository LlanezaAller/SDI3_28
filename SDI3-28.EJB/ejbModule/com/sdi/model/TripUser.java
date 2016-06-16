package com.sdi.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tripuser")
public class TripUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private Trip trip;
	private String relation;
	
	public TripUser(){}
	
	public TripUser(Trip trip, String relation) {
		super();
		this.trip = trip;
		this.relation = relation;
	}
	@XmlElement
	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	@XmlElement
	public String getRelation() {
		return relation;
	}
	
	public void setRelation(String relation) {
		this.relation = relation;
	}

}
