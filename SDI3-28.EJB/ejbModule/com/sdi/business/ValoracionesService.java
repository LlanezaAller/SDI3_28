package com.sdi.business;

import java.util.Date;
import java.util.List;

import com.sdi.model.Rating;
import com.sdi.model.SimpleRating;

public interface ValoracionesService {

	List<Rating> findAll();
	
	List<SimpleRating> findLatestRatings(Date limitDate);
	
	void remove(long id_valoracion);

	
	
}
