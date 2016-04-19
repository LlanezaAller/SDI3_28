package com.sdi.business.impl.classes.usuarios;

import com.sdi.business.Command;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infraestructure.factories.Factories;

public class UsuariosFinder implements Command {

	String usuario;

	public UsuariosFinder(String usuario) throws EntityNotFoundException {
		this.usuario = usuario;
	}

	@Override
	public Object execute() {
		return Factories.persistence.CreateUserFinder().findByLogin(usuario);
	}

}
