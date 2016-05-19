package com.sdi.actions.impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.sdi.actions.Action;
import com.sdi.infraestructure.factories.Factories;

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
			Factories.business.getValoracionesService().remove(id);
			System.out.println("Valoración [" + id + "] eliminado");
		} catch (NumberFormatException | IOException e) {
			System.out.println("Error con el id de la valoración");
			e.printStackTrace();
		}
		
	}
	@Override
	public String describe() {
		return "Eliminar valoración";
	}

}