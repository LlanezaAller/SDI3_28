package com.sdi.client.type;

import java.io.Serializable;


public class Waypoint implements Serializable{

	private Double lat;
	private Double lon;


	public Waypoint() {
	}


	public Double getLat() {
		return lat;
	}


	public void setLat(Double lat) {
		this.lat = lat;
	}


	public Double getLon() {
		return lon;
	}


	public void setLon(Double lon) {
		this.lon = lon;
	}
	
}