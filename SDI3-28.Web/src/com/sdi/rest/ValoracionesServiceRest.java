package com.sdi.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.model.Rating;

@Path("/ValoracionesService")
public interface ValoracionesServiceRest {
	
	@GET
	@Path("/findAllOrderedByDate")
	@Produces({MediaType.APPLICATION_XML})
	List<Rating> findAllOrdered();
	
	@DELETE
	@Path("/remove/{id}")
	void remove(@PathParam("id") long id_valoracion);

	
	
}
