package com.sdi.client.action;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.sdi.client.model.Trip;
import com.sdi.client.model.User;

public class RESTService {
	private static final String REST_SERVICE_URL = "http://localhost:8280/SDI3-28.Web/rest";

	private String usuario;
	private String pass;

	public RESTService(String usuario, String pass) {
		this.usuario = usuario;
		this.pass = pass;
	}

	public List<Trip> getUserTrips() {
		List<Trip> trips = ClientBuilder
				.newClient()
				.register(new Authenticator(usuario, pass))
				.target(REST_SERVICE_URL
						+ "/ViajesService/findAllTripsByPromoterID")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Trip>>() {
				});
		return trips;
	}

	public List<User> getUnconfirmedUsers(Trip trip) {
		List<User> usuarios = ClientBuilder
				.newClient()
				.register(new Authenticator(usuario, pass))
				.target(REST_SERVICE_URL
						+ "/UsuariosService/findUnconfirmedUsersByTrip/")
				.path("" + trip.getId()).request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<User>>() {
				});
		return usuarios;
	}

	public void confirmUser(User u, Trip t) {
		Form form = new Form()
						.param("userLogin", u.getLogin())
						.param("tripID", String.valueOf(t.getId()));
		Entity<Form> entity = Entity.form(form);
		ClientBuilder
				.newClient()
				.register(new Authenticator(usuario, pass))
				.target(REST_SERVICE_URL
						+ "/UsuariosService/confirmUser/")
				.request()
				.put(entity);
	}

}
