package com.sdi.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import alb.util.log.Log;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.util.SdiUtil;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.type.AddressPoint;
import com.sdi.model.type.City;
import com.sdi.model.type.SeatStatus;
import com.sdi.model.type.TripStatus;
import com.sdi.model.type.Waypoint;

@ManagedBean
@ViewScoped
public class BeanTrip implements Serializable {
	private static final long serialVersionUID = 1L;
	private Trip trip;
	private boolean hasSeat;
	private boolean hasApplication;
	private boolean isEditable;
	private boolean isEditing;

	@PostConstruct
	public void init() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String tripID = request.getParameter("trip");
			if (tripID != null) {
				Log.info("Intentando cargar el viaje con id %s", tripID);
				Long id = Long.parseLong(tripID);
				trip = Factories.business.getViajesService().findTrip(id);
				if (trip != null) {
					checkHasApplication();
					checkHasSeat();
					isEditable = trip.getStatus() == TripStatus.OPEN;
					Log.info("Viaje %d cargado con éxito", trip.getId());
				}
			} else {
				clearTrip();
				Log.info("Inicializado con un trip vacio");
			}
		} catch (NumberFormatException | EntityNotFoundException ex) {
			Log.error("ID de viaje inválida");
		}
	}

	public void rejectSeat(Seat s) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("user");
		Log.info("Rechazando asiento...");
		if (user != null) {
			if (s.getTrip().getPromoter().getId().equals(user.getId())) {
				trip = Factories.business.getAsientosService()
						.cambiarEstadoAsiento(s, user.getLogin());
			} else {
				Log.error(
						"El usuario no es el promotor: Promotor real %d, Usuario %d",
						trip.getPromoter().getId(), user.getId());
			}

		} else {
			Log.error("Usuario no logueado");
		}
	}

	public void confirmSeat(Seat s) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("user");
		Log.info("Confirmando asiento...");
		if (user != null) {
			if (s.getTrip().getPromoter().getId().equals(user.getId())) {
				trip = Factories.business.getAsientosService()
						.cambiarEstadoAsiento(s, user.getLogin());
			} else {
				Log.error(
						"El usuario no es el promotor: Promotor real %d, Usuario %d",
						trip.getPromoter().getId(), user.getId());
			}
		} else {
			Log.error("Usuario no logueado");
		}
	}

	public void rejectApplication(User u) {
		manageApplication(SeatStatus.EXCLUDED, u);
	}

	public void confirmApplication(User u) {
		if (trip.getAvailablePax() > 0) {
			manageApplication(SeatStatus.ACCEPTED, u);
		} else {
			Log.error("No hay plazas disponibles");
		}
	}

	private void manageApplication(SeatStatus status, User u) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("user");
		if (user != null) {
			if (trip.getPromoter().getId().equals(user.getId())) {
				trip = Factories.business.getUsuariosService()
						.promoterManageApplication(status, u.getLogin(),
								trip.getId());
			} else {
				Log.error(
						"El usuario no es el promotor: Promotor real %d, Usuario %d",
						trip.getPromoter().getId(), user.getId());
			}
		} else {
			Log.error("Usuario no logueado");
		}
	}

	public void applyForSeat() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("user");
		if (user != null) {
			trip = Factories.business.getUsuariosService().aplicar(
					user.getLogin(), trip.getId());

			hasApplication = SdiUtil.assertHasApplication(user, trip);
			try {
				hasSeat = SdiUtil.assertHasSeat(user, trip);
			} catch (EntityNotFoundException e) {
				Log.debug(e);
			}
		} else {
			Log.error("Usuario no logueado");
		}
	}

	public void cancelMyApplication() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("user");
		if (user != null) {
			try {
				Factories.business.getUsuariosService().cancelarAplicacion(
						user.getLogin(), trip.getId());
				trip = Factories.business.getViajesService().findTrip(
						trip.getId());
			} catch (EntityNotFoundException e) {
				Log.error("Viaje no encontrado");
			}
			hasApplication = SdiUtil.assertHasApplication(user, trip);
			try {
				hasSeat = SdiUtil.assertHasSeat(user, trip);
			} catch (EntityNotFoundException e) {
				Log.debug(e);
			}

		} else {
			Log.error("Usuario no logueado");
		}
	}

	public String createTrip() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("user");
		if (user != null) {
			try {
				trip.setPromoter(user);

				Factories.business.getViajesService().saveTrip(trip);

				return "EXITO";
			} catch (EntityAlreadyExistsException e) {
				Log.error("Error al crear un viaje: Ya existe ese viaje");
			}

		} else {
			Log.error("Un visitante no logueado ha intentado crear un viaje");
		}

		return "FRACASO";
	}

	public void modifyTrip() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("user");

		Log.info("Intentando cargar viaje para modificar...");
		if (user != null && trip.getPromoter().getId().equals(user.getId())) {
			isEditing = true;
			Log.info("Editando viaje %d ...", trip.getId());
		}
	}

	public void saveTripChanges() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("user");
		if (user != null && trip.getPromoter().getId().equals(user.getId())) {

			Factories.business.getViajesService().update(trip);

			isEditing = false;
			Log.info("Trip editado con éxito");
		} else {
			Log.error("Usuario no autorizado a editar un viaje");
		}
	}

	public void clearTrip() {
		trip = new Trip();
		trip.setDeparture(new AddressPoint());
		trip.setDestination(new AddressPoint());
		trip.getDeparture().setWaypoint(new Waypoint(.0, .0));
		trip.getDestination().setWaypoint(new Waypoint(.0, .0));
	}

	public void fillDefault() {
		trip = new Trip();
		trip.setAvailablePax(4);
		trip.setMaxPax(5);
		trip.setEstimatedCost(200.0);
		trip.setClosingDate(new Date(System.currentTimeMillis() + 7 * 24 * 60
				* 60 * 1000));
		trip.setDepartureDate(new Date(System.currentTimeMillis() + 9 * 24 * 60
				* 60 * 1000));
		trip.setArrivalDate(new Date(System.currentTimeMillis() + 10 * 24 * 60
				* 60 * 1000));
		trip.setComments("");
		AddressPoint origin = new AddressPoint();
		origin.setAddress("C\\Calle por defecto de salida");
		origin.setCity(City.Madrid);
		origin.setState("Madrid");
		origin.setZipCode("28004");
		origin.setCountry("España");
		origin.setWaypoint(new Waypoint());
		trip.setDeparture(origin);
		AddressPoint destination = new AddressPoint();
		destination.setAddress("C\\Calle por defecto de llegada");
		destination.setCity(City.Barcelona);
		destination.setState("Cataluña");
		destination.setZipCode("08001");
		destination.setCountry("España");
		destination.setWaypoint(new Waypoint());
		trip.setDestination(destination);
		Log.info("Datos por defecto cargados");
	}

	public List<String> completeCity(String query) {
		City[] enumValues = City.values();
		List<String> results = new ArrayList<>();
		for (City c : enumValues) {
			if (c.toString().toLowerCase().startsWith(query.toLowerCase()))
				results.add(c.toString());
		}
		return results;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public boolean getHasSeat() {
		return hasSeat;
	}

	public void setHasSeat(boolean hasSeat) {
		this.hasSeat = hasSeat;
	}

	public boolean getHasApplication() {
		return hasApplication;
	}

	public void setHasApplication(boolean hasApplication) {
		this.hasApplication = hasApplication;
	}

	public boolean getIsEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public boolean getIsEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;
	}

	private void checkHasApplication() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				    .getExternalContext().getSessionMap();
		User user = (User) session.get("user");
		hasApplication = Factories.business.getViajesService()
				.checkHasApplication(user, trip);
	}

	private void checkHasSeat() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				    .getExternalContext().getSessionMap();
		User user = (User) session.get("user");
		try {
			hasSeat = Factories.business.getViajesService().checkHasSeat(user, trip);
		} catch (EntityNotFoundException e) {
			Log.debug(e);
		}
	}
}
