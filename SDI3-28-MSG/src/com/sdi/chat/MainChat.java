package com.sdi.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.sdi.chat.model.Seat;
import com.sdi.chat.model.SeatStatus;
import com.sdi.chat.model.SimpleResponse;
import com.sdi.chat.model.Trip;
import com.sdi.chat.model.User;



public class MainChat {
	private static final String REST_SERVICE_URL = "http://localhost:8280/SDI3-28.Web/rest";
	
	private BufferedReader console;
	
	private List<Trip> trips;
	private User user;
	public static void main(String[] args) throws JMSException {
		System.out.println("Bienvenido al chat de Share My Trip");
		LogConfig.config();
		new MainChat().run();
	}

	

	private void run() throws JMSException {
		console = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Introduzca sus credenciales:");
		try {
			System.out.print("Usuario: ");
			String login = console.readLine();
			System.out.print("Contraseña: ");
			String password = console.readLine();
			user = login(login, password);
			if(user!=null){
				trips = findTrips(user);
				showTripsTable(trips);
			}
			else
				run();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private User login(String login, String password){	
		User user = new User();
		try{
		user.setLogin(login);
		user.setPassword(password);
		user.setId((Long) ClientBuilder
				.newClient()
				.register(new Authenticator(login, password))
				.target(REST_SERVICE_URL
						+ "/UsuariosService/getID")
				.request(MediaType.APPLICATION_XML)
				.get(SimpleResponse.class).getResponse()
				);
		} catch (NotAuthorizedException e){
			System.out.println("Credenciales inválidas, vuelva a intentarlo.");
			return null;
		}
		return user;
	}
	
	private List<Trip> findTrips(User user){
		List<Seat> seatList = ClientBuilder
				.newClient()
				.register(new Authenticator(user.getLogin(), user.getPassword()))
				.target(REST_SERVICE_URL
						+ "/AsientosService/findByUser")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<Seat>>() {});
		
		trips = new ArrayList<>();
		for(Seat s: seatList){
			if(s.getStatus()==SeatStatus.ACCEPTED || s.getTrip().getPromoter().getId()==user.getId())
				trips.add(s.getTrip());
		}
		
		return trips;
	}
	
	private void showTripsTable(List<Trip> trips){
		if(trips!=null && trips.size()>0){
			System.out.println("ID\tSalida\tDestino\tEstado");
			int id=0;
			for(Trip t:trips){
				System.out.println(id + "(" +t.getId() +") "+"\t"+t.getDeparture().getCity()+"\t" + t.getDestination().getCity()+ t.getStatus());
				id++;
			}
			System.out.println("Introduzca id del viaje");
			try {
				int tripID = Integer.valueOf(console.readLine());
				if(tripID>=0 && tripID<trips.size()){
					Chat c = new Chat(console, trips.get(tripID).getId(), user.getLogin());
					c.start();
				}else {
					System.out.println("ID inválido");
					showTripsTable(trips);
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("No hay viajes disponibles para este usuario.");
		}
	}
	

}
