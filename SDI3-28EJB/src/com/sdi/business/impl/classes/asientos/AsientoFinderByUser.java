package com.sdi.business.impl.classes.asientos;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;

public class AsientoFinderByUser implements Command {

	Long id;

	public AsientoFinderByUser(Long id) {
		this.id = id;
	}

	@Override
	public Object execute() {
		return Factories.persistence.CreateSeatFinder().findByUser(id);
	}

}
