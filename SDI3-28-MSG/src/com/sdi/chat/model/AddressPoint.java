package com.sdi.chat.model;

import java.io.Serializable;


public class AddressPoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address;
	private City city;
	private String state;
	private String country;
	private String zipCode;


	private Waypoint waypoint;

	public AddressPoint() {
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setWaypoint(Waypoint waypoint) {
		this.waypoint = waypoint;
	}

	public String getAddress() {
		return address;
	}

	public City getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public Waypoint getWaypoint() {
		return waypoint;
	}

}
