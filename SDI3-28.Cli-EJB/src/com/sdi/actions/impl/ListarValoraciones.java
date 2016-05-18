package com.sdi.actions.impl;

import java.util.Date;

import com.sdi.actions.Action;
import com.sdi.business.ViajesService;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Rating;

public class ListarValoraciones implements Action {

	@Override
	public void execute() {
		System.out.println("Listando valoraciones...");
		System.out.println("");
		System.out.println("Ciudad destino\tUsuario valorado\tAutor valoración\tValoración\tComentario");
		ViajesService tripService = Factories.business.getViajesService();
		Date limitDate = new Date(System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000);
		/**for (Rating r : tripService.findValoraciones()) {// TODO findValoraciones() ordenadas de más reciente a más antigua
			if(r.getAboutSeat().getTrip().getArrivalDate().before(limitDate))
					return;
			System.out.println(r.getId() + "\t" + r.getAboutSeat().getTrip().getDestination()
					.getCity()
					+ "\t" + r.getAboutSeat().getUser().getName()
					+ "\t" + r.getFromSeat().getUser().getName()
					+ "\t" + r.getValue()
					+ "\t" + r.getComment());
		}*/
	}

	@Override
	public String describe() {
		return "Listar valoraciones";
	}
}
