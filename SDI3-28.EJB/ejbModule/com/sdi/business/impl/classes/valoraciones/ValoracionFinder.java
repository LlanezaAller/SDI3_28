package com.sdi.business.impl.classes.valoraciones;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;

public class ValoracionFinder implements Command {
	
	@Override
	public Object execute() {
		return Factories.persistence.CreateRatingFinder()
				.findAllOrdered();
	}

}
