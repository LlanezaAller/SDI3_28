package com.sdi.actions.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import com.sdi.actions.Action;
import com.sdi.ws.EJBValoracionesServiceService;
import com.sdi.ws.Rating;
import com.sdi.ws.ValoracionesService;

public class ListarValoraciones implements Action {

	@Override
	public void execute() {
		System.out.println("Listando valoraciones...");
		System.out.println("");
		System.out
				.println("ID\tCiudad destino\tUsuario valorado\tAutor valoración\tValoración\tComentario");
		GregorianCalendar limitDate = new GregorianCalendar();
		limitDate.add(Calendar.DAY_OF_MONTH, -30);
		ValoracionesService valoracionesService = new EJBValoracionesServiceService()
				.getValoracionesServicePort();

		try {
			for (Rating r : valoracionesService
					.findLatestRatings(DatatypeFactory.newInstance()
							.newXMLGregorianCalendar(limitDate))) {
				System.out.println(r.getId() + "\t"
						+ r.getAboutSeat().getSeatTrip().getDestination().getCity() + "\t"
						+ r.getAboutSeat().getSeatUser().getName() + "\t" + r.getFromSeat().getSeatUser().getName()
						+ "\t" + r.getValue() + "\t" + r.getComment());
			}
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String describe() {
		return "Listar valoraciones";
	}
}
