package com.sdi.model;

public class TripUser {
	private Trip trip;
	private String relation;

	public TripUser(Trip trip, String relation) {
		super();
		this.trip = trip;
		this.relation = relation;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}
