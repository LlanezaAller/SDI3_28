package com.sdi.business;

import java.util.List;

import com.sdi.model.Rating;

public interface ValoracionesService {

	List<Rating> findAllOrdered();

	void remove(long id_valoracion);

	
	
}
