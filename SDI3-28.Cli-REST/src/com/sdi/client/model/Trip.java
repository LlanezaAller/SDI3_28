package com.sdi.client.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.sdi.client.model.type.AddressPoint;
import com.sdi.client.model.type.TripStatus;


@XmlRootElement(name = "trip")
public class Trip implements Serializable{


	private Long id;



	private AddressPoint departure;



	private AddressPoint destination;


	private Date arrivalDate;


	private Date departureDate;


	private Date closingDate;

	private Integer availablePax;
	private Integer maxPax;
	private Double estimatedCost;
	private String comments;


	private TripStatus status;

	// Relaciones
	private Set<User> aplicadores = new HashSet<>();


	private Set<Seat> seats = new HashSet<>();


	private User promoter;

	public Trip() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AddressPoint getDeparture() {
		return departure;
	}

	public void setDeparture(AddressPoint departure) {
		this.departure = departure;
	}

	public AddressPoint getDestination() {
		return destination;
	}

	public void setDestination(AddressPoint destination) {
		this.destination = destination;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Integer getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(Integer availablePax) {
		this.availablePax = availablePax;
	}

	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	public Set<User> getAplicadores() {
		return aplicadores;
	}

	public void setAplicadores(Set<User> aplicadores) {
		this.aplicadores = aplicadores;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public User getPromoter() {
		return promoter;
	}

	public void setPromoter(User promoter) {
		this.promoter = promoter;
	}
	


}