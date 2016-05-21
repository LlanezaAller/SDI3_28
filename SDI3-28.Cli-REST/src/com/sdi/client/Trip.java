package com.sdi.client;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sdi.client.type.AddressPoint;
import com.sdi.client.type.TripStatus;


public class Trip implements Serializable{


	private Long id;



	private AddressPoint departure;



	private AddressPoint destination;


	private Date arrivalDate;


	private Date departureDate;


	private Date closingDate;

	private Integer availablePax = 0;
	private Integer maxPax = 0;
	private Double estimatedCost = 0.0;
	private String comments;


	private TripStatus status;

	// Relaciones
	private Set<User> aplicadores = new HashSet<>();


	private Set<Seat> seats = new HashSet<>();


	private User promoter;

	public Trip() {
	};

	public Trip(AddressPoint departure, AddressPoint destination,
			Date arrivalDate, Date departureDate, Date closingDate,
			Integer availablePax, Integer maxPax, Double estimatedCost,
			String comments, TripStatus status, User promoter) {
		super();

		this.departure = departure;
		this.destination = destination;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.closingDate = closingDate;
		this.availablePax = availablePax;
		this.maxPax = maxPax;
		this.estimatedCost = estimatedCost;
		this.comments = comments;
		this.status = status;
		this.promoter = promoter;
		promoter._getTrips().add(this);
	}

	public void setPromoter(User promoter) {
		this.promoter = promoter;
	}

	public AddressPoint getDeparture() {
		return departure;
	}

	public void setDeparture(AddressPoint departure) {
		this.departure = departure;
	}

	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		result = prime * result
				+ ((closingDate == null) ? 0 : closingDate.hashCode());
		result = prime * result
				+ ((departure == null) ? 0 : departure.hashCode());
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trip other = (Trip) obj;
		if (arrivalDate == null) {
			if (other.arrivalDate != null)
				return false;
		} else if (!arrivalDate.equals(other.arrivalDate))
			return false;
		if (closingDate == null) {
			if (other.closingDate != null)
				return false;
		} else if (!closingDate.equals(other.closingDate))
			return false;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", departure=" + departure + ", destination="
				+ destination + ", arrivalDate=" + arrivalDate
				+ ", departureDate=" + departureDate + ", closingDate="
				+ closingDate + ", availablePax=" + availablePax + ", maxPax="
				+ maxPax + ", estimatedCost=" + estimatedCost + ", comments="
				+ comments + ", status=" + status + ", promoterId=" + promoter
				+ "]";
	}

	// Metodos de relacion

	Set<User> _getApplications() {
		return aplicadores;
	}

	public Set<User> getApplications() {
		return Collections.unmodifiableSet(aplicadores);
	}

	Set<Seat> _getSeats() {
		return seats;
	}

	public Set<Seat> getSeats() {
		return Collections.unmodifiableSet(seats);
	}

	public User getPromoter() {
		return promoter;
	}

	void _setPromoter(User promoter) {
		this.promoter = promoter;
	}

}