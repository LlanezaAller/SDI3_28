package com.sdi.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;

import org.primefaces.event.SelectEvent;

import alb.util.log.Log;

import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.TripUser;
import com.sdi.model.User;
import com.sdi.model.type.SeatStatus;
import com.sdi.model.type.TripStatus;

@ManagedBean
@ViewScoped
public class BeanTripList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Trip> trips;
	private List<TripUser> tripsRelation;

	private Trip selectedTrip;
	private List<TripUser> selectedTripsRelation;

	public void loadOpenedTrips(ComponentSystemEvent event) {
		List<Trip> openedTrips = Factories.business.getViajesService()
				.findAllByStatus(TripStatus.OPEN);
		trips = new ArrayList<>();
		Log.info("Cargado lista de viajes disponibles...");
		for (int i = 0; i < openedTrips.size(); i++) {
			if (!openedTrips.get(i).getAvailablePax().equals(0)) {
				trips.add(openedTrips.get(i));
			} else
				Log.info("Borrando viaje %d", openedTrips.get(i).getId());
		}

	}

	public void loadUserTrips(ComponentSystemEvent event) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("user");
		tripsRelation = new ArrayList<TripUser>();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication()
				.getResourceBundle(facesContext, "msgs");
		if (user != null) {
			List<Trip> notConfirmedTrips = Factories.business
					.getViajesService().findAllAplicantsByUserId(
							user.getId());
			for (Trip t : notConfirmedTrips) {
				if (t.getStatus() == TripStatus.CANCELLED) {
					tripsRelation.add(new TripUser(t, bundle
							.getString("tripCancelled")));
				} else if (t.getStatus() == TripStatus.OPEN) {
					tripsRelation.add(new TripUser(t, bundle
							.getString("seatPending")));
				} else {
					tripsRelation.add(new TripUser(t, bundle
							.getString("seatFull")));
				}
			}
			List<Seat> seats = Factories.business.getAsientosService()
					.findByUser(user.getId());
			for (Seat s : seats) {
				if (user.getId().equals(s.getTrip().getPromoter().getId())) {
					tripsRelation.add(new TripUser(s.getTrip(), bundle
							.getString("tripPromoter")));
				} else {
					if (s.getTrip().getStatus() == TripStatus.CANCELLED) {
						tripsRelation.add(new TripUser(s.getTrip(), bundle
								.getString("tripCancelled")));
					} else {
						if (s.getStatus() == SeatStatus.ACCEPTED)
							tripsRelation.add(new TripUser(s.getTrip(), bundle
									.getString("seatAccepted")));
						else if (s.getStatus() == SeatStatus.EXCLUDED)
							tripsRelation.add(new TripUser(s.getTrip(), bundle
									.getString("seatRejected")));
					}
				}
			}
		}
	}

	public void viewTrip(SelectEvent event) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("user");
		if (user != null) {
			ExternalContext ec = FacesContext.getCurrentInstance()
					.getExternalContext();
			try {
				ec.redirect(ec.getRequestContextPath()
						+ "/user/viewTrip.xhtml?trip=" + selectedTrip.getId());
			} catch (IOException e) {
				Log.error("Error al redirigir a la página de viajes");
			}
		} else {
			Log.error("Usuario no autorizado ha intentado ver un viaje");
		}
	}

	public void cancelTrips() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("user");

		if (user != null) {
			int cancelledTrips = Factories.business.getViajesService()
					.cancelTrips(selectedTripsRelation, user);

			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle bundle = context.getApplication().getResourceBundle(
					context, "msgs");

			context.addMessage(
					null,
					new FacesMessage(String.format(
							bundle.getString("cancelledTripsMessage"),
							cancelledTrips)));
		} else {
			Log.error("Un visitante no logueado ha intentado borrar un viaje");
		}
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public List<TripUser> getTripsRelation() {
		return tripsRelation;
	}

	public void setTripsRelation(List<TripUser> tripsRelation) {
		this.tripsRelation = tripsRelation;
	}

	public Trip getSelectedTrip() {
		return selectedTrip;
	}

	public void setSelectedTrip(Trip selectedTrip) {
		this.selectedTrip = selectedTrip;
	}

	public List<TripUser> getSelectedTripsRelation() {
		return selectedTripsRelation;
	}

	public void setSelectedTripsRelation(List<TripUser> selectedTripsRelation) {
		this.selectedTripsRelation = selectedTripsRelation;
	}
}
