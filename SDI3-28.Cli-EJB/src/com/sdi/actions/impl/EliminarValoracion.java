package com.sdi.actions.impl;

import java.io.BufferedReader;

import com.sdi.actions.Action;
import com.sdi.infraestructure.factories.Factories;

public class EliminarValoracion  implements Action{
	private BufferedReader console;
	
	private long id_valoracion;
	
	public EliminarValoracion(BufferedReader console) {
		this.console=console;
	}
	@Override
	public void execute() {
		System.out.println("Introduzca el id de la valoración a eliminar...");
		System.out.println("");
		
		Factories.business.getValoracionesService().remove(id_valoracion);
	}
	@Override
	public String describe() {
		return "Eliminar valoración";
	}

}