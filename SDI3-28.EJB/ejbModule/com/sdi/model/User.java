package com.sdi.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sdi.model.type.UserStatus;

@Entity
@Table(name = "TUSERS")
@XmlRootElement(name = "user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String token;

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@ManyToMany(mappedBy = "aplicadores")
	private Set<Trip> aplicaciones = new HashSet<>();

	@OneToMany(mappedBy = "seatUser", cascade = (CascadeType.REMOVE))
	private Set<Seat> seats = new HashSet<>();

	@OneToMany(mappedBy = "promoter", cascade = (CascadeType.REMOVE))
	private Set<Trip> trips = new HashSet<>();

	public User() {
	};

	public User(String login, String password, String name, String surname,
			String email, UserStatus status) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.status = status;
	}

	public void update(User u) {
		this.login = u.getLogin();
		this.password = u.getPassword();
		this.name = u.getName();
		this.surname = u.getSurname();
		this.email = u.getEmail();
		this.status = u.getStatus();
		this.token = u.getToken();
	}
	
	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	@XmlElement
	public Long getId() {
		return id;
	}
	
	@XmlElement
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", name=" + name + ", surname=" + surname + ", status="
				+ status + ", email=" + email + "]";
	}

	// Metodos de relacion

	Set<Trip> _getApplications() {
		return aplicaciones;
	}

	public Set<Trip> getApplications() {
		return Collections.unmodifiableSet(aplicaciones);
	}

	Set<Seat> _getSeats() {
		return seats;
	}

	public Set<Seat> getSeats() {
		return Collections.unmodifiableSet(seats);
	}

	Set<Trip> _getTrips() {
		return trips;
	}

	public Set<Trip> getTrips() {
		return Collections.unmodifiableSet(trips);
	}

	// Fin de aplicación
	public void finAplicacion(Trip trip) {
		Trip target=null;
		for (Trip t : aplicaciones) {
			if (t.equals(trip)) {
				target = t;
				break;
			}
		}
		if(target!=null){
			target._getApplications().remove(this);
			aplicaciones.remove(target);
		}
	}

	// Usuario intenta aplicar
	public void aplicar(Trip t) {
		aplicaciones.add(t);
		t._getApplications().add(this);
	}

}
