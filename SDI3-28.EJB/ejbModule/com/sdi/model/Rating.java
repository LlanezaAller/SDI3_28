package com.sdi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TRATING")
@XmlRootElement(name = "rating")
public class Rating implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Seat fromSeat;

	@ManyToOne
	private Seat aboutSeat;

	private String comment;
	private Integer value = 0;

	public Rating(Seat fromSeat, Seat aboutSeat, String comment, Integer value) {
		super();
		this.fromSeat = fromSeat;
		this.aboutSeat = aboutSeat;
		this.comment = comment;
		this.value = value;
		fromSeat._getRatingsAbout().add(this);
		fromSeat._getRatingsFrom().add(this);

		aboutSeat._getRatingsAbout().add(this);
		aboutSeat._getRatingsFrom().add(this);
	}

	public Rating() {
	};
	@XmlElement
	public Seat getFromSeat() {
		return fromSeat;
	}
	
	public void setFromSeat(Seat fromSeat) {
		this.fromSeat = fromSeat;
	}
	@XmlElement
	public Seat getAboutSeat() {
		return aboutSeat;
	}

	public void setAboutSeat(Seat aboutSeat) {
		this.aboutSeat = aboutSeat;
	}
	@XmlElement
	public Long getId() {
		return id;
	}
	@XmlElement
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	@XmlElement
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Rating [fromSeat=" + fromSeat + ", aboutSeat=" + aboutSeat
				+ ", comment=" + comment + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aboutSeat == null) ? 0 : aboutSeat.hashCode());
		result = prime * result
				+ ((fromSeat == null) ? 0 : fromSeat.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Rating other = (Rating) obj;
		if (aboutSeat == null) {
			if (other.aboutSeat != null)
				return false;
		} else if (!aboutSeat.equals(other.aboutSeat))
			return false;
		if (fromSeat == null) {
			if (other.fromSeat != null)
				return false;
		} else if (!fromSeat.equals(other.fromSeat))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
