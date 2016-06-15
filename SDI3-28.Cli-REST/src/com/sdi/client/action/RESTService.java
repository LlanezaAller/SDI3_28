package com.sdi.client.action;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.sdi.client.model.Seat;
import com.sdi.client.model.Trip;
import com.sdi.client.model.User;
import com.sdi.client.model.type.SeatStatus;

public class RESTService {
	private static final String REST_SERVICE_URL = "http://localhost:8280/SDI3-28.Web/rest";
	
	public List<Trip> getUserTrips(User usuario) {
		List<Trip> trips = ClientBuilder
				.newClient()
				.target(REST_SERVICE_URL
						+ "/ViajesService/findAllTripsByPromoterID/1")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Trip>>() {
				});
		return trips;
	}

	public List<User> getUnconfirmedUsers(Trip trip) {
		List<User> usuarios = ClientBuilder
				.newClient()
				.target(REST_SERVICE_URL
						+ "/UsuariosService/findUnconfirmedUsersByTrip/")
				.path(""+trip.getId())
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<User>>() {
				});
		return usuarios;
	}
	
	public void confirmUser(User u, Trip t) {
		Seat s = new Seat();
		s.setUser(u);
		s.setTrip(t);
		s.setStatus(SeatStatus.ACCEPTED);
		ClientBuilder
				.newClient()
				.target(REST_SERVICE_URL
						+ "/UsuariosService/manageApplication/")
				.request()
				.put(Entity.entity(s, MediaType.APPLICATION_XML));
	}
	
}
