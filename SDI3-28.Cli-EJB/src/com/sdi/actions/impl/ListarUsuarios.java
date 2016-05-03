package com.sdi.actions.impl;

import java.util.List;

import com.sdi.actions.Action;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.User;

public class ListarUsuarios implements Action{

	@Override
	public void execute() {
		System.out.println("Listando usuarios...");
		System.out.println("");
		System.out.println("Nick\t\tNombre\t\tApellidos\t\t");
		List<User> users =null;//= Factories.business.getUsuariosService().findAll();
		for(User u:users){
			System.out.println(u.getLogin() + "\t" +u.getName() + "\t" +u.getSurname());
		}
	}

	@Override
	public String describe() {
		return "Listar usuarios";
	}
}
