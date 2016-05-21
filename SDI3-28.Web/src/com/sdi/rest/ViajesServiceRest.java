package com.sdi.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.Trip;
import com.sdi.model.TripUser;
import com.sdi.model.User;
import com.sdi.model.type.TripStatus;

@Path("/ViajesService")
public interface ViajesServiceRest {
	
	@GET
	@Path("/findTrip/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Trip findTrip(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@POST
	@Path("/saveTrip")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void saveTrip(Trip viaje) throws EntityAlreadyExistsException;
	
	@GET
	@Path("/findAllTrips")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Trip> findAllTrips();
	
	@GET
	@Path("/findAllAplicantsByUserID/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Trip> findAllAplicantsByUserId(@PathParam("id") Long id);
	
	@GET
	@Path("/findAllByStatus")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Trip> findAllByStatus(TripStatus status);
	
	@GET
	@Path("/findAllTripsByPromoterID/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Trip> findAllTripsByPromoterId(@PathParam("id") Long id);
	
	@PUT
	@Path("/update")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void update(Trip t);
	
	@PUT
	@Path("/cancelTrips")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	int cancelTrips(List<TripUser> selectedTripsRelation, User user);
}
