package com.sdi.business.impl.classes.viajes;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;

public class ViajesFinder implements Command {

	Long id;

	public ViajesFinder(Long id) {
		this.id = id;
	}

	@Override
	public Object execute() {
		return Factories.persistence.CreateTripFinder().findById(id);
	}

}
