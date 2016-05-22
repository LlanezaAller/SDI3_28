package com.sdi.client;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.sdi.client.type.UserStatus;

@XmlRootElement(name = "user")
public class User implements Serializable{
	
	private Long id;

	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;

	private UserStatus status;


	private Set<Trip> aplicaciones = new HashSet<>();


	private Set<Seat> seats = new HashSet<>();


	private Set<Trip> trips = new HashSet<>();

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Set<Trip> getAplicaciones() {
		return aplicaciones;
	}

	public void setAplicaciones(Set<Trip> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public Set<Trip> getTrips() {
		return trips;
	}

	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
	}
	
}
