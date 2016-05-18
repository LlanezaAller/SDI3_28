package com.sdi.actions.impl;

import com.sdi.actions.Action;

public class EliminarValoracion  implements Action{

	@Override
	public void execute() {
		System.out.println("Introduzca el id de la valoración a eliminar...");
		System.out.println("");
		//TODO ViajesService.deleteRating()
	}
	@Override
	public String describe() {
		return "Eliminar valoración";
	}

}