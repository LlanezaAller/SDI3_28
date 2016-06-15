package com.sdi.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.Seat;
import com.sdi.model.Trip;

@Path("/AsientosService")
public interface AsientosServiceRest {
	
	@POST
	@Path("/saveSeat")
	@Consumes({MediaType.APPLICATION_XML})
	void saveSeat(Seat seat) throws EntityAlreadyExistsException;
	
	@GET
	@Path("/findByUserAndTrip/{idUser}/{idTrip}")
	@Produces({MediaType.APPLICATION_XML})
	Seat findByUserAndTrip(@PathParam("idUser") Long idUser, @PathParam("idTrip") Long idTrip)
			throws EntityNotFoundException;
	
	@GET
	@Path("/findByUser/{idUser}")
	@Produces({MediaType.APPLICATION_XML})
	List<Seat> findByUser(@PathParam("idUser") Long idUser);
	
	@POST
	@Path("/cambiarEstadoAsiento")
	@Consumes({MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_XML})
	Trip cambiarEstadoAsiento(Seat s, String login);

}
