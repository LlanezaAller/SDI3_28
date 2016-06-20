package com.sdi.persistence.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Rating;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.type.AddressPoint;
import com.sdi.model.type.City;
import com.sdi.model.type.SeatStatus;
import com.sdi.model.type.TripStatus;
import com.sdi.model.type.UserStatus;
import com.sdi.model.type.Waypoint;
import com.sdi.persistence.SystemFinder;
import com.sdi.persistence.util.Jpa;

public class SystemFinderImpl implements SystemFinder {

	// Numero de usuarios y viajes
	int numeroUsuarios = 3;
	// Numero de viajes por usuario
	int numeroViajes = 10;

	@Override
	public void unlink(Object o) {
		Jpa.getManager().detach(o);
	}

	@Override
	public void deleteAll() {
		EntityManager em = Jpa.getManager();

		em.createNamedQuery("System.dropRatings").executeUpdate();
		em.createNamedQuery("System.dropSeats").executeUpdate();
		em.createNamedQuery("System.dropTrips").executeUpdate();
		em.createNamedQuery("System.dropUsers").executeUpdate();
	}

	List<User> usuarios = null;
	List<Trip> viajes = null;
	List<Seat> seats = null;

	@Override
	public void createElements() {
		// Creacion User
		usuarios = createUser(numeroUsuarios);
		for (User u : usuarios)
			Factories.persistence.CreateUserFinder().newUser(u);

		// Creacion Trip
		viajes = createTrip(numeroViajes, usuarios);
		for (Trip t : viajes)
			Factories.persistence.CreateTripFinder().newTrip(t);

		// Creacion Seats y Application y ratings 
		seats = createSeats(viajes);
		int c=0;
		for (Seat s1 : seats) {
			Factories.persistence.CreateSeatFinder().newSeat(s1);
			if(s1.getTrip().getStatus()==TripStatus.DONE && s1.getStatus()==SeatStatus.ACCEPTED){
				for(Seat s2: seats){
					if(s1.getTrip().getId().equals(s2.getTrip().getId())){
						if(!s2.equals(s1) && s2.getStatus()==SeatStatus.ACCEPTED){
							Factories.persistence.CreateRatingFinder().newRating(new Rating(s1, s2, "Comentario genérico " + c++, 10));
						}
					}
				}
			}
		}
	}

	private ArrayList<Seat> createSeats(List<Trip> viajes) {
		ArrayList<Seat> asientos = new ArrayList<>();
		User prom = null;
		Trip t = null;
		for (int i = 0; i < viajes.size(); i++) {
			t = viajes.get(i);
			prom = t.getPromoter();
			asientos.add(new Seat(prom, t));
			asientos.get(asientos.size() - 1).setStatus(SeatStatus.ACCEPTED);
			int numSeats = Math.min(t.getAvailablePax(), usuarios.size());
			for (int j = 0; j < numSeats; j++) {
				if (usuarios.get(j).getId() != prom.getId()) {
					int param = (int) ((j * (Math.random() * 3)) % 3);
					switch (param) {
					case 0:// Asiento confirmado
						asientos.add(new Seat(usuarios.get(j), t));
						asientos.get(asientos.size() - 1).setStatus(
								SeatStatus.ACCEPTED);
						t.setAvailablePax(t.getAvailablePax() - 1);
						break;
					case 1:// Asiento rechazado
						asientos.add(new Seat(usuarios.get(j), t));
						asientos.get(asientos.size() - 1).setStatus(
								SeatStatus.EXCLUDED);
						break;
					case 2:// Aplicación
						usuarios.get(j).aplicar(t);
						break;
					}
				}
			}
		}
		return asientos;
	}

	@Override
	public void resetIDs() {

	}

	private ArrayList<User> createUser(int num) {
		ArrayList<User> lista = new ArrayList<>();
		for (int i = 1; i < num + 1; i++)
			lista.add(new User("usuario" + i, "usuario" + i, "usuario" + i
					+ "Nombre", "usuario" + i + "Apellido", "usuario" + i
					+ "@users.com", UserStatus.ACTIVE));

		return lista;
	}

	private ArrayList<Trip> createTrip(int num, List<User> usuarios) {
		List<AddressPoint> address = createAddressPoints(num * 2);
		ArrayList<Trip> viajes = new ArrayList<>();
		Long d1 = (long) (24 * 60 * 60 * 1000);
		for (User u : usuarios)
			for (int i = 0; i < num; i++) {
				int param = i % 4;
				switch (param) {
				case 0:
					viajes.add(new Trip(address.get(i), address.get(num + i),
							new Date(System.currentTimeMillis() - d1 * i),
							new Date(System.currentTimeMillis() - d1 * 2 * i),
							new Date(System.currentTimeMillis() - d1 * 3 * i),
							2, 3, 10.0, "comment" + i, TripStatus.CANCELLED, u));
					break;
				case 1:
					viajes.add(new Trip(address.get(i), address.get(num + i),
							new Date(System.currentTimeMillis() - d1 * i),
							new Date(System.currentTimeMillis() + d1 * 2 * i),
							new Date(System.currentTimeMillis() + d1 * 3 * i),
							3, 4, 10.0, "comment" + i, TripStatus.CLOSED, u));
					break;
				case 2:
					viajes.add(new Trip(address.get(i), address.get(num + i),
							new Date(System.currentTimeMillis() - d1 * i),
							new Date(System.currentTimeMillis() - d1 * 2 * i),
							new Date(System.currentTimeMillis() - d1 * 3 * i),
							3, 4, 10.0, "comment" + i, TripStatus.DONE, u));
					break;
				case 3:
					viajes.add(new Trip(address.get(0), address.get(i),
							new Date(System.currentTimeMillis() + d1 * i),
							new Date(System.currentTimeMillis() + d1 * 2 * i),
							new Date(System.currentTimeMillis() + d1 * 3 * i),
							3, 4, 10.0, "comment" + i, TripStatus.OPEN, u));
					break;
				}

			}
		return viajes;
	}

	private ArrayList<AddressPoint> createAddressPoints(int num) {
		ArrayList<AddressPoint> address = new ArrayList<>();
		City[] cities = City.values();
		for (int i = 1; i < num + 1; i++)
			if (i % 2 == 0)
				address.add(new AddressPoint("Direccion" + i, cities[i
						% cities.length], "State" + i, "country" + i, "00" + i
						+ "" + i, new Waypoint(0.0, 0.0)));
			else
				address.add(new AddressPoint("Direccion" + i, cities[i
						% cities.length], "State" + i, "Country" + i, "00" + i
						+ "" + i, new Waypoint(0.0, 0.0)));

		return address;
	}

}