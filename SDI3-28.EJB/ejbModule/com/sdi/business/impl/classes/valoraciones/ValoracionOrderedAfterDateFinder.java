package com.sdi.business.impl.classes.valoraciones;

import java.util.Date;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;

public class ValoracionOrderedAfterDateFinder implements Command {
	private Date limitDate;
	
	public ValoracionOrderedAfterDateFinder(Date limitDate) {
		super();
		this.limitDate = limitDate;
	}

	@Override
	public Object execute() {
		return Factories.persistence.CreateRatingFinder()
				.findLatestRatings(limitDate);
	}

}
