package com.sdi.business;

import java.util.Date;
import java.util.List;

import com.sdi.model.Rating;

public interface ValoracionesService {

	List<Rating> findAll();
	
	List<Rating> findLatestRatings(Date limitDate);
	
	void remove(long id_valoracion);

	
	
}
