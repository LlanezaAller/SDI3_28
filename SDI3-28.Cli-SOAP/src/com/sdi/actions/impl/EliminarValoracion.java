package com.sdi.actions.impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.sdi.actions.Action;
import com.sdi.ws.EJBValoracionesServiceService;

public class EliminarValoracion  implements Action{
	private BufferedReader console;
	
	public EliminarValoracion(BufferedReader console) {
		this.console=console;
	}
	@Override
	public void execute() {
		System.out.println("Introduzca el id de la valoración a eliminar...");	
		try {
			long id = Integer.valueOf(console.readLine());
			new EJBValoracionesServiceService().getValoracionesServicePort().remove(id);
			System.out.println("Valoración [" + id + "] eliminado");
		} catch (NumberFormatException | IOException e) {
			System.out.println("ID no válido. Reintentar.");
			execute();
		}
		
	}
	@Override
	public String describe() {
		return "Eliminar valoración";
	}

}