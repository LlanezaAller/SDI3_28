package com.sdi.client.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sdi.client.model.type.AddressPoint;
import com.sdi.client.model.type.TripStatus;


@XmlRootElement(name = "trip")
public class Trip implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


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
	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement
	public AddressPoint getDeparture() {
		return departure;
	}

	public void setDeparture(AddressPoint departure) {
		this.departure = departure;
	}
	@XmlElement
	public AddressPoint getDestination() {
		return destination;
	}

	public void setDestination(AddressPoint destination) {
		this.destination = destination;
	}
	@XmlElement
	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	@XmlElement
	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	@XmlElement
	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	@XmlElement
	public Integer getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(Integer availablePax) {
		this.availablePax = availablePax;
	}
	@XmlElement
	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}
	@XmlElement
	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	@XmlElement
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	@XmlElement
	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}
	
	@XmlElement
	public Set<User> getAplicadores() {
		return aplicadores;
	}

	public void setAplicadores(Set<User> aplicadores) {
		this.aplicadores = aplicadores;
	}
	@XmlElement
	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}
	
	@XmlElement
	public User getPromoter() {
		return promoter;
	}

	public void setPromoter(User promoter) {
		this.promoter = promoter;
	}
	


}