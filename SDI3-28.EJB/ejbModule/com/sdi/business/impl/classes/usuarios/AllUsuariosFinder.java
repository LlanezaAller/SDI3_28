package com.sdi.business.impl.classes.usuarios;

import java.util.List;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.User;

public class AllUsuariosFinder implements Command{

	@Override
	public Object execute() {
		List<User> users = Factories.persistence.CreateUserFinder().findAll();
		
		return users;
	}

}
