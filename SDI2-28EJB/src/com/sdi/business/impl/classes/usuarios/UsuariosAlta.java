package com.sdi.business.impl.classes.usuarios;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.User;

public class UsuariosAlta implements Command {

	User usuario;

	public UsuariosAlta(User usuario) {
		this.usuario = usuario;
	}

	@Override
	public Object execute() {
		Factories.persistence.CreateUserFinder().newUser(usuario);
		return null;
	}

}
