package com.sdi.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sdi.model.keys.SeatKey;
import com.sdi.model.type.SeatStatus;

@Entity
@Table(name = "TSEATS")
@IdClass(SeatKey.class)
@XmlRootElement(name = "seat")
public class Seat implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	private User seatUser;
	@Id
	@ManyToOne
	private Trip seatTrip;

	private String comment;

	@Enumerated(EnumType.STRING)
	private SeatStatus status;

	@OneToMany(mappedBy = "fromSeat", cascade = (CascadeType.REMOVE))
	private Set<Rating> ratingsFrom = new HashSet<>();

	@OneToMany(mappedBy = "aboutSeat", cascade = (CascadeType.REMOVE))
	private Set<Rating> ratingsAbout = new HashSet<>();

	public Seat() {
	};

	public Seat(User user, Trip trip) {
		this.seatUser = user;
		this.seatTrip = trip;

		user._getSeats().add(this);
		trip._getSeats().add(this);
	}
	
	@XmlElement(name = "seatUser")
	public User getSeatUser() {
		return seatUser;
	}

	public void setSeatUser(User user) {
		this.seatUser = user;
	}
	
	@XmlElement(name = "seatTrip")
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

	@Override
	public String toString() {
		return "Seat [userId=" + seatUser + ", tripId=" + seatTrip + ", comment="
				+ comment + ", status=" + status + "]";
	}

	// Relaciones

	Set<Rating> _getRatingsFrom() {
		return ratingsFrom;
	}

	public void unlink() {
		seatUser._getApplications().remove(this);
		seatTrip._getApplications().remove(this);
		this.seatUser = null;
		this.seatTrip = null;
	}

	public Set<Rating> getRatingsFrom() {
		return Collections.unmodifiableSet(ratingsFrom);
	}

	Set<Rating> _getRatingsAbout() {
		return ratingsAbout;
	}

	public Set<Rating> getRatingsAbout() {
		return Collections.unmodifiableSet(ratingsAbout);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seatTrip == null) ? 0 : seatTrip.hashCode());
		result = prime * result + ((seatUser == null) ? 0 : seatUser.hashCode());
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
		Seat other = (Seat) obj;
		if (seatTrip == null) {
			if (other.seatTrip != null)
				return false;
		} else if (!seatTrip.equals(other.seatTrip))
			return false;
		if (seatUser == null) {
			if (other.seatUser != null)
				return false;
		} else if (!seatUser.equals(other.seatUser))
			return false;
		return true;
	}

}
