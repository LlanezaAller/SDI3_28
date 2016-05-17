package com.sdi.business.impl.classes.viajes;

import java.util.List;

import alb.util.log.Log;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Trip;
import com.sdi.model.TripUser;
import com.sdi.model.User;
import com.sdi.model.type.TripStatus;

public class CancelarViajes implements Command {

	List<TripUser> listaPorCancelar;
	User user;

	public CancelarViajes(List<TripUser> selectedTripsRelation, User user) {
		this.listaPorCancelar = selectedTripsRelation;
		this.user = user;
	}

	@Override
	public Object execute() {
		int cancelledTrips = 0;
		for (TripUser t : listaPorCancelar) {
			if (user.getId().equals(t.getTrip().getPromoter().getId())) {
				if (t.getTrip().getStatus() == TripStatus.OPEN) {
					Trip t2 = Factories.persistence.CreateTripFinder()
							.findById(t.getTrip().getId());
					t2.setStatus(TripStatus.CANCELLED);
					Log.info("Viaje %d cancelado con éxito", t.getTrip()
							.getId());
					cancelledTrips++;

				} else {
					Log.error("Un usuario ha intentado cancelar un viaje después de su fecha de salida");
				}
			} else {
				Log.error("Un usuario no promotor ha intentado borrar un viaje");
			}
		}
		return cancelledTrips;
	}

}
