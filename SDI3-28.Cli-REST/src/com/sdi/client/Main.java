package com.sdi.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.sdi.client.action.RESTService;
import com.sdi.client.model.Seat;
import com.sdi.client.model.Trip;
import com.sdi.client.model.User;
import com.sdi.client.model.type.TripStatus;

public class Main {
	
	private RESTService rest;

	public static void main(String[] args) {
		LogConfig.config();
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
		
		rest = new RESTService();
		
		
		User u = new User();
		u.setId(1l);
		u.setLogin("usuario1");
		u.setPassword("usuario1");

		Trip tripActual = null;
		List<Trip> trips = rest.getUserTrips(u);

		showTrips(trips);
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while (true) {
			try {
				input = console.readLine();
				int selectedTrip = Integer.parseInt(input);
				if(selectedTrip>0 && selectedTrip<trips.size()){
					tripActual = trips.get(selectedTrip);
					if(tripActual.getStatus()==TripStatus.OPEN){
						System.out.println(">Seleccionado viaje nº" + selectedTrip + ", con salida [" + tripActual.getDeparture().getCity() + "] y destino [" + tripActual.getDestination().getCity() + "]");
						List<User> aplicaciones = showTripApplications(tripActual);
						input = console.readLine();
						int selectedUser = Integer.parseInt(input);
						rest.confirmUser(aplicaciones.get(selectedUser), tripActual);
						break;
					}else
						System.out.println("Viaje cerrado, seleccione otro.");
				}
			} catch (Exception e) {
				System.out.println("Introduzca un índice válido\n");
				showTrips(trips);
			}
		}		
		System.out.println("\n-- ws REST JAX-RS remote client ended -");
	}
	
	private List<User> showTripApplications(Trip t){
		List<User> usuarios = rest.getUnconfirmedUsers(t);
		System.out.println("Seleccione el usuario a confirmar:");
		System.out.println("Nº\tNombre\tApellido");
		int c=0;
		for(User us : usuarios){
			System.out.println(c++ + "\t" + us.getName() + "\t" + us.getSurname());
		}
		return usuarios;
	}
	
	private void showTrips(List<Trip> trips){
		int c = 0;
		System.out.println("Selecciona uno de los siguientes viajes:");
		System.out.println("Nº\tSalida\t\tLlegada\tEstado");
		for (Trip t : trips) {
			System.out.println(c++ + "(" + t.getId() + ")\t" + t.getDeparture().getCity() + "\t" + t.getDestination().getCity()+"\t"+ t.getStatus());
		}
	}

}
