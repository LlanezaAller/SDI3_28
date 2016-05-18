package com.sdi.actions.impl;

import java.io.BufferedReader;

import com.sdi.actions.Action;

public class EliminarValoracion  implements Action{
	private BufferedReader console;
	public EliminarValoracion(BufferedReader console) {
		this.console=console;
	}
	@Override
	public void execute() {
		System.out.println("Introduzca el id de la valoración a eliminar...");
		System.out.println("");
		
		//TODO ViajesService.deleteRating(id);
	}
	@Override
	public String describe() {
		return "Eliminar valoración";
	}

}