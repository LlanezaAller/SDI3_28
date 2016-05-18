package com.sdi.business.impl.classes.valoraciones;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Rating;

public class removeValoracion implements Command {

	public long id;
	
	public removeValoracion(long id){
		this.id = id;
	}
	
	@Override
	public Object execute() {
		Rating rat = Factories.persistence.CreateRatingFinder().findById(id);
		Factories.persistence.CreateRatingFinder().deleteRating(rat);
		return null;
	}

}
