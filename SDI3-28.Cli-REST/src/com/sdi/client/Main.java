package com.sdi.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class Main {
	private static final 
		String REST_SERVICE_URL = "http://localhost:8280/SDI3-28.Web/rest";

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		
		// List<User> usuarios = restGetUsers(); // A GET operation
		//
		// showStudents(alumnos);
		//
		// String res = getStudentByIdAsJsonString(alumnos.get(0));
		// System.out.println(res);
		//
		// res = getStudentByIdAsXmlString(alumnos.get(0));
		// System.out.println(res);
		//
		// Alumno a = getStudentByIdAsObject(alumnos.get(0));
		// printStudent(a);
		//
		// a = createNewStudent(); // A PUT operation
		// updateStudent(alumnos.get(0));// A POST operation
		// deleteStudent(alumnos.get(1));// A DELETE operation

		User u = new User();
		u.setLogin("usuario1");
		u.setPassword("usuario1");
		
		List<Trip> trips = getTripsByIdAsXmlString(u);

		System.out.println("\n-- ws REST JAX-RS remote client ended -");
	}
	

	private List<Trip> getTripsByIdAsXmlString(User usuario) {
		List<Trip> trips = ClientBuilder
				.newClient()
				.target(REST_SERVICE_URL + "/ViajesService/findAllTripsByPromoterID/1")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Trip>>() {});
		return trips;
	}

}
