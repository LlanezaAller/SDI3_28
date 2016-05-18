package com.sdi.actions.impl;

import java.util.List;

import com.sdi.actions.Action;
import com.sdi.business.UsuariosService;
import com.sdi.business.ViajesService;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.User;

public class ListarUsuarios implements Action{

	@Override
	public void execute() {
		System.out.println("Listando usuarios...");
		System.out.println("");
		System.out.println("Nick\t\tNombre\t\tApellidos\t\tNº Viajes creados \t\t Nº Viajes Participados");
		UsuariosService userService = Factories.business.getUsuariosService();
		ViajesService tripService = Factories.business.getViajesService();
		List<User> users =userService.findAll();

		for(User u:users){
			System.out.println(u.getLogin() + "\t" +u.getName() + "\t" + u.getSurname() + "\t");
			//TODO + tripService.findPromotedTrips() + \t + tripService.findParticipatedTrips);
		}
	}

	@Override
	public String describe() {
		return "Listar usuarios";
	}
}
