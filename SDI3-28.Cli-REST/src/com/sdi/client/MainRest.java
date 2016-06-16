package com.sdi.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotAuthorizedException;

import com.sdi.client.action.RESTService;
import com.sdi.client.model.Trip;
import com.sdi.client.model.User;
import com.sdi.client.model.type.TripStatus;

public class MainRest {
	
	private RESTService rest;
	private BufferedReader console;
	private String usuario;
	private String pass;

	public static void main(String[] args) {
		LogConfig.config();
		new MainRest().run();
	}

	private void run() {		
		console = new BufferedReader(new InputStreamReader(System.in));
		login();	
		System.out.println("Adios!");
	}
	private void login(){
		System.out.println("Bienvenido a la app de ShareYourTrip, Por favor intruduzca sus credenciales.");
		try {
			System.out.print("Usuario: ");
			usuario = console.readLine();
			System.out.print("Contraseña: ");
			pass = console.readLine();
			rest = new RESTService(usuario, pass);
			showTrips(filterTrips(rest.getUserTrips(), TripStatus.OPEN));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotAuthorizedException e){
			System.out.println("Credenciales inválidas, vuelva a intentarlo.");
			login();
		}
	}
	private List<Trip> filterTrips(List<Trip> userTrips, TripStatus status) {
		List<Trip> filtered = new ArrayList<>();
		for(Trip t:userTrips){
			if(t.getStatus()==status)
				filtered.add(t);
		}
		return filtered;
	}

	private void showTrips(List<Trip> trips){
		int c = 0;
		System.out.println("Selecciona uno de los siguientes viajes:");
		System.out.println("Nº\tSalida\t\tLlegada\tEstado");
		for (Trip t : trips) {
			System.out.println(c++ + "(" + t.getId() + ")\t" + t.getDeparture().getCity() + "\t" + t.getDestination().getCity()+"\t"+ t.getStatus());
		}
		try{
			System.out.println("Selecciona un viaje para confirmar sus usuarios.");
			String input = console.readLine();
			if(!input.equals("!exit")){
				int tripSelected = Integer.valueOf(input);
				if(tripSelected>=0 && tripSelected<trips.size())
					showTripApplications(trips.get(tripSelected));
				else
					System.out.println("Viaje no válido, vuelve a intentarlo");
				showTrips(trips);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private List<User> showTripApplications(Trip t){
		List<User> usuarios = rest.getUnconfirmedUsers(t);
		System.out.println("Seleccione el usuario a confirmar:");
		System.out.println("Nº\tNombre\tApellido");
		int c=0;
		for(User us : usuarios){
			System.out.println(c++ + "\t" + us.getName() + "\t" + us.getSurname());
		}
		try{
			System.out.println("Selecciona un usuario para confirmar.");
			String input = console.readLine();
			if(!input.equals("!exit")){
				int userSelected = Integer.valueOf(input);
				if(userSelected>=0 && userSelected<usuarios.size()) {
					rest.confirmUser(usuarios.get(userSelected), t);
					System.out.println("Usuario confirmado con éxito.");
				}
				else
					System.out.println("Usuario no válido, vuelve a intentarlo");
				showTripApplications(t);		
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	

}
