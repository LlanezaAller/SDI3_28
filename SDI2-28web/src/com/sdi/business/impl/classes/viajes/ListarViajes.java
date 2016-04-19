package com.sdi.business.impl.classes.viajes;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;

public class ListarViajes implements Command {

	@Override
	public Object execute() {
		return Factories.persistence.CreateTripFinder().findAll();
	}

}
