package com.sdi.actions.impl;

import java.util.Date;

import com.sdi.actions.Action;
import com.sdi.ws.EJBValoracionesServiceService;
import com.sdi.ws.Rating;
import com.sdi.ws.ValoracionesService;

public class ListarValoraciones implements Action {

	@Override
	public void execute() {
		System.out.println("Listando valoraciones...");
		System.out.println("");
		System.out.println("ID\tCiudad destino\tUsuario valorado\tAutor valoración\tValoración\tComentario");
		Date limitDate = new Date(System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000);
		ValoracionesService valoracionesService = new EJBValoracionesServiceService().getValoracionesServicePort();
		for (Rating r :valoracionesService.findAllOrdered()) {
			if(r.getAboutSeat().getTrip().getArrivalDate().toGregorianCalendar().getTime().before(limitDate))
					return;
			System.out.println(r.getId() + "\t" + r.getAboutSeat().getTrip().getDestination()
					.getCity()
					+ "\t" + r.getAboutSeat().getUser().getName()
					+ "\t" + r.getFromSeat().getUser().getName()
					+ "\t" + r.getValue()
					+ "\t" + r.getComment());
		}
	}

	@Override
	public String describe() {
		return "Listar valoraciones";
	}
}
