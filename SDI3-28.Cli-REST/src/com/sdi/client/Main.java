package com.sdi.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class Main {
	private static final String REST_SERVICE_URL = "http://localhost:8280/SDI3-28.Web/rest";

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
		String con = "";
		User u = new User();
		u.setLogin("usuario1");
		u.setPassword("usuario1");

		Trip tripActual = null;
		List<Trip> trips = getTripsByIdAsXmlString(u);

		int c = 1;
		System.out.println("Selecciona uno de los siguientes viajes:");
		for (Trip t : trips) {
			System.out.println("Viaje numero " + c++);
			System.out.println("Salida:");
			System.out.println("\t" + t.getDeparture().getCity());
			System.out.println("Llegada:");
			System.out.println("\t" + t.getDestination().getCity());
			System.out.println("************************");

		}
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				con = console.readLine();
				tripActual = trips.get(Integer.parseInt(con));
				break;
			} catch (Exception e) {
				System.out.println("Introduzca un índice válido");
			}
		}
		
		List<User> usuarios = getUnconfirmedUsersByTripIdAsXmlString(tripActual);
		System.out.println("Seleccione el usuario a confirmar:");
		for(User us : usuarios){
			System.out.println("Nombre:");
			System.out.println("\t"+us.getName());
			System.out.println("Apellido:");
			System.out.println("\t"+us.getSurname());
		}
		
		System.out.println("\n-- ws REST JAX-RS remote client ended -");
	}

	private List<Trip> getTripsByIdAsXmlString(User usuario) {
		List<Trip> trips = ClientBuilder
				.newClient()
				.target(REST_SERVICE_URL
						+ "/ViajesService/findAllTripsByPromoterID/1")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Trip>>() {
				});
		return trips;
	}

	private List<User> getUnconfirmedUsersByTripIdAsXmlString(Trip trip) {
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
	private List<Seat> getSeatsByTripIdAsXmlString(Trip trip) {
		List<Seat> seats = ClientBuilder
				.newClient()
				.target(REST_SERVICE_URL
						+ "/ViajesService/findSeatsFromTrip/")
						.path(""+trip.getId())
						.request(MediaType.APPLICATION_XML)
						.get(new GenericType<List<Seat>>() {
						});
		return seats;
	}

}
