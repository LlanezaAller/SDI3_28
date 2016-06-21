package com.sdi.actions.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sdi.actions.Action;
import com.sdi.business.ValoracionesService;
import com.sdi.business.impl.RemoteEJBServicesLocator;
import com.sdi.model.SimpleRating;

public class ListarValoraciones implements Action {

	@Override
	public void execute() {
		System.out.println("Listando valoraciones...");
		System.out.println("");
		System.out
				.println("ID\tCiudad destino\tUsuario valorado\tAutor valoración\tValoración\tComentario");

		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -30);
		Date limitDate = cal.getTime();

		ValoracionesService valoracionesService = new RemoteEJBServicesLocator()
				.getValoracionesService();

		for (SimpleRating r : valoracionesService.findLatestRatings(limitDate)) {
			System.out.println(r.getId() + "\t"
					+ r.getAboutTrip().getDestination().getCity() + "\t"
					+ r.getAbout().getName() + "\t" + r.getFrom().getName()
					+ "\t" + r.getRating() + "\t" + r.getComment());
		}
	}

	@Override
	public String describe() {
		return "Listar valoraciones";
	}
}
